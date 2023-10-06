package com.ar.lighthouse.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.main.service.EventImgVO;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ProductVO;

@Mapper
public interface mainPageMapper {

	public List<CategoryVO> categoryList();
	
	public List<CategoryVO> childCategory(CategoryVO cate);
	
	public List<ProductVO> selProductList();
	
	public List<EventImgVO> showEventBanner();
	
	public List<EventImgVO> randomSelProduct(ProductVO productVO);
	
	// 카테고리 자식 가져오기
	public List<CategoryVO> childCateList(CategoryVO cate);
	
	public List<CategoryVO> selectAllCategory();
}
