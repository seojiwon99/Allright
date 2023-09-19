package com.ar.lighthouse.buyp.service;

import java.util.List;

import lombok.Data;

@Data
public class WishVO {

	private int favorite_code;
	private String memberId;
	private String productCode;
	
	private String productName;
	private String productContent;
	private int productCost;
	private int salePrice;
	
	private String imgName;
	private String uploadName;
	private String imgContent;
	
}
