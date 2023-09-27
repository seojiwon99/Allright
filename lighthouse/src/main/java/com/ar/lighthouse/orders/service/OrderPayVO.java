package com.ar.lighthouse.orders.service;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayVO {
	
	//쿠폰 사용 여부 확인 위한 번호, 할인상품확인
	private List<OrderPayVO> orderList = new ArrayList<OrderPayVO>();
	private int mycouponCode;
	private String productCode;
	private int productSalePrice;
	private int couponPrice;
	private int optionDetailCode; 
	private int deliveryCost;
}
