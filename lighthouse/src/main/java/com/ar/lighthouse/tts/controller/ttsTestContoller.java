package com.ar.lighthouse.tts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class ttsTestContoller {
	
	
	@GetMapping("page/test/ttsTest")
	public void ttsTest(HttpServletRequest request) {
		HttpSession session = request.getSession();
	}
	@GetMapping("page/test/ttsAndstt")
	public void ttsstt() {
		
	}
	@GetMapping("testCategory")
	public String testtest() { 
		return "page/test/testbody";
	}
}
