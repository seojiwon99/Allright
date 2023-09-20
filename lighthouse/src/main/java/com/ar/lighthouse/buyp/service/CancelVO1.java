package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CancelVO1 {
	
	private String cancelCode;
	private int orderDetailCode;
	private String cancelReason;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cancelRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cancelRetractDate;
	private String cancelStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cancelDate;
	private String cancelRejectionCode;
	
	private String cancelRejectionReason;
	private String cDetailReason;
	
	private int orderCnt;
	private int paymentPrice;
	
	private int optionCode;
	
	private String productName;
}
