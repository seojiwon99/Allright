package com.ar.lighthouse.main.service;

import java.util.List;

import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ProductVO;

public interface MainPageService {
	public List<CategoryVO> getCategoryList();
	
	public List<CategoryVO> getchildCategory(CategoryVO cate);
	
	public List<ProductVO> selProductList();
	
	public List<EventImgVO> showEventBanner();
	
	public List<EventImgVO> randomGetProduct();
	
	public List<CategoryVO> getChildCateList(CategoryVO cate);
	
	public List<CategoryVO> getAllCategoryList();
	
	public List<ProductVO> getBestProductByFassion();

	public List<ProductVO> getBestProductByFOOD();
	
	public List<ProductVO> getBestProductByLife();
}
