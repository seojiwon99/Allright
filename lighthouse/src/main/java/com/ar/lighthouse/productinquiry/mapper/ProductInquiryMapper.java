package com.ar.lighthouse.productinquiry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

@Mapper
public interface ProductInquiryMapper {
	
	//문의사항 리스트
	public List<ProductInquiryVO> selectInquiryList(ProductInquiryVO productInquiryVO);
}
