package com.ar.lighthouse.productinquiry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.productinquiry.mapper.ProductInquiryMapper;
import com.ar.lighthouse.productinquiry.service.ProductInquiryService;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;
import com.ar.lighthouse.review.service.ReviewVO;

@Service
public class ProductInquiryServiceImpl implements ProductInquiryService {

	@Autowired
	ProductInquiryMapper mapper;

	@Override
	public List<ProductInquiryVO> getInquiryList(ProductInquiryVO productInquiryVO, Criteria cri) {
		return mapper.selectInquiryList(productInquiryVO, cri);
	}

	// 등록
	@Override
	public void addInquiry(ProductInquiryVO inquiryVO) {
		mapper.insertInquiry(inquiryVO);
	}

	// 삭제
	@Override
	public boolean removeInquiry(int queCode) {

		return mapper.deleteInquiry(queCode) == 1;
	}

	// 수정
	@Override
	public boolean editInquiry(ProductInquiryVO productInquiryVO) {
		return mapper.updateInquiry(productInquiryVO) == 1;
	}

	@Override
	public int countGetInquiry(ProductInquiryVO productInquiryVO) {
		return mapper.inquiryCount(productInquiryVO);
	}

	@Override
	public List<ProductInquiryVO> qnaGetInq(ProductInquiryVO productInquiryVO) {
		return mapper.selectInquiryListN(productInquiryVO);
	}

}
