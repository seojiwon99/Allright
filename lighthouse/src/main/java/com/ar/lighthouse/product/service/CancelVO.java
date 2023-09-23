package com.ar.lighthouse.product.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.buyp.service.DetailVO;

import lombok.Data;

@Data
public class CancelVO {
	String cancelCode;
	long orderDetailCode;
	String cancelReason;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelRegdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelRetractDate;
	
	String cancelStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date cancelDate;
	String cancelRejectionReason;
	String productName;
	String orderStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date chargeDate;
	
	String memberName;
}
