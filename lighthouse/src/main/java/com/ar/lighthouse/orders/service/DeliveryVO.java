package com.ar.lighthouse.orders.service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryVO {
	
	private int orderCode; //주문코드
	private String memberId; // 아이디
	private Date orderDate; // 주문날짜
	private Date chargeDate; // 결제날짜
	private int orderPrice; //총결제금액
	private String orderAddr; // 배송지주소
	private String orderDetailsAddr; // 배송지 상세주소
	private String recipientName; // 받는 사람
	private String recipientTel; // 받는사람 번호
	private String requestedTerm; // 요청사항
	
	
}
