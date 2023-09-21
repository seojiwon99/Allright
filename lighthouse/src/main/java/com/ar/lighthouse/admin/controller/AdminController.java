package com.ar.lighthouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.admin.service.ProductDetailVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.customsvc.service.CustomService;
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
	
	@PostMapping("admin/addNotice")
	public String addNotice(NoticeAdminVO noticeAdminVO) {
		System.out.println(noticeAdminVO);
		adminService.addNotice(noticeAdminVO);
		return "redirect:/admin/notice";
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
	
	
	
	@GetMapping("admin/inquiryList")
	public String inquiryList(Criteria cri,Model model, InquiryVO inquiryVO) {
		int totalCnt = adminService.getTotalInquiryCount(inquiryVO);
		model.addAttribute("inqList", adminService.getInquiryList(cri.getAmount(), cri.getPageNum(), inquiryVO.getCustomInquiryTitle()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/inquiryList";
	}
	@GetMapping("admin/clearInquiryList")
	public String ClearInquiryList(Criteria cri, Model model, InquiryVO inquiryVO) {
		int totalCnt = adminService.getTotalInquiryCount(inquiryVO);
		model.addAttribute("inqList", adminService.getClearInquiryList(cri.getAmount(), cri.getPageNum(), inquiryVO.getCustomInquiryTitle()));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/clearInquiryList";
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
		
		
		return "page/admin/sellerList";
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
