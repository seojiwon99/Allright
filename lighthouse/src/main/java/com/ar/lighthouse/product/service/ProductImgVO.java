package com.ar.lighthouse.product.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProductImgVO {

	int imgCode;
	String productCode;
	int imgOrder;
	String imgName;
	String uploadName;
	String imgContent;
	Date imgRegdate;

}
