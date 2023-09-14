package com.ar.lighthouse.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.product.mapper.ProductMapper;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper productMapper;
	



	@Override
	public List<ProductVO> productList(ProductVO productVO) {
		return  productMapper.productList(productVO);
	}


	@Override
	public int productDelete(ProductVO productVO) {
		return productMapper.productDelete(productVO);
	}





	@Override
	public List<ProductVO> selectProduct(ProductVO productVO) {
		return productMapper.selectProduct(productVO);
	}

	
	//제품 상세 단건조회
	@Override
	public ProductVO goodsDetail(ProductVO productVO) {
		return productMapper.selectInfo(productVO);
	}


	
	
}
