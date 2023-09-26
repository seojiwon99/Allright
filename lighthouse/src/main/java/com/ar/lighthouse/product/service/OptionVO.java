package com.ar.lighthouse.product.service;

import java.util.List;

import lombok.Data;

@Data
public class OptionVO {

	private int optionCode;
	String productCode;
	String optionName;
	String optionValue;

	List<OptionDetailVO> detailVO;
}

