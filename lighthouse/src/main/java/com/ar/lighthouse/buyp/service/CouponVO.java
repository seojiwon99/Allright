package com.ar.lighthouse.buyp.service;

import java.util.Date;

import lombok.Data;

@Data
public class CouponVO {

	//쿠폰보관함
	private int mycouponCode;
	private int couponCode;
	private String memberId;
	private Date issueDate;
	private Date usingDate;
	private Date endDate;
	
	
	private String couponName;
	private String couponContent;
	private Date couponDeadline;
	private String couponCondition;
	private int couponMinPrice;
	private int couponMaxPrice;
	private int couponDiscountPrice;
	private int couponDiscountRate;
	
	
}
