package com.ar.lighthouse.orders.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderChkVO {

	private int orderCode;
	private int orderDetailCode;
	private String cancelReason;
	private String refundTypecode;
	private String refundType;
	private String memberId;
}
