package com.ar.lighthouse.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.buyp.service.DetailVO;

import lombok.Data;

@Data
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
	   
	   List<DetailVO> orderDetail;
	  
	   //MemberVO
	   private String memberId;
	   private String memberName;
	   private String memberAddr;
	   private String memberDetailsAddr;
	   private String memberEmail;
	   private String memberTel;
	   
	   //CartVO
	   private int cartCount;
	   private int cartCode;
	   
	   //OptionVO
	   private String optionName;
	   private String optionValue;
	   private int optionPrice;
	   
	   //ProductSVO
	   private String productName;
	   private int productCost;
	   private int salePrice;
	   
	   //ImegsVO
	   private String uploadName;
	   
	   //CouponVO
	   private String couponName;
	   
	
}
