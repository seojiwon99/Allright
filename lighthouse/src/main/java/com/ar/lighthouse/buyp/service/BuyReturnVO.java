package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BuyReturnVO {

	private String returnCode;

	private String reCode;

	private int orderDetailCode;
	private String returnReason;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRetractdate;
	private String returnStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date returnProcessdate;

	private int orderCode;

	private int orderCnt;
	private int paymentPrice;
	
	private int optionCode;
	
	private String productName;

	
	private String memberId;
	
	private String uploadPath;
	private String uploadName;
	private String imgContent;
	private String imgOrder;

}
