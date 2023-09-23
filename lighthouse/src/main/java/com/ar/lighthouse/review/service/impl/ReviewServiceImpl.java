package com.ar.lighthouse.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.review.mapper.ReviewMapper;
import com.ar.lighthouse.review.service.ReviewImgVO;
import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewMapper mapper;

	@Override
	public List<ReviewVO> getReviewList(ReviewVO reviewVO) {
		return mapper.selectReviewList(reviewVO);
	}

	@Override
	public void addReview(ReviewVO reviewVO) {
		mapper.insertReview(reviewVO);
	}

	@Override
	public void addReviewImg(ImgsVO imgsVO) {
		mapper.insertReviewImg(imgsVO);
	}

	@Override
	public boolean removeReview(int reviewCode) {
		return mapper.deleteReview(reviewCode) == 1;
	}

	@Override
	public List<ReviewVO> countGetReview(ReviewVO reviewVO) {
		return mapper.countReview(reviewVO);
	}

	@Override
	public boolean editReview(ReviewVO reviewVO) {
		return mapper.updateReview(reviewVO) == 1;
	}

}
