package com.ar.lighthouse.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;

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
	
	@GetMapping("modifyForm")
	public String modifyForm() {
		return "seller/modifyForm";
	}
	
	
	@GetMapping("productDelete")
	public int productDelete(@RequestParam("productCode") String productCode) {
	    // productCode 값을 받아와서 삭제 작업 수행
	    return productService.deleteProduct(productCode);
	}
	
	
	
	
	
	
}
