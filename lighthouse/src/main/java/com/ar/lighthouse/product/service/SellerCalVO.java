package com.ar.lighthouse.product.service;

import java.util.Date;

import lombok.Data;

@Data
public class SellerCalVO {
	int calCode;
	int orderDetailCode;
	
	int fee;
	int paymentPrice; // 결제금액
	int settlementAmount; // 정산금액
	Date settlementDate; // 정산예정일
	Date completDate; // 정선완료일
	int permission; // 수수료
	// productVO
	String productName;
	
	// memberVO
	String memberName;
	
	// DetailVO
	int orderStatus;
	
}
