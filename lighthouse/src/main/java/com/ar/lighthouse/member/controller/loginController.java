package com.ar.lighthouse.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.service.MemberService;

@Controller
public class loginController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("page/member/join")
	public void loginForm() {
		
	}
	
	@GetMapping("page/member/idCheck")
	@ResponseBody
	public int idCheck(String memberId) {
		System.out.println(memberId);
		return memberService.MemberCheck(memberId);
	}
}
