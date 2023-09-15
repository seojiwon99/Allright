package com.ar.lighthouse.product.service;

import java.util.Date;

import lombok.Data;

@Data
public class ImgsVO {

	
	int imgCode;
	String parentCode;
	int imgOrder;
	String imgName;
	String uploadName;
	String imgContent;
	Date imgRegdate;
	String imgFromtable;
	String productCode;
	String reviewCode;

}
