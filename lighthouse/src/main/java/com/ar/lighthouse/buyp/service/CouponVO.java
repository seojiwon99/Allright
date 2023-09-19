package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CouponVO {

   //쿠폰보관함
   private int mycouponCode;
   private int couponCode;
   private String memberId;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date issueDate;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date usingDate;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date endDate;
   
   
   private String couponName;
   private String couponContent;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date couponDeadline;
   private String couponCondition;
   private int couponMinPrice;
   private int couponMaxPrice;
   private int couponDiscountPrice;
   private int couponDiscountRate;
   
   
}