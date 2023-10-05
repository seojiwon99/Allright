package com.ar.lighthouse.buyp.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.product.service.OptionDetailVO;
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
	   private String orderStatus;
	   private String orderStatusNm;
	   private String orderCheck;
	   
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   private Date orderDate;
	   
	   private int mycouponCode;
	   private String optionCouponCheck;
	   private String deliveryService;
	   private String deliveryNumber;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   private Date deliveryDate;
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   private Date deliveryStart;
   
   
   // 멤버 테이블
   String memberId;
   String memberName;
   int memberTel;
   String memberEmail;

   //상품 테이블 조인
   private String productName;
   private String productCode;
   private int salePrice;
   
   // 주문 테이블 조인
   private String requestedTerm;
   
   //이미지 테이블 조인
   private int imgCode;
   private String imgContent;
   private String imgName;
   private String uploadName;
   private int imgOrder;
   
   
// ajax넘어오는값
 String searchValue;
 String searchKey;
 
 @DateTimeFormat(pattern="yyyy-MM-dd")
 Date fromDate;
 
 @DateTimeFormat(pattern="yyyy-MM-dd")
 Date toDate;
 int returnCnt;
 int cancelCnt;
 int totalPrice;
 int month;
 int year;
 int totalCount;
 int day_count;
   //취소,반품,교환 
   
  
   List<BuyCancelVO> BuyCancel;
   private String cancelStatus;
   private String cancelStatusNm;
   private Date cancelRegdate;
   
   List<BuyReturnVO> BuyReturn;
   private String returnStatus;
   private String returnStatusNm;
   private Date returnRegdate;
   
   List<BuyExchangeVO> BuyExchange;
   private String exchangeStatus;
   private String exchangeStatusNm;
   private Date exchangeRegdate;
   
   // 옵션 VO
   OptionVO option;
   private String optionLast;
   List<OptionDetailVO> optionDetail;
}
