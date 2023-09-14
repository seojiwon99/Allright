package com.ar.lighthouse.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	
//	판매자 메인페이지
	@GetMapping("sellerMain")
	public String seller() {
		return "page/seller/sellerMain";
	}
	
	@GetMapping("productList")
	public String productList(Model model, ProductVO productVO) {
		model.addAttribute("productList", productService.productList(productVO));
		return "page/seller/productList";
	}
	
//	조건순 order by
	@GetMapping("selectProduct")
	public String selectProduct( Model model,ProductVO productVO) {
		System.out.println(productVO.getOptionVal());
		model.addAttribute("productList",productService.selectProduct(productVO));
		return "page/seller/productList :: #sortList";
	}
	
//	등록폼
	@GetMapping("insertProduct")
	public String productForm() {
		return "page/seller/productForm";
	}

// 등록
	@PostMapping("insertProduct")
	public String insertProduct(ProductVO productVO) {
		productService.insertProduct(productVO);
		return "redirect:productList";
	}

	
//	수정폼
	@GetMapping("modifyForm")
	public String modifyForm() {
		return "page/seller/modifyForm";
	}
	
//	다건삭제
	@PostMapping("productDelete")
	@ResponseBody
	public List<String> productDelete(@RequestBody List<ProductVO> productList) {
	    List<String> delList = new ArrayList();
	    
	    for(ProductVO productVO : productList) {
	    	int result = productService.productDelete(productVO);
	    	if(result > 0) {
	    		delList.add(productVO.getProductCode());
	    	}
	    }
		
		return delList;
	    
	}
	
	
	
	//상품 단건 조회
	@GetMapping("goodDetail")
	public String getGoodDetail(String productCode, Model model) {
		ProductVO vo = new ProductVO();
		vo.setProductCode(productCode);
		
		ProductVO productVO = productService.goodsDetail(vo);
		model.addAttribute("goods", productVO);
		
		return "page/goods/goodDetail";
	}
	
	
}
