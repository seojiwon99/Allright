package com.ar.lighthouse.product.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public interface sellerMailServiceInter {
    
    // 메일 내용 작성 
    MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;

    // 랜덤 인증코드 생성
    String createKey();
    
    // 메일 발송
    String sendSimpleMessage(String to) throws Exception;
    
    
    MimeMessage productDelMessage(String to, String reason) throws MessagingException, UnsupportedEncodingException;
    //판매자에게 상품 삭제 메일
    String sendDelMessage(String to, String reason) throws Exception;

}