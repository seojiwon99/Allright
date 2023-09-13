package com.ar.lighthouse.cart.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {
	
	private int cartCode;
	private int cartCount;
	
	private String memberId;
	
	//옵션 조인해서 받은 값
	private int salePrice;
	private String optionName;
	private String optionValue;
	
	//상품명 조인해서 받은 값
	private String productName; 
	private int productCost;
	private int deliveryCost;
	
	//상품 이미지 조인해서 받은 값
	private String imgName;
	
	
	
}
