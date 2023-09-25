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
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date chargeDate;
	
	   // 멤버 테이블
		private String memberId;
		private String memberName;
		private int memberTel;
		   
		   //상품 테이블 조인
		   private String orderStatus;
		   private String productName;
		   private String productCode;
	
//  ajax넘어오는값
	   String searchValue;
	   String searchKey;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date fromDate;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date toDate;
	   
//	   returnVO
		String returnCode;
		String returnReason;
		
		@DateTimeFormat(pattern="yyyy-MM-dd")
		Date returnRegdate;
		
		@DateTimeFormat(pattern="yyyy-MM-dd")
		Date returnRetractDate;
		
		String returnStatus;
		
		@DateTimeFormat(pattern="yyyy-MM-dd")
		Date returnProcessingDate;
		
		
		
		
}
