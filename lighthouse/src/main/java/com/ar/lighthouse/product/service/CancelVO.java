package com.ar.lighthouse.product.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.buyp.service.DetailVO;

import lombok.Data;

@Data
public class CancelVO {
	String cancelCode;
	long orderDetailCode;
	String cancelReason;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelRegdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelRetractDate;
	
	String cancelStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelDate;
	String cancelRejectionReason;

	
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
	   
	   
	//   ajax넘어오는값
	   String searchValue;
	   String searchKey;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date fromDate;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date toDate;
}
