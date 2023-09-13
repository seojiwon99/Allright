package com.ar.lighthouse.product.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OptionVO {
	private Long optionCode;
	   private String productCode;
	   private int optionOrder;
	   private String optionName;
	   private String optionValue;
	   private int optionPrice;
	   private String optionSellStatus;
	   private String optionExStatus;
	   private int optionCount;
	   private int minOrder;
	   private int maxOrder;
	   private int salePrice;
	   
}
