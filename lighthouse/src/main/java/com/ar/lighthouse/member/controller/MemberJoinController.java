package com.ar.lighthouse.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class MemberJoinController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/page/member/AjaxJoin")
	@ResponseBody
	public int memberJoin(@RequestBody MemberVO memberVO) {
		System.out.println(memberVO);
		return memberService.MemberJoin(memberVO);
	}
}
