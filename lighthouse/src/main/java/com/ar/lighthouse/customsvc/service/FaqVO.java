package com.ar.lighthouse.customsvc.service;


import lombok.Data;

@Data
public class FaqVO {
	
	private int faqCode;
	private String questionTitle;
	private String questionAnswer;
	private Integer faqType;
	private String typeName;
	
	
	
}
