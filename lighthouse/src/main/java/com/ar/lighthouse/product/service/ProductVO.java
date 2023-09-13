package com.ar.lighthouse.product.service;


import java.util.List;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	 	int deliveryCost;
	 	int returnCost;
	 	int exchangeCost;
	 	String deliveryService;

	 	String productStatus;
	 	List<ProductImgVO> productImg;

	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	Date productRegdate;
	 	
	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	Date productUpdatedate;
	 	int productCost;
	 	int salePrice;
	 	
	 	List<OptionVO> option;
	 	
	 	
}
