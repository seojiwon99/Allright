package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExchangeVO {
	
	private String exchangeCode;
	private int orderDetailCode;
	private String exchangeReason;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exchangeRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exchangeRetractdate;
	private String exchangeStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exchangeProcessingdate;
	private String exchangeAddr;
	private int deliveryNumber;
	private int orderCode;
	
	private int orderCnt;
	private int paymentPrice;
	
	private int optionCode;
	
	private String productName;
	
	private String memberId;
}
