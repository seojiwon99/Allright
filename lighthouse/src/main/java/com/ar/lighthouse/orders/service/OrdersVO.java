package com.ar.lighthouse.orders.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.ImgsVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductVO;

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
	
	//CouponVO
	private String couponName;
	
	
	
	
}
