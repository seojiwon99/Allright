package com.ar.lighthouse.orders.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundVO {

	private String refundCode;
	private String refundType;
	private String refundTypecode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	private String refundMethod;
	private int refundAmount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date completeDate;
	private String paymentKey;
	
	//orderDetailVO
	private int discountPrice;
	private int orderPrice;
	private int paymentPrice;
	
	//couponboxVO
	private int mycouponCode;
}
