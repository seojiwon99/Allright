package com.ar.lighthouse.admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class ProductDetailVO {
	
 	private String productCode;
 	private String memberId;
 	private String categoryCode;
 	private String productName;
 	private String productContent;
 	private String productLocation;
 	private String productOrigin;
 	private String productMakeorigin;
 	private int deliveryCost;
 	private int returnCost;
 	private int exchangeCost;
 	private String deliveryService;
 	private String productBrand;
 	private String productStatus;
 	private String productExStatus;

 	@DateTimeFormat(pattern="yyyy-MM-dd")
 	private Date productRegdate;
 	
 	@DateTimeFormat(pattern="yyyy-MM-dd")
 	private Date productUpdatedate;
 	
 	private int productCost;
 	private int salePrice;
 	
 	private String memberTel;
 	private String businessNumber;
 	private String memberEmail;
}
