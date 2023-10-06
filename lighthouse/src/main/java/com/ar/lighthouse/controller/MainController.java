package com.ar.lighthouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ProductVO;


/*
  개발자 : 김무준
  개발일자 : 2023/09/13
  		메인페이지관리
 */

@Controller
public class MainController {

	@Autowired
	MainPageService service;

	@GetMapping("test")
	public String Test() {
		return "page/test";
	}


	
	@GetMapping("/")
	public String Body(Model model, ProductVO productVO) {
	
		model.addAttribute("categories",service.getCategoryList());
		model.addAttribute("allCtg", service.getAllCategoryList());
		model.addAttribute("productList",service.selProductList());
		System.out.println(model);
		
		model.addAttribute("productbanner",service.showEventBanner());
	
		model.addAttribute("productRand", service.randomGetProduct(productVO));
	
		return "page/body";
		
	}
	

	
	@PostMapping("childCateList")
	@ResponseBody
	public List<CategoryVO> cateChildList(@RequestBody CategoryVO cate){
		
		return service.getChildCateList(cate);
	}
	
	@PostMapping("category")
	public String TTScategory(Model model) {
		model.addAttribute("allCtg", service.getAllCategoryList());
		return "page/goods/category";
	}

}
