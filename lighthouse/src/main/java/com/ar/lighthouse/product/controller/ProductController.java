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

import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.*;
import com.ar.lighthouse.main.service.MainPageService;
@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	MainPageService mainPageService;

	
//	판매자 메인페이지
	@GetMapping("sellerMain")
	public String seller() {
		return "page/seller/sellerMain";
	}
	
	@GetMapping("productList")
	public String productList(Model model, ProductVO productVO) {
		model.addAttribute("productList", productService.getproductList(productVO));
		return "page/seller/productList";
	}
	
//	조건순 order by
	@GetMapping("getOptionProduct")
	public String productDetail(Model model,ProductVO productVO) {
		System.out.println(productVO.getOptionVal());
		model.addAttribute("productList",productService.getOptionProduct(productVO));
		return "page/seller/productList :: #sortList";
	}
	
//	등록폼
	@GetMapping("insertProduct")
	public String productForm(Model model, CategoryVO categoryVO){
		model.addAttribute("getCategoryList", mainPageService.getCategoryList());
		return "page/seller/productForm";
	}
//  등록 ( 첫번째 카테고리
	@GetMapping("childCate")
	public String childCate(CategoryVO categoryVO, Model model) {
		model.addAttribute("getCategoryList",mainPageService.getchildCategory(categoryVO));
		return "page/seller/productForm :: #ChildCate";
	}
//  등록 ( 두번째 카테고리
	@GetMapping("childOfCate")
	public String childOfCate(CategoryVO categoryVO, Model model) {
		model.addAttribute("getCategoryList",mainPageService.getchildCategory(categoryVO));
		return "page/seller/productForm :: #ChildOfChildCate";
	}
//  등록 ( 세번째 카테고리
	@GetMapping("thirdOfCate")
	public String thirdOfCate(CategoryVO categoryVO, Model model) {
		model.addAttribute("getCategoryList",mainPageService.getchildCategory(categoryVO));
		return "page/seller/productForm :: #thirdOfChildCate";
	}
	
	
// 등록
	@PostMapping("insertProduct")
	public String addProduct(ProductVO productVO, OptionVO optionVO) {
		productService.addProduct(productVO);
		productService.addOption(optionVO);
		return "redirect:productList";
	}

	
//	수정폼
	@GetMapping("modifyForm")
	public String modifyForm() {
		return "page/seller/modifyForm";
	}
	
//	선택전시상태변경
	@PostMapping("updateExStatus")
	@ResponseBody
	public List<String> productDelete(@RequestBody List<ProductVO> productList) {
	    List<String> delList = new ArrayList();
	    for(ProductVO productVO : productList) {
	    	int result = productService.updateExStatus(productVO);
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
