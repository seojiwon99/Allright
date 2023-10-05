package com.ar.lighthouse.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.review.service.ReviewVO;

@Mapper
public interface ReviewMapper {

	// 리뷰 전체조회
	public List<ReviewVO> selectReviewList(@Param("reviewVO") ReviewVO reviewVO, @Param("cri") Criteria cri);

	// 리뷰 수
	public int countReview(ReviewVO reviewVO);

	// 리뷰 별점 평균
	public int reviewStarAvg(ReviewVO reviewVO);

	// 리뷰등록
	public void insertReview(ReviewVO reviewVO);

	// 리뷰 이미지등록
	public void insertReviewImg(ImgsVO imgsVo);

	// 리뷰 수정
	public int updateReview(ReviewVO reviewVO);

	// 리뷰 수정 이미지
	public int updateReviewImg(ImgsVO imgsVO);

	// 리뷰 삭제
	public int deleteReview(int reviewCode);
	
	//리뷰 이미지 삭제
	public int reviewImgDelete(ImgsVO imgsVO);

	// 리뷰 신고 코드 리스트
	public List<CodeVO> reviewDeclareCode(CodeVO codeVO);

	// 리뷰신고
	public void reviewDeclare(DeclareVO declareVO);

}
