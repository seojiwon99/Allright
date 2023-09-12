package com.ar.lighthouse.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class MemberJoinController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/page/member/AjaxJoin")
	public String memberJoin(@RequestBody MemberVO memberVO) {
		
		memberVO.setMemberPw(passwordEncoder.encode(memberVO.getMemberPw()));
		System.out.println(memberVO.getMemberPw());
		
		String result = "";
		if(memberService.MemberJoin(memberVO)>0) {
			result = "/page/member/loginForm";
		}else {
			result = "/page/member/joinForm";
		}
		return result;
	}
}
