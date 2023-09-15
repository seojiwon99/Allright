package com.ar.lighthouse.customsvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.InquiryVO;

@Controller
public class CustomController {
	
	@Autowired
	CustomService customService;
	
	// faq 화면
	@GetMapping("/custom/faqList")
	public String faqList(@RequestParam(required = false, defaultValue = "", value="faqType") String faqType, Model model) {
		model.addAttribute("faqList", customService.getFaqList(faqType));
		
		return "page/custom/faqList";
	}
	
	// 공지사항 화면(페이징)
	@GetMapping("custom/notice")
	public String noticeList(Model model, Criteria cri) {
		int totalCnt = customService.getTotalCount(cri);
		model.addAttribute("noticeList", customService.getNoticeList(cri));
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		return "page/custom/notice";
	}
	
	
	// 1:1문의 화면
	@GetMapping("custom/inquiry")
	public String inquiry() {
		return "page/custom/inquiry";
	}
	
	@PostMapping("custom/inquiryInsert")
	public ResponseEntity<String> inqInsert(@RequestBody InquiryVO inqVO){
		int insertCnt = customService.inqInsert(inqVO);
		return insertCnt == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
