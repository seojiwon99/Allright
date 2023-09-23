package com.ar.lighthouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
public class AdminController {
	
	@Autowired
	CustomService customService;
	
	@Autowired
	AdminService adminService;
	
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
	public String faqList(@RequestParam(required = false, defaultValue = "", value="faqType") String faqType, Model model) {
		model.addAttribute("faqList", customService.getFaqList(faqType));
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
		System.out.println(declareVO);
		int totalCnt = adminService.getTotalClearDeclareCount(declareVO);
		model.addAttribute("declareList", adminService.getClearDeclareList(cri.getAmount(), cri.getPageNum(), declareVO.getDeclareContent(), declareVO.getDeclareReason()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/clearDeclareList";
	}
	
	@GetMapping("admin/declareDetail")
	public String declareDetail(Model model, DeclareVO declareVO) {
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
		return "success";
	}
	
	@GetMapping("admin/allProductList")
	public String allProductList(Criteria cri, Model model, ProductDetailVO productDetailVO) {
		int totalCnt = adminService.getTotalProductCount(productDetailVO);
		model.addAttribute("proList", adminService.getProductList(cri.getAmount()
				, cri.getPageNum(), productDetailVO.getMemberId()
				, productDetailVO.getMemberTel()
				, productDetailVO.getBusinessNumber(), productDetailVO.getProductCode())) ;
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		
		
		return "page/admin/allProductList";
	}
	
	
	
	@GetMapping("admin/bannerUpdateForm")
	public String bannerUpdateForm() {
		return "page/admin/bannerUpdateForm";
	}
	
}
