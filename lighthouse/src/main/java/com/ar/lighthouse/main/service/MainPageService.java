package com.ar.lighthouse.main.service;

import java.util.List;

import com.ar.lighthouse.product.service.ProductVO;

public interface MainPageService {

	public List<ProductVO> selProductList();
	
	public List<EventImgVO> showEventBanner();
}
