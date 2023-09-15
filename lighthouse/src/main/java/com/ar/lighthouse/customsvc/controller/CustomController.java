package com.ar.lighthouse.customsvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.InquiryVO;
import com.ar.lighthouse.customsvc.service.NoticeVO;
import com.ar.lighthouse.member.service.MemberVO;

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
	
	// 공지사항 상세화면
	@GetMapping("custom/noticeInfo")
	public String noticeDetail(@RequestParam(defaultValue = "0") int noticeCode,Model model, @ModelAttribute("cri") Criteria cri) {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setNoticeCode(noticeCode);
		
		model.addAttribute("noticeInfo",customService.getNotice(noticeVO));
		return "page/custom/noticeInfo"; 
	}
	
	// 1:1문의 화면
	@GetMapping("custom/inquiry")
	public String inquiryForm() {
		return "page/custom/inquiry";
	}
	
	// 등록 로직
	
	@PostMapping("custom/inquiryInsert")
	@ResponseBody
	public ResponseEntity<String> addInquiry(@RequestBody InquiryVO inqVO, HttpServletRequest req){
		
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		inqVO.setMemberId(memberVO.getMemberId());
		
		int insertCnt = customService.addInquiry(inqVO);
		return insertCnt == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
