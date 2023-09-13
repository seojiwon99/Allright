package com.ar.lighthouse.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String productList(Model model, ProductVO productVO) {
		model.addAttribute("productList", productService.productList(productVO));
		return "seller/productList";
	}
	
//	조건순 order by
	@GetMapping("selectProduct")
	public List<ProductVO> selectProduct(ProductVO productVO) {
		return productService.selectProduct(productVO);
	}
	
	
	@GetMapping("productForm")
	public String productForm() {
		return "seller/productForm";
	}
	
	@GetMapping("modifyForm")
	public String modifyForm() {
		return "seller/modifyForm";
	}
	
//	단건삭제
	@GetMapping("productDelete")
	public int productDelete(ProductVO productVO) {
	    return productService.productDelete(productVO);
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
