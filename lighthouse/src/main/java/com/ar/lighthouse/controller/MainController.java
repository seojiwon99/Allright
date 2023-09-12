package com.ar.lighthouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.main.service.MainPageService;

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
	
		model.addAttribute("productList",service.selProductList());
		System.out.println(model);
		
		model.addAttribute("productbanner",service.showEventBanner());
		System.out.println(model);
		
		return "page/body";
		
	}

}
