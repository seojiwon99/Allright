package com.ar.lighthouse.productinquiry.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductInquiryVO {

	int queCode;
	String productCode;
	String queContent;
	String memberId;
	String queSecret;
	Date queRegdate;
	Date queUpdatedate;
	String queAns;
	String sellerId;
	
	// 상품테이블
	String productName;
	
//	 멤버테이블
	String memberName;
	
	//   ajax넘어오는값
	   String searchValue;
	   String searchKey;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date fromDate;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   Date toDate;
	
}
