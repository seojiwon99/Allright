package com.ar.lighthouse.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.mail.RegisterMail;
import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;
/*
 * 개발자 : 염유준 
 * 개발일자 : 2023/09/14
 * 			회원 메일 전송 관리
 * 
 */


@Controller
public class MailController {
	
    @Autowired
    RegisterMail registerMail;
    
	@Autowired
	MemberService memberService;
	

    @PostMapping("/page/member/login/mailConfirm")
    @ResponseBody
    public Map<String,Object> mailConfirm(@RequestParam(name = "email") String email, @RequestParam(name="memberAuthor") int memberAuthor) throws Exception{
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한  인증코드 ==> " + code);
        MemberVO memberVO = new MemberVO();
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberAuthor > 0) {
        	memberVO.setMemberEmail(email);
        	memberVO.setMemberAuthor(memberAuthor);
        	memberVO = memberService.getMemberEmailCheck(memberVO);
        	map.put(code, memberVO);
        	System.out.println(memberVO);
        	return map;
        }else {
        	map.put("key", code);
        	return map;
        }
    }
    
    @PostMapping("/page/member/login/mailConfirmJoin")
    @ResponseBody
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
    
    @PostMapping("/page/member/login/mailPwConfirm")
    @ResponseBody
    public Map<String,Object> mailPwConfirm(
    		@RequestParam(name = "email") String email,
    		@RequestParam(name="memberAuthor") int memberAuthor,
    		@RequestParam(name="memberId") String memberId) throws Exception{
    	
        
        MemberVO memberVO = new MemberVO();
        Map<String, Object> map = new HashMap<String, Object>();
        
    	memberVO.setMemberEmail(email);
    	memberVO.setMemberAuthor(memberAuthor);
    	memberVO.setMemberId(memberId);
    	if(memberService.getMemberCrossCheck(memberVO)<1) {
    		map.put("result", "fail");
    		return map;
    	}else {
    	String code = registerMail.sendSimpleMessage(email);
    	System.out.println("사용자에게 발송한  인증코드 ==> " + code);
    	
    	map.put(code, memberVO);
    	System.out.println(memberVO);
    	return map;
    	}
    }
    
}
