package com.ar.lighthouse.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.mail.RegisterMail;


@Controller
public class MailController {
	
    @Autowired
    RegisterMail registerMail;

    @PostMapping("/page/member/login/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
}
