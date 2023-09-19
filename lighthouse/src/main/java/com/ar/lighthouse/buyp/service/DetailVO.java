package com.ar.lighthouse.buyp.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.OrdersVO;

import lombok.Data;
@Data
public class DetailVO {
	private int orderDetailCode;
	private String productName;
	private String requestedTerm;
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
	private String deliveryCompany;
	private int deliveryNumber;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deliveryDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deliveryStart;
	
	OptionVO option;
	
	
}
