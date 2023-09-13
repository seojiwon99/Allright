package com.ar.lighthouse.customsvc.service;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryVO {
	private int customInquiryCode;
	private String memberId;
	private String customInquiryTitle;
	private String customInquiryContent;
	private Date customInquiryRegdate;
	private String customInquiryAnswer;
	private String customInquiryAnswerStatus;
	
	private int inqCode;
}
