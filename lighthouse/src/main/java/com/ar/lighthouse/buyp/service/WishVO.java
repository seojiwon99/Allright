package com.ar.lighthouse.buyp.service;


import lombok.Data;

@Data
public class WishVO {

	private int favoriteCode;
	private String memberId;
	private String productCode;
	
	private String productName;
	private String productContent;
	private int productCost;
	private int salePrice;
	
	private String imgName;
	private String uploadName;
	private String uploadPath;
	private String imgContent;
	
}
