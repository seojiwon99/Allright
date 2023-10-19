package com.ar.lighthouse.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.member.function.ranNumber;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

/*
 * 개발자 : 염유준 
 * 개발일자 : 2023/09/14
 * 			회원 문자 전송 관리
 * 
 */

@Controller
public class SMSController {
	final DefaultMessageService messageService;

    public SMSController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSA1HTLHMQOVDGH", "XIGHXNGHNAD0OSWRRQAPVG0PNY7JWOXK", "https://api.coolsms.co.kr");
    }
    @PostMapping("/page/member/send-one")
    @ResponseBody
    public String sendOne(String phone) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01036095465");
        message.setTo(phone);
        ranNumber ran = new ranNumber();
        String sendRanNum = ran.randomNumber(); // 6자리 숫자 String 저장
        String sendMsg = "All Right 휴대폰 인증입니다. [" + sendRanNum + "]";
        message.setText(sendMsg);
        
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return sendRanNum;
    }
    
    @PostMapping("/page/member/seller-one")
    @ResponseBody
    public String sendTwo(String phone, @RequestParam String productName) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01036095465");
        message.setTo(phone);
        System.out.println("@@@@@@@@@@@@" + productName + phone);
        phone = phone.replaceAll("-", "");
        String sendMsg = "All Right 쇼핑몰입니다. 주문하신 " + productName + " 상품이 발송되었습니다.";
        message.setText(sendMsg);
        
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return "성공";
    }
}
