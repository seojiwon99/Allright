package com.ar.lighthouse.product.service;

import java.util.Date;

import lombok.Data;

@Data
public class CancelVO {
	String cancelCode;
	long orderDetailCode;
	String cancelReason;
	Date cancelRegdate;
	Date cancelRetractDate;
	String cancelStatus;
	Date cancelDate;
	String cancelRejectionReason;
	
}
