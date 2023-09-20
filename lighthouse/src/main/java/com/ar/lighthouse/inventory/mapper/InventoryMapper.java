package com.ar.lighthouse.inventory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.product.service.ProductVO;

@Mapper
public interface InventoryMapper {

	//모든 항목 리스트
	public List<ProductVO> allProductList(ProductVO productVO);
}
