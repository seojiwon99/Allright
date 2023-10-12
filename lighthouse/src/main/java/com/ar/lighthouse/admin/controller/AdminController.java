package com.ar.lighthouse.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.admin.service.ProductDetailVO;
import com.ar.lighthouse.admin.service.SuspendVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.FaqVO;
import com.ar.lighthouse.customsvc.service.InquiryVO;
import com.ar.lighthouse.main.service.EventImgVO;
import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.member.mail.RegisterMail;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class AdminController {
	
	@Autowired
	CustomService customService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MainPageService service;
	
	@Autowired
    RegisterMail registerMail;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@GetMapping("admin/main")
	public String adminMain() {
		
		return "page/admin/adminMain";
	}
	
	@GetMapping("admin/notice")
	public String noticeList(Criteria cri, Model model, NoticeAdminVO noticeAdminVO) {
		int totalCnt = adminService.getTotalNoticeCount(noticeAdminVO);
		model.addAttribute("noticeList", adminService.getAdminNoticeList(cri , noticeAdminVO));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/notice";
	}
	
	@GetMapping("admin/faq")
	public String faqList(Criteria cri,Model model, FaqVO faqVO) {
		int totalCnt = adminService.getTotalFaqCount(faqVO);		
		model.addAttribute("faqList", adminService.getAdminFaqList(cri , faqVO));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/faq";
	}
	
	@GetMapping("admin/noticeForm")
	public String noticeForm() {
		return "page/admin/noticeForm";
	}
	
	@GetMapping("admin/noticeDetail")
	public String noticeDetail(Model model,NoticeAdminVO noticeAdminVO) {
		model.addAttribute("noticeAdminVO", adminService.getNoticeDetail(noticeAdminVO));
		
		return "page/admin/noticeDetail";
	}
	
	@GetMapping("admin/faqForm")
	public String faqForm() {
		return "page/admin/faqForm";
	}
	@GetMapping("admin/faqDetail")
	public String faqDetail(Model model, FaqVO faqVO) {
		model.addAttribute("faqVO", adminService.getFaqDetail(faqVO)); 
		
		return "page/admin/faqDetail";
	}
	
	@PostMapping("admin/addNotice")
	public String addNotice(NoticeAdminVO noticeAdminVO) {
		adminService.addNotice(noticeAdminVO);
		return "redirect:/admin/notice";
	}
	@PostMapping("admin/addFaq")
	public String addFaq(FaqVO faqVO) {
		adminService.addFaq(faqVO);
		return "redirect:/admin/faq";
	}
	@PostMapping("admin/editNotice")
	public String editNotice(NoticeAdminVO noticeAdminVO) {
		adminService.editNotice(noticeAdminVO);
		return "redirect:/admin/notice";
	}
	@PostMapping("admin/editFaq")
	public String editFaq(FaqVO faqVO) {
		adminService.editFaq(faqVO);
		return "redirect:/admin/faq";
	}
	
	
	@GetMapping("admin/declareList")
	public String declareList(Criteria cri,Model model, DeclareVO declareVO) {
		System.out.println(declareVO);
		int totalCnt = adminService.getTotalDeclareCount(declareVO);
		model.addAttribute("declareList", adminService.getDeclareList(cri.getAmount(), cri.getPageNum(), declareVO.getDeclareContent(), declareVO.getDeclareReason()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/declareList";
	}
	
	@GetMapping("admin/clearDeclareList")
	public String cleardeclareList(Criteria cri,Model model, DeclareVO declareVO) {
		int totalCnt = adminService.getTotalClearDeclareCount(declareVO);
		model.addAttribute("declareList", adminService.getClearDeclareList(cri.getAmount(), cri.getPageNum(), declareVO.getDeclareContent(), declareVO.getDeclareReason()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/clearDeclareList";
	}
	
	@GetMapping("admin/declareDetail")
	public String declareDetail(Model model, DeclareVO declareVO) {
		System.out.println(declareVO);

		DeclareVO vo = adminService.getDeclareDetail(declareVO);
		vo.setSuspendStatus(declareVO.getSuspendStatus());
		model.addAttribute("declareDetail", vo);

		return "page/admin/declareDetail";
	}
	@GetMapping("admin/clearDeclareDetail")
	public String clearDeclareDetail(Model model, DeclareVO declareVO) {
		model.addAttribute("declareDetail", adminService.getDeclareDetail(declareVO));
		return "page/admin/declareDetail";
	}
	
	@PostMapping("admin/suspendUser")
	@ResponseBody
	public String editSuspendUser(@RequestBody SuspendVO suspendVO) {
		if(suspendVO.getSuspStatus()%100 == 1) {
			suspendVO.setSuspDate(30);
		}else if(suspendVO.getSuspStatus()%100 == 2){
			suspendVO.setSuspDate(90);
		}else if(suspendVO.getSuspStatus()%100 == 3) {
			suspendVO.setSuspDate(9000);
		}
		
		if(adminService.addSuspend(suspendVO)>0) {
			adminService.editDeclareStatus(suspendVO);
		};
		
		return "success";
	}
	
	@GetMapping("admin/inquiryDetail")
	public String inquiryDetail(Model model, InquiryVO inquiryVO) {
		model.addAttribute("inquiryDetail", adminService.getInquiryDetail(inquiryVO));
		return "page/admin/inquiryDetail";
	}
	
	
	
	
	
	
	@GetMapping("admin/inquiryList")
	public String inquiryList(Criteria cri,Model model, InquiryVO inquiryVO) {
		String status = "N";
		inquiryVO.setCustomInquiryAnswerStatus(status);
		System.out.println(inquiryVO);
		int totalCnt = adminService.getTotalInquiryCount(inquiryVO);
		model.addAttribute("inqList", adminService.getInquiryList(cri.getAmount(), cri.getPageNum(), inquiryVO.getCustomInquiryTitle()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/inquiryList";
	}
	@GetMapping("admin/clearInquiryList")
	public String ClearInquiryList(Criteria cri, Model model, InquiryVO inquiryVO) {
		String status = "Y"; 
		inquiryVO.setCustomInquiryAnswerStatus(status);
		System.out.println(inquiryVO);
		int totalCnt = adminService.getTotalInquiryCount(inquiryVO);
		model.addAttribute("inqList", adminService.getClearInquiryList(cri.getAmount(), cri.getPageNum(), inquiryVO.getCustomInquiryTitle()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/clearInquiryList";
	}
	@PostMapping("admin/updateCustomInquiry")
	@ResponseBody
	public String updateCustomInquiry(@RequestBody InquiryVO inquiryVO) {
		adminService.editCustomInquiry(inquiryVO);
		return "success";
	}
	
	
	
	@GetMapping("admin/buyerList")
	public String buyerList(Criteria cri, Model model, MemberDetailVO memberDetailVO) {
		memberDetailVO.setMemberAuthor(1);
		int totalCnt = adminService.getTotalUserCount(memberDetailVO);
		model.addAttribute("memList", adminService.getBuyerList(cri.getAmount()
				, cri.getPageNum(), memberDetailVO.getMemberId()
				, memberDetailVO.getMemberName(), memberDetailVO.getMemberTel()
				, memberDetailVO.getBusinessNumber(), memberDetailVO.getMemberAuthor())) ;
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		model.addAttribute("suspReason", adminService.getSuspReason());
		
		return "page/admin/buyerList";
	}
	
	@GetMapping("admin/sellerList")
	public String sellerList(Criteria cri, Model model, MemberDetailVO memberDetailVO) {
		memberDetailVO.setMemberAuthor(2);
		int totalCnt = adminService.getTotalUserCount(memberDetailVO);
		model.addAttribute("memList", adminService.getBuyerList(cri.getAmount()
				, cri.getPageNum(), memberDetailVO.getMemberId()
				, memberDetailVO.getMemberName(), memberDetailVO.getMemberTel()
				, memberDetailVO.getBusinessNumber(), memberDetailVO.getMemberAuthor())) ;
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		model.addAttribute("suspReason", adminService.getSuspReason());
		
		return "page/admin/sellerList";
	}
	@PostMapping("admin/suspendByAdmin")
	@ResponseBody
	public String addSuspendByAdmin(@RequestBody SuspendVO suspendVO) {
		if(suspendVO.getSuspStatus() == 0) {
			//수정
			adminService.editSuspendStatus(suspendVO.getMemberId());
		}else {
			//등록
			if(suspendVO.getSuspStatus()%100 == 1) {
				suspendVO.setSuspDate(30);
			}else if(suspendVO.getSuspStatus()%100 == 2){
				suspendVO.setSuspDate(90);
			}else if(suspendVO.getSuspStatus()%100 == 3) {
				suspendVO.setSuspDate(9000);
			}
			adminService.addSuspendByAdmin(suspendVO);
		}
		int status = suspendVO.getSuspStatus();
		String msg = String.valueOf(status);
		return msg;
	}
	
	
	@GetMapping("admin/memberDetailValue")
	@ResponseBody
	public MemberDetailVO selectMemberDetailValue(String memberId) {
		return adminService.getMemberDetailValue(memberId);
	}
	
	
	@GetMapping("admin/allProductList")
	public String allProductList(Criteria cri, Model model, ProductDetailVO productDetailVO) {
		int totalCnt = adminService.getTotalProductCount(productDetailVO);
		model.addAttribute("proList", adminService.getProductList(cri.getAmount()
				, cri.getPageNum(), productDetailVO.getMemberId()
				, productDetailVO.getMemberTel()
				, productDetailVO.getBusinessNumber(), productDetailVO.getProductCode())) ;
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		model.addAttribute("suspReason", adminService.getSuspReason());
		
		
		return "page/admin/allProductList";
	}
	
	@GetMapping("admin/removeProductByAdmin")
	@ResponseBody
	public String removeProductByAdmin(String productCode, String deleteReason, String deleteStatus, String memberEmail) {
		String rs = null;
		int delRs = adminService.removeProductByAdmin(productCode);
		System.out.println("delRs : " + delRs);
		if(delRs>0) {
			try {
				rs = registerMail.sendDelMessage(memberEmail,deleteReason);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	
	
	@GetMapping("admin/bannerUpdateForm")
	public String bannerUpdateForm( Model model) {
		model.addAttribute("banner",adminService.getEventBannerList());
		return "page/admin/bannerUpdateForm";
	}
	
	@PostMapping("admin/addBanner")
	@ResponseBody
	public String addBaner(MultipartFile uploadFile, EventImgVO eventImgVO) {
		
		if(uploadFile.getContentType().startsWith("image") == false){
    		System.err.println("this file is not image type");
    		return null;
    	}
		
    	String originalName = uploadFile.getOriginalFilename();
        // System.out.println("originalName : " + originalName);
        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
        eventImgVO.setImgName(fileName);
        
        // System.out.println("fileName : " + fileName);
    
        //날짜 폴더 생성
        String folderPath = makeFolder();
        // System.out.println("folderPath"+ folderPath);
        String uuid = UUID.randomUUID().toString();	// 유니크한 이름 때문에
        //System.out.println("uuid"+uuid);
        eventImgVO.setUploadName(uuid+"_"+fileName);
        
        String uploadFileName = folderPath + '/' + uuid + "_" + fileName;
        // System.out.println("uploadFileName" + uploadFileName);
        eventImgVO.setUploadPath(folderPath);
        
        String saveName = uploadPath + '/' + uploadFileName;
        
        Path savePath = Paths.get(saveName);
        try{
        	uploadFile.transferTo(savePath); // 파일의 핵심
        	System.out.println(uploadFile);
        	System.out.println(eventImgVO);
        	adminService.addBanner(eventImgVO);
        	return "success";
        } catch (IOException e) {
             e.printStackTrace();	      
             return "fail";
        }
	}
	
	@PostMapping("admin/editBanner")
	@ResponseBody
	public String editBanner(MultipartFile uploadFile,EventImgVO eventImgVO) {
		if(uploadFile == null) {
			adminService.editBanner(eventImgVO);
			return "update success";
		}else {
			String prevPath = adminService.findBannerPath(eventImgVO.getEventImgCode()); // 이전 파일 경로 
			deleteFile(prevPath); // 삭제 (boolean)
			
			String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        eventImgVO.setImgName(fileName);
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        
	        String uuid = UUID.randomUUID().toString();	// 유니크한 이름 때문에
	        eventImgVO.setUploadName(uuid+"_"+fileName);
	        
	        String uploadFileName = folderPath + '/' + uuid + "_" + fileName;
	        eventImgVO.setUploadPath(folderPath);
	        
	        String saveName = uploadPath + '/' + uploadFileName;
	        Path savePath = Paths.get(saveName);
	        try{
	        	uploadFile.transferTo(savePath); // 파일의 핵심
	        	adminService.editBanner(eventImgVO);
	        	return "img edit success";
	        } catch (IOException e) {
	             e.printStackTrace();	      
	             return "fail";
	        }
			
		}
//		
//		
	}
	
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // 경로에서 사용하는 /는 인지 못함
		// LocalDate를 문자열로 포멧
		String folderPath = str;//.replace("/", File.separator); // <- 그래서 separator 사용
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}

	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
	private boolean deleteFile(String path) {
		File targetDir = Paths.get(uploadPath, path).toFile();
		System.out.println("target" + targetDir);
		return targetDir.delete();
	}
	
	
	@GetMapping("header")
	public String header(Model model) {
		model.addAttribute("categories",service.getCategoryList());
		model.addAttribute("allCtg", service.getAllCategoryList());
		return "fragments/header";
	}
	
}
