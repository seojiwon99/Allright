package com.ar.lighthouse.orders.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdersVO {
	
	private int orderCode;
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date orderDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chargeDate;
	private int orderPrice;
	private String orderAddr;
	private String orderDetailsAddr;
	private String recipientName;
	private String recipientTel;
	private String requestedTerm;
	
	
	
	//MemberVO
	private String memberId;
	private String memberName;
	private String memberAddr;
	private String memberDetailsAddr;
	private String memberEmail;
	private String memberTel;
	
	//CartVO
	private int cartCount;
	private int[] cartCode;
	
	//OptionVO
	private String optionName;
	private String optionValue;
	private int optionPrice;
	
	//ProductSVO
	private String productName;
	private int productCost;
	private int salePrice;
	private int deliveryCost;
	
	//ImegsVO
	private String uploadName;
	
	//CouponsVO
	private int couponCode;
	private String couponName;
	private String couponContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date couponDeadline;
	private String couponBase;
	private int couponMinPrice;
	private int couponMaxPrice;
	private int couponDiscountPrice;
	private int couponDiscountRate;
	
	//CouponBox
	private int mycouponCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date usingDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String couponUse;
	
	
	
	
	
}
