package com.ar.lighthouse.product.service;


import java.util.List;

import com.ar.lighthouse.buyp.service.DetailVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OptionDetailVO {
	private Long optionCode;
	   private String productCode;
	   private int optionOrder;
	   private String optionName;
	   private String optionValue;
	   private int optionPrice;
	   private String optionSellStatus;
	   private String optionExStatus;
	   private int optionCount;
	   private int salePrice;
	   
	   private String optionLast;
	   
	
}
