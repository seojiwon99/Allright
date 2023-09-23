package com.ar.lighthouse.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ImgsListVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryService;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;
import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class ProductController {


	@Value("${file.upload.path}")
	private String uploadPath;
	

	@Autowired
	ProductService productService;

	@Autowired
	ReviewService reviewService;
	

	@Autowired
	ProductInquiryService custominquiryService;


	@Autowired
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
	
//	교환 페이지
	@GetMapping("exchangeList")
	public String productExchange() {
		return "page/seller/exchangeList";
	}
	
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
	public String cancelProdructs() {
		
//		model.addAttribute("cancelInfo", productService.getCancelList(cancelVO));
		
		return "page/seller/cancelProduct";
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
	@GetMapping("insertProductForm")
	public String productForm(Model model, CategoryVO categoryVO, CodeVO codeVO) {
		// model.addAttribute("getCategoryList", mainPageService.getCategoryList());
		model.addAttribute("delivery", productService.getDeliveryList());
		System.out.println(model);
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
  
	// 상품 등록
		@PostMapping("insertProduct")
		public String addProduct(List<MultipartFile> files ,ProductVO productVO,  HttpServletRequest req, RedirectAttributes rtt) {
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
			
			productVO.setMemberId(memberVO.getMemberId());
			productVO.setCategoryCode("P00001");
			productVO.setDeliveryService("영차");

			// System.out.println(productVO);
			productService.addProduct(productVO);
			
		
			int i = 0;
			for(MultipartFile uploadFile : files){
		    	if(uploadFile.getContentType().startsWith("image") == false){
		    		System.err.println("this file is not image type");
		    		return null;
		    	}
		    	// System.out.println(i);
		    	String originalName = uploadFile.getOriginalFilename();
		        // System.out.println("originalName : " + originalName);
		        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
		        productVO.getProductImg().get(i).setImgName(fileName);
		        
		        // System.out.println("fileName : " + fileName);
		    
		        //날짜 폴더 생성
		        String folderPath = makeFolder();
		        // System.out.println("folderPath"+ folderPath);
		        String uuid = UUID.randomUUID().toString();	// 유니크한 이름 때문에
		        //System.out.println("uuid"+uuid);
		        productVO.getProductImg().get(i).setUploadName(uuid+"_"+fileName);
		        
		        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
		        // System.out.println("uploadFileName" + uploadFileName);
		        productVO.getProductImg().get(i).setUploadPath(folderPath);
		        
		        String saveName = uploadPath + File.separator + uploadFileName;
		        
		        Path savePath = Paths.get(saveName);
		        try{
		        	uploadFile.transferTo(savePath); // 파일의 핵심
		            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
		        	productVO.getProductImg().get(i).setProductCode(productVO.getProductCode());
		        	productVO.getProductImg().get(i).setImgOrder(i+1);
		        	// System.out.println("@@@@@@@@@@@@@" + productVO);
		        	if(files.get(0) == uploadFile) {
		        		int idx = originalName.indexOf(".");
		        		//System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
		        		// System.out.println("!!!!!!!!"+originalName.substring(0,idx));
		        		// System.out.println("여기@@@"+ uploadPath + "\\" + folderPath);
		        		// System.out.println("파일보기" + "s_" +uuid+"_"+ originalName);
		        		FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath + "\\" + folderPath, "s_" +uuid+"_"+ originalName));
		        		// System.out.println("thumbnail" + thumbnail);
		        		FileInputStream input = new FileInputStream(new File(uploadPath+ "\\" +folderPath, uuid+"_"+originalName));
		        		Thumbnailator.createThumbnail(input , thumbnail, 100,100);
		        		
		        		thumbnail.close();
		        		
		        	}
		        	System.out.println(productVO.getProductImg().get(i));
		        	productService.addProductImg(productVO.getProductImg().get(i));
		        	i++;
		        	
		        } catch (IOException e) {
		             e.printStackTrace();	             
		        }
		    	
		  }
			 
			rtt.addFlashAttribute("msg", "등륵성공");
			
			return "redirect:insertProductForm/"+memberVO.getMemberId();
		}

		// 상품 상세보기 사진 정보 보내기
		@PostMapping("insertDetailImg")
		public String addDetailImg(Model model,MultipartFile[] uploadFile,ImgsListVO imgVO) {
			System.out.println(uploadFile);
			for(var i=0; i<imgVO.getImgsVO().size(); i++) {
				System.out.println(imgVO.getImgsVO().get(i));			
			}
			
			int idx =0;
			List<ImgsVO> imgsInfo = new ArrayList<ImgsVO>();
			
			for(MultipartFile files : uploadFile) {
				if(files.getContentType().startsWith("image") == false) {
					System.err.println("this file is not image type");
		    		return null;
				}
				
				String originalName = files.getOriginalFilename();
				
				String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
				imgVO.getImgsVO().get(idx).setImgName(fileName);
				
				String folderPath = makeFolder();
				String uuid = UUID.randomUUID().toString();
				imgVO.getImgsVO().get(idx).setUploadName(uuid+"_"+fileName);
				
				String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
				imgVO.getImgsVO().get(idx).setUploadPath(folderPath);
				
				String saveName = uploadPath + File.separator + uploadFileName;
				
				Path savePath = Paths.get(saveName);
				
				try {
					files.transferTo(savePath);
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
			model.addAttribute("img" , imgVO);
			model.addAttribute("uploadFile", uploadFile);
			System.out.println(model);
			return "redirect:insertProductForm";
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
	
	
	// 파일 업로드 처리
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
	
	// 리뷰등록
	@PostMapping("insertReview")
	@ResponseBody
	public String addReivew(MultipartFile[] files, ReviewVO review, ImgsVO imgsVO,Model model) {
		
		reviewService.addReview(review);
		
	    for(MultipartFile uploadFile : files){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename();
	        System.out.println("originalName : " + originalName);
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        imgsVO.setImgName(fileName);
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        
	        //UUID
	        String uuid = UUID.randomUUID().toString();	// 유니크한 이름 때문에
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        imgsVO.setUploadName(uuid+"_"+fileName);
	        
	        // System.out.println("uuid : " + uuid);
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	       //  System.out.println("uploadFileName : " + uploadFileName);
	        imgsVO.setUploadPath(folderPath);
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	       // System.out.println("saveName : " + saveName);
	        
	        Path savePath = Paths.get(saveName);
	        // System.out.println("savePath : " + savePath);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	       //  System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath); // 파일의 핵심
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        	imgsVO.setReviewCode(review.getReviewCode());
				reviewService.addReviewImg(imgsVO);
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        
	     }
	    
	    return "";
	}
	
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));	// 경로에서 사용하는 /는 인지 못함
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); // <- 그래서 separator 사용
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
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

	// qna 수정
	@PostMapping("editInquiry")
	@ResponseBody
	public ProductInquiryVO editInquiry(@RequestBody ProductInquiryVO inquiryVO) {

		System.out.println(inquiryVO);

		if (custominquiryService.editInquiry(inquiryVO)) {
			System.out.println("성공");
		}
		;

		return inquiryVO;

	}

	// qna 삭제
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

	public String getGoodDetail(String productCode, Model model, HttpSession session, ProductVO vo, OptionVO optionVO) {

		// 상품정보
		ProductVO productVO = productService.goodsDetail(vo);
		model.addAttribute("goods", productVO);

		// 리뷰정보
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setProductCode(productCode);
		model.addAttribute("review", reviewService.getReviewList(reviewVO));
		
		
		// qna 조회
		ProductInquiryVO productInquiryVO = new ProductInquiryVO();
		productInquiryVO.setProductCode(productCode);
		model.addAttribute("inquiry", custominquiryService.getInquiryList(productInquiryVO));

		// 옵션 조회
		optionVO.setProductCode(productCode);
		model.addAttribute("options", productService.getOptionList(optionVO));
		System.out.println(model);

		return "page/goods/goodDetail";
	}

	// 이미지 보여주기
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File(uploadPath + fileName);
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping("insertImg")
	public String productdetailImg(Model model, ProductVO productVO, RedirectAttributes rttr) {
//		System.out.println(productVO);
//		model.addAttribute("product", productVO);
		rttr.addFlashAttribute("product", productVO);
		
		return "redirect:/insertProductForm";
	}

}
