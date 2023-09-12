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
	private String memberId;
	private Long optionCode;
	private int cartCount;
	
	
}
