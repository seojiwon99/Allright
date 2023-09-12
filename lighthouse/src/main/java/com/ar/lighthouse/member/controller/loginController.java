package com.ar.lighthouse.member.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;

@Controller
@SessionAttributes("loginMember")
public class loginController {
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberService memberService;
	
	//로그인화면으로
	@GetMapping("page/member/loginForm")
	public void loginForm() {
		
	}
	//회원가입 화면으로
	@GetMapping("page/member/joinSellerForm")
	public void joinSForm() {
		
	}
	@GetMapping("page/member/joinBuyerForm")
	public void joinBForm() {
		
	}
	//id 중복체크
	@GetMapping("page/member/idCheck")
	@ResponseBody
	public int idCheck(String memberId) {
		System.out.println(memberId);
		return memberService.MemberCheck(memberId);
	}
	@GetMapping("page/member/findMember")
	public void findMember() {
		
	}
	
	
	//로그인
	@PostMapping("page/member/login")
	@ResponseBody
	public String login(@RequestBody MemberVO memberVO, HttpSession session) {
		//
		
		System.out.println(memberVO);
		MemberVO loginVO = memberService.memberLogin(memberVO);
		if(loginVO.getMemberId() == null) {
			return "fail";
		}
		
		if(passwordEncoder.matches(memberVO.getMemberPw(), loginVO.getMemberPw())) {
			session.setAttribute("loginMember", loginVO);
			return "success";
		}else{
			return "fail";
		}
		
		
	}

}
