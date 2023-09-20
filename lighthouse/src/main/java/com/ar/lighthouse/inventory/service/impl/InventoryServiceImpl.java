package com.ar.lighthouse.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.inventory.mapper.InventoryMapper;
import com.ar.lighthouse.inventory.service.InventoryService;
import com.ar.lighthouse.product.service.ProductVO;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryMapper mapper;
	
	@Override
	public List<ProductVO> getAllProducts(ProductVO productVO) {
		return mapper.allProductList(productVO);
	}

}
