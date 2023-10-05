package com.ar.lighthouse.productinquiry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

@Mapper
public interface ProductInquiryMapper {

	// 문의사항 리스트
	public List<ProductInquiryVO> selectInquiryList(@Param("productInquiryVO") ProductInquiryVO productInquiryVO,
			@Param("cri") Criteria cri);

	// 문의 사항 카운트
	public int inquiryCount(ProductInquiryVO productInquiryVO);

	// 문의사항 등록
	public void insertInquiry(ProductInquiryVO inquiryVO);

	// 문의사항 수정
	public int updateInquiry(ProductInquiryVO productInquiryVO);

	// 문의 사항 삭제
	public int deleteInquiry(int queCode);
	
	public List<ProductInquiryVO> selectInquiryListN(ProductInquiryVO productInquiryVO);
}
