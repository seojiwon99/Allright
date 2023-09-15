package com.ar.lighthouse.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.product.service.ImgsVO;
import com.ar.lighthouse.review.service.ReviewVO;

@Mapper
public interface ReviewMapper {

	//리뷰 전체조회
	public List<ReviewVO> selectReviewList(ReviewVO reviewVO);
	
	//리뷰등록
	public void insertReview(ReviewVO reviewVO);
	
	//리뷰 이미지등록
	public void insertReviewImg(ImgsVO imgsVo);
	
	//리뷰 삭제
	public int deleteReview(String memberId); 
	
}
