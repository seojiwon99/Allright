package com.ar.lighthouse.buyp.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.product.service.OptionVO;

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
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date deliveryDate;
   
   //상품 테이블 조인
   private String productName;
   private String productCode;
   
   //이미지 테이블 조인
   private int imgCode;
   private String imgContent;
   private String imgName;
   private String uploadName;
   private int imgOrder;
   
   
}
