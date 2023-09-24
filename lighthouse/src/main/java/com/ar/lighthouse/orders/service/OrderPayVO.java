package com.ar.lighthouse.orders.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayVO {
	
	//쿠폰 사용 여부 확인 위한 번호, 할인상품확인
	private int mycouponCode;
	private String productCode;
	private int productSalePrice;
	private int couponPrice;
}
