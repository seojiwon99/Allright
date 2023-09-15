package com.ar.lighthouse.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.product.mapper.ProductMapper;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper productMapper;
	


	@Override
	public List<ProductVO> getproductList(ProductVO productVO) {
		return  productMapper.selectProductList(productVO);
	}


	@Override
	public int updateExStatus(ProductVO productVO) {
		return productMapper.updateExStatus(productVO);
	}

//  order by list
	@Override
	public List<ProductVO> getOptionProduct(ProductVO productVO) {
		return productMapper.selectOptionProduct(productVO);
	}

//  상품테이블 등록
	@Override
	public int addProduct(ProductVO productVO) {
		return productMapper.insertProduct(productVO);
	}
//	옵션테이블 등록
	@Override
	public int addOption(OptionVO optionVO) {
		return productMapper.insertProduct(optionVO);
	}


	
	//제품 상세 단건조회
	@Override
	public ProductVO goodsDetail(ProductVO productVO) {
		return productMapper.selectInfo(productVO);
	}




	
	
}
