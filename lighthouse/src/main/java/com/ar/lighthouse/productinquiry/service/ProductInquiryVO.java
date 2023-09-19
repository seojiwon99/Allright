package com.ar.lighthouse.productinquiry.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProductInquiryVO {

	int queCode;
	String productCode;
	String queContent;
	String memberId;
	String queSecret;
	Date queRegdate;
	Date queUpdatedate;
	String queAns;
	String sellerId;
}
