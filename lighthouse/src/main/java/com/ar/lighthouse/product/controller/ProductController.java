package com.ar.lighthouse.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.product.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	@GetMapping("sellerMain")
	public String seller() {
		return "seller/sellerMain";
	}
	
	@GetMapping("productList")
	public String productList(Model model) {
		model.addAttribute("productList", productService.productList());
		return "seller/productList";
	}
	
	@GetMapping("productForm")
	public String productForm() {
		return "seller/productForm";
	}
}
