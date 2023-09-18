package com.ar.lighthouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.main.service.MainPageService;


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
	public String Body(Model model) {
	
		model.addAttribute("categories",service.getCategoryList());
		
		model.addAttribute("productList",service.selProductList());
		System.out.println(model);
		
		model.addAttribute("productbanner",service.showEventBanner());
	
		model.addAttribute("productRand", service.randomGetProduct());
	
		return "page/body";
		
	}
	
	@GetMapping("goodsList")
	public String goodsList() {
		
		return "page/goods/goodsList";
		
	}

}
