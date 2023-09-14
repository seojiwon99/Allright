package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TradeVO {
	
	//취소
	private String cancelCode;
	private int orderDetailCode;
	private String cancelReason;
	private String cancelRegdate;
	private String cancelRetractDate;
	private String cancelStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cancelDate;
	
	//반품
	private String returnCode;
	private String returnReason;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnRetractdate;
	private String returnStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnProcessdate;
	
	//교환
	private String exchangeCode;
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
	
	//주문상태
	private int orderStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	private int orderCnt;
	private int paymentPrice;
	private String productName;
	
	//상품 테이블 조인
	private String productCode;
	
	//이미지 테이블 조인
	private int imgCode;
	private String imgContent;
	private String imgName;
	private String uploadName;
	private int imgOrder;

	

	
}
