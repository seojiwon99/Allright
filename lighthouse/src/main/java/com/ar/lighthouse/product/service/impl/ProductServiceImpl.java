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
	public List<ProductVO> productList() {
		return productMapper.productList();
	}

	

	@Override
	public int deleteProduct(String productCode) {
		return productMapper.deleteProduct(productCode);
	}
	
	
	
	
}
