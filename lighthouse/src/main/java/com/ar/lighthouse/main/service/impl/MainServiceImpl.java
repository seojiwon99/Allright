package com.ar.lighthouse.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.main.mapper.mainPageMapper;
import com.ar.lighthouse.main.service.EventImgVO;
import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ProductVO;

@Service
public class MainServiceImpl implements MainPageService{

	@Autowired
	mainPageMapper mapper;

	@Override
	public List<ProductVO> selProductList() {
		return mapper.selProductList();
	}

	@Override
	public List<EventImgVO> showEventBanner() {
		return mapper.showEventBanner();
	}

	@Override
	public List<EventImgVO> randomGetProduct() {
		return mapper.randomSelProduct();
	}

	@Override
	public List<CategoryVO> getCategoryList() {
		return mapper.categoryList();
	}

	@Override
	public List<CategoryVO> getchildCategory(CategoryVO cate) {
		return mapper.childCategory(cate);
	}
	
	@Override
	public List<CategoryVO> getChildCateList(CategoryVO cate) {
		return mapper.childCateList(cate);
	}
	
	
	
}
