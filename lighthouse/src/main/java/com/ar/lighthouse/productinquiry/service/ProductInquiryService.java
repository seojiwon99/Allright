package com.ar.lighthouse.productinquiry.service;

import java.util.List;

public interface ProductInquiryService {

	//문의 사항 조회
	public List<ProductInquiryVO> getInquiryList(ProductInquiryVO productInquiryVO);
	
	public void addInquiry(ProductInquiryVO inquiryVO);
}
