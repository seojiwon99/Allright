package com.ar.lighthouse.productinquiry.service;

import java.util.List;

import com.ar.lighthouse.review.service.ReviewVO;

public interface ProductInquiryService {

	// 문의 사항 조회
	public List<ProductInquiryVO> getInquiryList(ProductInquiryVO productInquiryVO);
	
	// 문의 사항 수
	public int countGetInquiry(ProductInquiryVO productInquiryVO); 

	// 문의 사항 등록
	public void addInquiry(ProductInquiryVO inquiryVO);

	// 문의 사항 수정
	public boolean editInquiry(ProductInquiryVO productInquiryVO);

	// 문의 사항 삭제
	public boolean removeInquiry(int queCode);
}
