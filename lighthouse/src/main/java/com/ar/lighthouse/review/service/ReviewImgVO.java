package com.ar.lighthouse.review.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewImgVO {
	int reviewImgCode;
	int reviewCode;
	int imgOrder;
	String imgName;
	String uploadName;
	String imgContent;
	Date imgRegdate;
}
