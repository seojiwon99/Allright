package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReturnVO1 {

	private String returnCode;
	private int orderDetailCode;
	private String returnReason;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRetractdate;
	private String returnStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date returnProcessdate;
	
	
	private int orderCnt;
	private int paymentPrice;
	
	private int optionCode;
	
	private String productName;
}
