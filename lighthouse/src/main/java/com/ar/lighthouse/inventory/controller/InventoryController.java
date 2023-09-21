package com.ar.lighthouse.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.inventory.service.InventoryService;
import com.ar.lighthouse.product.service.ProductVO;

@Controller
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	//전체 조회
	@GetMapping("goodsList")
	public String getAllProducts(Model model, ProductVO proucVo) {

		model.addAttribute("product", inventoryService.getAllProducts(proucVo));

		return "page/goods/goodsList";

	}
}
