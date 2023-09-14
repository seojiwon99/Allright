package com.ar.lighthouse.product.service;

import java.util.List;

public interface ProductService {
	
	public List<ProductVO> productList(ProductVO productVO);
	
	public List<ProductVO> selectProduct(ProductVO productVO);
	
	public int productDelete(ProductVO productVO);


	//상품 단건 상세페이지
	public ProductVO goodsDetail(ProductVO productVO);

}
