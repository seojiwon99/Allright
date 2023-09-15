package com.ar.lighthouse.product.service;

import java.util.List;

public interface ProductService {
	
//	
	public List<ProductVO> getproductList(ProductVO productVO);
	
	
	public List<ProductVO> getOptionProduct(ProductVO productVO);
	
//	전시상태변경
	public int updateExStatus(ProductVO productVO);
	
//  상품등록	
	public int addProduct(ProductVO productVO);
	public int addOption(OptionVO optionVO);

	//상품 단건 상세페이지
	public ProductVO goodsDetail(ProductVO productVO);

}
