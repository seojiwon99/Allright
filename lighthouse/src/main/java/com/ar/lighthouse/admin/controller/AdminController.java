package com.ar.lighthouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.customsvc.service.CustomService;

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
	@GetMapping("admin/main2")
	public String adminMain2(Criteria cri, Model model) {
		int totalCnt = customService.getTotalCount(cri);
		model.addAttribute("noticeList", customService.getNoticeList(cri));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/admin/adminMain2";
	}
	
	@GetMapping("admin/notice")
	public String noticeList(Criteria cri, Model model) {
		int totalCnt = customService.getTotalCount(cri);
		model.addAttribute("noticeList", customService.getNoticeList(cri));
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
	public String declareList() {
		return "page/admin/declareList";
	}
	@GetMapping("admin/clearDeclareList")
	public String cleardeclareList() {
		return "page/admin/clearDeclareList";
	}
	@GetMapping("admin/inquiryList")
	public String inquiryList() {
		return "page/admin/inquiryList";
	}
	@GetMapping("admin/clearInquiryList")
	public String ClearInquiryList() {
		return "page/admin/clearInquiryList";
	}
	@GetMapping("admin/buyerList")
	public String buyerList() {
		return "page/admin/buyerList";
	}
	@GetMapping("admin/sellerList")
	public String sellerList() {
		return "page/admin/sellerList";
	}
	@GetMapping("admin/allProductList")
	public String allProductList() {
		return "page/admin/allProductList";
	}
	@GetMapping("admin/bannerUpdateForm")
	public String bannerUpdateForm() {
		return "page/admin/bannerUpdateForm";
	}
	
}
