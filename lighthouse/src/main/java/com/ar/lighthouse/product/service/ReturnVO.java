package com.ar.lighthouse.product.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReturnVO {
	String returnCode;
	long orderDetailCode;
	String returnReason;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date returnRegdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date returnRetractDate;
	
	String returnStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date returnProcessingDate;
	
	
	String productName;
	String orderStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date chargeDate;
	
	String memberName;
}
