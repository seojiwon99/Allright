package com.ar.lighthouse.product.service;

import lombok.Data;

@Data
public class ProductVO {
	 	String productCode;
	 	String memberId;
	 	String categoryCode;
	 	String productName;
	 	String productContent;
	 	String productLocation;
	 	String productOrigin;
	 	String productMakeorigin;
	 	String deliveryCost;
	 	String returnCost;
	 	String exchangeCost;
	 	String deliveryService;
	 	String productStatus;
	 	String productRegdate;
	 	String productUpdatedate;
	 	
	 	int optionCode;
	 	int optionOrder;
	 	String optionName;
	 	String optionValue;
	 	int optionPrice;
	 	boolean optionSalesStatus;
	 	boolean optionExStatus;
	 	int optionCount;
	 	int minOrder;
	 	int maxOrder;
	 	int salePrice;
}
