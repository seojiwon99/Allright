package com.ar.lighthouse.buyp.service;

import java.util.Date;

import lombok.Data;

@Data
public class MyInquiryVO {

	private int customInquiryCode;
	private String customInquiryTitle;
	private String customInquiryContent;
	private Date customInquiryRegdate;
	private String customInquiryAnswer;
	private String customInquiryAnswerStatus;
}
