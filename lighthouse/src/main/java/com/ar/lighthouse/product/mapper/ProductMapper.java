package com.ar.lighthouse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.product.service.ProductVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductVO> productList(ProductVO productVO);
	
	public List<ProductVO> selectProduct(ProductVO productVO);
	
	public int modifyProduct(ProductVO productVO);
	
	public int insertProduct(ProductVO productVO);
	
	public int productDelete(ProductVO productVO);

	
}
