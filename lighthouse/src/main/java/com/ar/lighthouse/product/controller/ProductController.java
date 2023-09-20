package com.ar.lighthouse.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.ImgsVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;
import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

@Controller
public class ProductController {

   @Autowired
   ProductService productService;

   @Autowired
   ReviewService reviewService;

   @Autowired
   MemberService memberService;

   @Autowired
   MainPageService mainPageService;

//   판매자 메인페이지
   @GetMapping("sellerMain")
   public String seller() {
      return "page/seller/sellerMain";
   }

//   판매자 상품문의페이지
   @GetMapping("productInquiry")
   public String productInquiry() {
      return "page/seller/productInquiry";
   }

//   판매자 mypage
   @GetMapping("sellerMypage/{memberId}")
   public String findMember(Model model, MemberVO memberVO) {

      model.addAttribute("sellerInfo", productService.getSellerInfo(memberVO));
      return "page/seller/sellerMypage";
   }

//  주문/발송 페이지
   @GetMapping("orderManagement")
   public String productOrder(Model model, ProductVO productVO) {
      model.addAttribute("orderList", productService.getProductOrder(productVO));
      return "page/seller/orderManagement";
   }

// 주문배송정보입력
   
     @PostMapping("updateDelivery") 
     @ResponseBody
     public List<String> updateDeliveryInfo(@RequestBody List<DetailVO> detailList){ 
        List<String> updateList = new ArrayList<>();
        
        for(DetailVO detailVo : detailList) { 
           int result =
              productService.updateDeliveryInfo(detailVo); 
           if(result > 0) {
              updateList.add(Integer.toString(detailVo.getOrderDetailCode()));
                    }
           }
     
     return updateList;
     
     }
    

//   정산관리 페이지
   @GetMapping("settlementManagement")
   public String settlementManagement() {
      return "page/seller/settlementManagement";
   }

//   판매자 상품목록
   @GetMapping("productList/{memberId}")
   public String productList(@PathVariable String memberId, Model model) {
      // memberId를 기반으로 해당 사용자가 등록한 상품 목록 조회
      List<ProductVO> productList = productService.getProductsByMemberId(memberId);

      // 모델에 상품 목록 추가
      model.addAttribute("productList", productList);

      return "page/seller/productList";
   }

//   상품 취소관리 페이지
   @GetMapping("cancelProduct") // Model model, CancelVO cancelVO
   public String cancelProdructs(Model model, CancelVO cancelVO) {

      model.addAttribute("cancelInfo", productService.getCancelList(cancelVO));

      return "page/seller/cancelProduct";
   }

//   교환 관리 페이지
   @GetMapping("exchangeList")
   public String exchangeProducts(Model model, ExchangeVO exchangeVO, ReturnVO returnVO) {

      model.addAttribute("exchangeInfo", productService.getExchangeList(exchangeVO));
      model.addAttribute("returnList", productService.getReturnList(returnVO));
      return "page/seller/exchangeList";
   }

//   상품상세설명등록 페이지
   @GetMapping("productContent")
   public String productContent() {
      return "page/seller/productContent";
   }

//   조건순 order by
   @GetMapping("getOptionProduct")
   public String productDetail(Model model, ProductVO productVO) {
      model.addAttribute("productList", productService.getOptionProduct(productVO));
      return "page/seller/productList :: #sortList";
   }

//   등록폼
   @GetMapping("insertProduct")
   public String productForm(Model model, CategoryVO categoryVO) {
      model.addAttribute("getCategoryList", mainPageService.getCategoryList());
      return "page/seller/productForm";
   }

//  등록 ( 첫번째 카테고리
   @GetMapping("childCate")
   public String childCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #ChildCate";
   }

//  등록 ( 두번째 카테고리
   @GetMapping("childOfCate")
   public String childOfCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #ChildOfChildCate";
   }

//  등록 ( 세번째 카테고리
   @GetMapping("thirdOfCate")
   public String thirdOfCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #thirdOfChildCate";
   }

// 등록
   @PostMapping("insertProduct")
   public String addProduct(ProductVO productVO, OptionVO optionVO) {
      productService.addProduct(productVO);
      productService.addOption(optionVO);
      return "redirect:productList";
   }

//   수정폼
   @GetMapping("modifyForm")
   public String modifyForm() {
      return "page/seller/modifyForm";
   }

//   선택전시상태변경
   @PostMapping("updateExStatus")
   @ResponseBody
   public List<String> productDelete(@RequestBody List<ProductVO> productList) {
      List<String> delList = new ArrayList<>();
      for (ProductVO productVO : productList) {
         int result = productService.updateExStatus(productVO);
         if (result > 0) {
            delList.add(productVO.getProductCode());
         }
      }

      return delList;

   }

   // 리뷰등록
   @PostMapping("reviewInsert")
   public String addReivew(ReviewVO review, ImgsVO imgsVO, Model model) {

      reviewService.addReview(review);
      model.addAttribute("review", review);
      reviewService.addReviewImg(imgsVO);
      model.addAttribute("reviewImg", imgsVO);

      return "/page/goods/goodDetail";

   }

   // 리뷰 삭제
   @PostMapping("removeDelete")
   public String deleteReview(String memberId) {

      reviewService.removeReview(memberId);

      return "redirect:/page/goods/goodDetail";
   }

   // 상품 단건 조회

   @GetMapping("goodDetail")
   public String getGoodDetail(String productCode, Model model) {
      ProductVO vo = new ProductVO();
      vo.setProductCode(productCode);

      ReviewVO reviewVO = new ReviewVO();
      reviewVO.setProductCode(productCode);

      ProductVO productVO = productService.goodsDetail(vo);
      model.addAttribute("goods", productVO);

      // 리뷰조회
      model.addAttribute("review", reviewService.getReviewList(reviewVO));
      System.out.println(model);

      return "page/goods/goodDetail";
   }

}