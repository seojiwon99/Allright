package com.ar.lighthouse.product.service;

import java.util.List;

import lombok.Data;

@Data
public class OptionVO {

	Long optionCode;
	String productCode;
	String optionName;
	String optionValue;

	String optionCount;

	List<OptionDetailVO> detailVO;
}