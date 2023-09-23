package com.ar.lighthouse.orders.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditVO {
	
	private String paymentKey;
	private String type;
	private String orderId;
	private String orderName;
	private String method;
	private String currency;
	private String mId;
	private Long amount;
	private String paymentType;
	private Long balanceAmount;
	private Long totalAmount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvedAt;

}
