package com.ar.lighthouse.productinquiry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.productinquiry.mapper.ProductInquiryMapper;
import com.ar.lighthouse.productinquiry.service.ProductInquiryService;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

@Service
public class ProductInquiryServiceImpl implements ProductInquiryService{

	@Autowired
	ProductInquiryMapper mapper;
	
	@Override
	public List<ProductInquiryVO> getInquiryList(ProductInquiryVO productInquiryVO) {
		return mapper.selectInquiryList(productInquiryVO);
	}

	//등록
	@Override
	public void addInquiry(ProductInquiryVO inquiryVO) {
		mapper.insertInquiry(inquiryVO);
	}

	//삭제
	@Override
	public boolean removeInquiry(int queCode) {
		
		return mapper.deleteInquiry(queCode)==1;
	}

}
