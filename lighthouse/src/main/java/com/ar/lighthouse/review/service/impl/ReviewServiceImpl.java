package com.ar.lighthouse.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void addReviewImg(ReviewImgVO reviewImgVO) {
		mapper.insertReviewImg(reviewImgVO);
	}

	@Override
	public boolean removeReview(String memberId) {
		return mapper.deleteReview(memberId) == 1;
	}

}
