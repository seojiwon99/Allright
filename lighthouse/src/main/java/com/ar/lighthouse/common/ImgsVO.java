package com.ar.lighthouse.common;

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
	String uploadPath;
	
	String productCode;
	int reviewCode;

}
