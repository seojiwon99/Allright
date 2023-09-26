package com.ar.lighthouse.product.service;

import lombok.Data;

@Data
public class OptionDetailVO {

	private Long optionCode;
	private String productCode;
	private Integer optionOrder;
	private String optionLast;
	private Integer optionPrice;
	private String optionSellStatus;
	private String optionExStatus;
	private Integer optionCount;
	private Integer minOrder;
	private Integer MaxOrder;
}
