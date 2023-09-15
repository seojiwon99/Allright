package com.ar.lighthouse.review.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {

	int reviewCode;
	String memberId;
	String productCode;
	String reviewContent;
	int reviewStar;
	Date reviewRegdate;
}
