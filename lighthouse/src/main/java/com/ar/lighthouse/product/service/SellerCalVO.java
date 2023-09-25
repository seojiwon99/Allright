package com.ar.lighthouse.product.service;

import java.util.Date;

import lombok.Data;

@Data
public class SellerCalVO {
	int calCode;
	int orderDetailCode;
	
	int fee;
	int paymentPrice;
	int settlementAmount;
	Date settlementDate;
	Date completDate;
	int myPrice; // 수수료
	int yourPrice; // 정산금액
	// productVO
	String productName;
	
	// memberVO
	String memberName;
	
	// DetailVO
	int orderStatus;
	
}
