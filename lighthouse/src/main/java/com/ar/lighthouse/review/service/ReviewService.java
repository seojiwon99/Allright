package com.ar.lighthouse.review.service;

import java.util.List;

import com.ar.lighthouse.common.ImgsVO;

public interface ReviewService {

	// 조회
	public List<ReviewVO> getReviewList(ReviewVO reviewVO);
	
	// 리뷰 수 조회
	public List<ReviewVO> countGetReview(ReviewVO reviewVO); 
	
	//등록
	public void addReview(ReviewVO reviewVO);
	
	//리뷰이미지등록
	public void addReviewImg(ImgsVO imgsVO);
	
	//리뷰 수정
	public boolean editReview(ReviewVO reviewVO);
	
	//리뷰 삭제
	public boolean removeReview(int reviewCode);
}
