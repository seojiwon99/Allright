package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class DetailVO {
	private int orderDetailCode;
	private int orderCode;
	private int optionCode;
	private int orderCnt;
	private int orderPrice;
	private int discountPrice;
	private int paymentPrice;
	private int orderStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	private int mycouponCode;
	private String optionCouponCheck;
	private int deliveryNumber;
	private Date deliveryDate;
}
