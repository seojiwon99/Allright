package com.ar.lighthouse.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class loginController {
	
	@Autowired
	MemberService memberService;
	//로그인화면으로
	@GetMapping("page/member/loginForm")
	public void loginForm() {
		
	}
	//회원가입 화면으로
	@GetMapping("page/member/joinForm")
	public void joinForm() {
		
	}
	//id 중복체크
	@GetMapping("page/member/idCheck")
	@ResponseBody
	public int idCheck(String memberId) {
		System.out.println(memberId);
		return memberService.MemberCheck(memberId);
	}
	
	//로그인
	@PostMapping("page/member/login")
	@ResponseBody
	public MemberVO login(@RequestBody MemberVO memberVO) {
		//
		System.out.println(memberVO);
		return memberService.memberLogin(memberVO);
	}
}
