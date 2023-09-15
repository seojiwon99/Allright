package com.ar.lighthouse.review.service;

import java.util.List;

public interface ReviewService {

	// 조회
	public List<ReviewVO> getReviewList(ReviewVO reviewVO);
	
	public void addReview(ReviewVO reviewVO);
	
	public void addReviewImg(ReviewImgVO reviewImgVO);
}
