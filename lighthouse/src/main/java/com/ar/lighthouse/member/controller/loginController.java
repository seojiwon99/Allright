package com.ar.lighthouse.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;


/*
 * 개발자 : 염유준 
 * 개발일자 : 2023/09/14
 * 			회원관리
 * 
 */

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
	public void joinSellerForm() {
		
	}
	@GetMapping("page/member/joinBuyerForm")
	public void joinBuyerForm() {
		
	}
	//id 중복체크
	@GetMapping("page/member/idCheck")
	@ResponseBody
	public int idCheck(String memberId) {
		System.out.println(memberId);
		return memberService.getMemberCheck(memberId);
	}
	@GetMapping("page/member/findMember")
	public void findMember() {
		
	}
	@GetMapping("page/member/findPassword")
	public void findPassword() {
		
	}
	@PostMapping("page/member/findPassword")
	public String findPassword(@RequestParam(name="memberId") String memberId,
			@RequestParam(name="memberAuthor") int memberAuthor,
			Model model) {
		int size = memberId.length();

		for(int i=size/2; i<size; i++) {
			memberId = memberId.replace(memberId.charAt(i), '*');
		}
		model.addAttribute("id",memberId);
		model.addAttribute("author",memberAuthor);
		return "page/member/findPassword";
	}
	
	
	//로그인
	@PostMapping("page/member/login")
	@ResponseBody
	public String login(@RequestBody MemberVO memberVO, HttpServletRequest request) {
		//
		
		System.out.println(memberVO);
		MemberVO loginVO = memberService.memberLogin(memberVO);
		System.out.println("loginVO = " + loginVO);
		if(loginVO.getMemberId() == null) {
			return "fail";
		}
		if(passwordEncoder.matches(memberVO.getMemberPw(), loginVO.getMemberPw())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginVO);
			return "success";
		}else{
			return "fail";
		}
	}
	//로그아웃
	@PostMapping("logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	//비밀번호 수정 폼
	@PostMapping("page/member/editPasswordForm")
	public String editPasswordForm(@RequestParam(name="memberId") String memberId, Model model) {
		model.addAttribute("memberId",memberId);
		return "page/member/editPasswordForm";
	}
	
	@PostMapping("page/member/editPassword")
	@ResponseBody
	public boolean editPassword(@RequestBody MemberVO memberVO) {
		System.out.println("edit" + memberVO);
		memberVO.setMemberPw(passwordEncoder.encode(memberVO.getMemberPw()));
		
		return memberService.editMemberPassword(memberVO);
		
		
	}
	@PostMapping("page/member/AjaxJoin")
	@ResponseBody
	public String memberJoin(@RequestBody MemberVO memberVO) {
		
		memberVO.setMemberPw(passwordEncoder.encode(memberVO.getMemberPw()));
		System.out.println(memberVO.getMemberPw());
		
		String result = "";
		if(memberService.addMember(memberVO)>0) {
			result = "success";
		}else {
			result = "fail";
		}
		return result;
	}

}
