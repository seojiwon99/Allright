package com.ar.lighthouse.inventory.service;

import java.util.List;

import com.ar.lighthouse.product.service.ProductVO;

public interface InventoryService {

	public List<ProductVO> getAllProducts(ProductVO productVO);
}
