package com.ar.lighthouse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductVO> productList(ProductVO productVO);
	
//	order by
	public List<ProductVO> selectProduct(ProductVO productVO);
	
	
//	등록
	public int insertProduct(ProductVO productVO);
//	public int insertOption(OptionVO optionVO);
	
//	수정
	public int modifyProduct(ProductVO productVO);
	
//	삭제
	public int productDelete(ProductVO productVO);

	
}
