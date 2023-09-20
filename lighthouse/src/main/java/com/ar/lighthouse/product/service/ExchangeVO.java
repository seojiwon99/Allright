package com.ar.lighthouse.product.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExchangeVO {
	String exchangeCode;
	long orderDetailCode;
	String exchangeReason;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date exchangeRegdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date exchangeRetractDate;
	
	String exchangeStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date exchangeProcessingDate;
	
	String exchangeAddr;
	long DeliveryNumber;
	
	String productName;
	String orderStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date chargeDate;
	
	String memberName;
}
