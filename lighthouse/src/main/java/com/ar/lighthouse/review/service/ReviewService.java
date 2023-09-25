package com.ar.lighthouse.review.service;

import java.util.List;

import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.common.CodeVO;
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
	
	//리뷰 이미지 수정
	public boolean editReviewImg(ImgsVO imgsVO);
	
	//리뷰 삭제
	public boolean removeReview(int reviewCode);

	
	//리뷰신고코드
	public List<CodeVO> reviewCodeList(CodeVO codeVO);
	
	//리뷰신고
	public void addReviewDeclare(DeclareVO declareVO);
}
