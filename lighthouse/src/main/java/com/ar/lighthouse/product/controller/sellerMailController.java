package com.ar.lighthouse.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;
/*
 * 개발자 : 홍규연 
 * 			회원 메일 전송 관리
 * 
 */
import com.ar.lighthouse.product.mail.sellerRegisterMail;


@Controller
public class sellerMailController {
	
    @Autowired
    sellerRegisterMail registerMail;
    
	@Autowired
	MemberService memberService;
	

    @PostMapping("seller/sellerMailConfirm")
    @ResponseBody
    public String sellerMailConfirm(@RequestBody List<MemberVO> memberInfoList) {
    	for (MemberVO memberInfo : memberInfoList) {
            String email = memberInfo.getMemberEmail();
            
            try {
				String code = registerMail.sendSimpleMessage(email);
				System.out.println("발송할 이메일 =>" + code);
			} catch (Exception e) {
				e.printStackTrace();
			}
            System.out.println(email);
        }

        return "작업이 완료되었습니다.";
    }
    

	/*
	 * @PostMapping("/page/member/login/sellerMailConfirmJoin")
	 * 
	 * @ResponseBody public String mailConfirm(@RequestBody MemberVO memberVO)
	 * throws Exception{ String code =
	 * registerMail.sendSimpleMessage(memberVO.getMemberEmail());
	 * System.out.println("사용자에게 발송한 인증코드 ==> " + code);
	 * 
	 * return code; }
	 */
    @PostMapping("/page/member/login/sellerMailPwConfirm")
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
