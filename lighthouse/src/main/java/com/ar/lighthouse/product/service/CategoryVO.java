package com.ar.lighthouse.product.service;

import lombok.Data;

@Data
public class CategoryVO {
	String categoryCode;
	String parentCategoryCode;
	String categoryName;
	int categoryLevel;
}
