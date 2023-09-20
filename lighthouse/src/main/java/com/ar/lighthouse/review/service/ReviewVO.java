package com.ar.lighthouse.review.service;

import java.util.Date;

import com.ar.lighthouse.common.ImgsVO;

import lombok.Data;

@Data
public class ReviewVO {

	int reviewCode;
	String memberId;
	String productCode;
	String reviewContent;
	int reviewStar;
	Date reviewRegdate;
	ImgsVO reviewImg;
}
