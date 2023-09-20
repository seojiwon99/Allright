package com.ar.lighthouse.product.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

import com.ar.lighthouse.productinquiry.service.ProductInquiryService;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

import retrofit2.http.GET;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ReviewService reviewService;
	

	@Autowired

	ProductInquiryService custominquiryService;

	MemberService memberService;


	@Autowired
	MainPageService mainPageService;

	
	
//	판매자 메인페이지
	@GetMapping("sellerMain")
	public String seller() {
		return "page/seller/sellerMain";
	}
	
//	판매자 상품문의페이지
	@GetMapping("productInquiry")
	public String productInquiry() {
		return "page/seller/productInquiry";
	}
	
//	판매자 mypage
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
	/*
	 * @PostMapping("updateDelivery") public String updateDeliveryInfo(@RequestBody
	 * List<DetailVO> detailVO) { List<String> updateList = new ArrayList();
	 * for(DetailVO detailVo : deliveryInfo) { int result =
	 * productService.updateExStatus(productVO); if(result > 0) {
	 * delList.add(productVO.getProductCode()); } }
	 * 
	 * 
	 * return delList;
	 * 
	 * }
	 */
	
//	정산관리 페이지
	@GetMapping("settlementManagement")
	public String settlementManagement() {
		return "page/seller/settlementManagement";
	}
	
//	판매자 상품목록
	@GetMapping("productList/{memberId}")
	public String productList(@PathVariable String memberId, Model model) {
	    // memberId를 기반으로 해당 사용자가 등록한 상품 목록 조회
	    List<ProductVO> productList = productService.getProductsByMemberId(memberId);
	    
	    // 모델에 상품 목록 추가
	    model.addAttribute("productList", productList);
	    
	    return "page/seller/productList";
	}
	
//	상품 취소관리 페이지
	@GetMapping("cancelProduct") //Model model, CancelVO cancelVO
	public String cancelProdructs(Model model, CancelVO cancelVO) {
		
		model.addAttribute("cancelInfo", productService.getCancelList(cancelVO));
		
		return "page/seller/cancelProduct";
	}
	
//	교환 관리 페이지
	@GetMapping("exchangeList")
	public String exchangeProducts(Model model, ExchangeVO exchangeVO, ReturnVO returnVO) {
		
		model.addAttribute("exchangeInfo", productService.getExchangeList(exchangeVO));
		model.addAttribute("returnList", productService.getReturnList(returnVO));
		return "page/seller/exchangeList";
	}
	
	
//	상품상세설명등록 페이지
	@GetMapping("productContent")
	public String productContent() {
		return "page/seller/productContent";
	}

//	조건순 order by
	@GetMapping("getOptionProduct")
	public String productDetail(Model model,ProductVO productVO) {
		model.addAttribute("productList",productService.getOptionProduct(productVO));
		return "page/seller/productList :: #sortList";
	}

//	등록폼
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
		for (ProductVO productVO : productList) {
			int result = productService.updateExStatus(productVO);
			if (result > 0) {
				delList.add(productVO.getProductCode());
			}
		}

		return delList;

	}
	
	

	// 리뷰등록

	@PostMapping("insertReview")
	@ResponseBody
	public String addReivew(MultipartFile[] files, ReviewVO review, ImgsVO imgsVO, Model model) {
		System.out.println(files);
		
		String uploadFolder = "C:\\upload";

		// make folder
		File uploadpath = new File(uploadFolder, getFolder());
		System.out.println(uploadpath);

		if (uploadpath.exists() == false) {
			uploadpath.mkdirs();
		}

		for (MultipartFile multipartFile : files) {

			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			File saveFile = new File(uploadpath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);
				System.out.println(saveFile);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		reviewService.addReview(review);
		
		reviewService.addReviewImg(imgsVO);
		
		System.out.println(model);
		
		
		
		model.addAttribute("img", imgsVO);
		
		
		reviewService.addReviewImg(imgsVO);;
		
//		System.out.println("??");


		return "page/goods/goodDetail";

	}

	// 리뷰 삭제
	@PostMapping("removeDelete")
	public String deleteReview(String memberId) {

		reviewService.removeReview(memberId);

		return "redirect:/page/goods/goodDetail";
	}

	
	//qna 등록
	@PostMapping("insertInquiry")
	@ResponseBody
	public ProductInquiryVO addInquiry( Model model, @RequestBody ProductInquiryVO inquiryVO) {
		
		custominquiryService.addInquiry(inquiryVO);
	
		return inquiryVO;
		
	}
	
	//qna 삭제
	@PostMapping("removeInquiry")
	@ResponseBody
	public int removeInquiry(@RequestBody Integer queCode , RedirectAttributes rttr) {
		System.out.println(queCode);
		if(custominquiryService.removeInquiry(queCode)) {
			rttr.addFlashAttribute("result","success");
		}
		return queCode;
		
		
		
	}
	
	// 상품 단건 조회

	@GetMapping("goodDetail")
	public String getGoodDetail(String productCode, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		ProductVO vo = new ProductVO();
		vo.setProductCode(productCode);

		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setProductCode(productCode);
		System.out.println(reviewVO);

		ProductVO productVO = productService.goodsDetail(vo);
		
		
		model.addAttribute("goods", productVO);

		ProductInquiryVO productInquiryVO = new ProductInquiryVO();
		productInquiryVO.setProductCode(productCode);

		// 리뷰조회
		model.addAttribute("review", reviewService.getReviewList(reviewVO));
		
		
		
		// qna 조회
		model.addAttribute("inquiry", custominquiryService.getInquiryList(productInquiryVO));
		System.out.println(model);

		return "page/goods/goodDetail";
	}

	// 파일 업로드 처리
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

}
