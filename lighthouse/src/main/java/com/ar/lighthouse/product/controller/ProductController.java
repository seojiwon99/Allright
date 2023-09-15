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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.review.service.ReviewImgVO;
import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ReviewService reviewService;
	
	
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
	
	//리뷰등록
	@PostMapping("reviewInsert")
	public String addReivew(ReviewVO review, ReviewImgVO reviewImg, RedirectAttributes rttr) {
		
		reviewService.addReview(review);
		reviewService.addReviewImg(reviewImg);
		rttr.addFlashAttribute("result", "리뷰 등록");
		
		return "redirect:/page/goods/goodDetail";
		
	}
	
	//상품 단건 조회
	@GetMapping("goodDetail")
	public String getGoodDetail(String productCode, Model model) {
		ProductVO vo = new ProductVO();
		vo.setProductCode(productCode);
		
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setProductCode(productCode);
		
		ProductVO productVO = productService.goodsDetail(vo);
		model.addAttribute("goods", productVO);
		
		
		//리뷰조회
		model.addAttribute("review", reviewService.getReviewList(reviewVO));
		System.out.println(model);
		
		return "page/goods/goodDetail";
	}
	
	
}
