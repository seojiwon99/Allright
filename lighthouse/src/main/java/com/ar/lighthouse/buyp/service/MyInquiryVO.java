package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MyInquiryVO {

	private int customInquiryCode;
	private String customInquiryTitle;
	private String customInquiryContent;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date customInquiryRegdate;
	private String customInquiryAnswer;
	private String customInquiryAnswerStatus;
	
//	member
	String memberId;
}
