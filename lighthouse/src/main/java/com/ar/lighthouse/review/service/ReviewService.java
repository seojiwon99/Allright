package com.ar.lighthouse.review.service;

import java.util.List;

public interface ReviewService {

	// 조회
	public List<ReviewVO> getReviewList(ReviewVO reviewVO);
	
	//등록
	public void addReview(ReviewVO reviewVO);
	
	//리뷰이미지등록
	public void addReviewImg(ReviewImgVO reviewImgVO);
	
	//리뷰 삭제
	public boolean removeReview(String memberId);
}
