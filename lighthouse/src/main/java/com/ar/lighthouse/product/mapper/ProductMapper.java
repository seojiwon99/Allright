package com.ar.lighthouse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductVO> selectProductList(ProductVO productVO);
	
//	order by
	public List<ProductVO> selectOptionProduct(ProductVO productVO);
	
	
//	등록
	public int insertProduct(ProductVO productVO);
	public int insertProduct(OptionVO optionVO);
	
//	수정
	public int modifyProduct(ProductVO productVO);
	
//	전시상태 수정
	public int updateExStatus(ProductVO productVO);

	
	
	//상품 단건조회페이지
	public ProductVO selectInfo(ProductVO productVO);
	
}
