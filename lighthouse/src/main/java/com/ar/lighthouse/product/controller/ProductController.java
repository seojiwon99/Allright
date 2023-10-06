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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.common.PageDTO;

import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.NoticeVO;

import com.ar.lighthouse.main.service.MainPageService;
import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.CategoryVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.ImgsListVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;
import com.ar.lighthouse.product.service.SellerCalVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryService;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;
import com.ar.lighthouse.review.service.ReviewService;
import com.ar.lighthouse.review.service.ReviewVO;

import net.coobird.thumbnailator.Thumbnailator;

// 홍규연 : 판매자 페이지
@Controller
public class ProductController {

   @Value("${file.upload.path}")
   private String uploadPath;

   @Autowired
   AdminService adminService;

   @Autowired
   ProductService productService;

   @Autowired
   ReviewService reviewService;
   
   @Autowired
   CustomService customService;
   
   @Autowired
   ProductInquiryService custominquiryService;

   @Autowired
   MemberService memberService;

   @Autowired
   MainPageService mainPageService;

   @Autowired
   CartService cartService;
   
   @Autowired
   MainPageService service;

//  판매자 메인페이지
   @GetMapping("sellerMain")
   public String seller() {
      return "page/seller/sellerMain";

   }

   // 공지사항 화면(페이징)
   @GetMapping("sellerInquiry")
   public String noticeList(Model model, Criteria cri) {
      int totalCnt = customService.getTotalCount(cri);
      model.addAttribute("noticeList", customService.getNoticeList(cri));
      model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
      model.addAttribute("categories",service.getCategoryList());
      model.addAttribute("allCtg", service.getAllCategoryList());
      return "page/seller/sellerInquiry";
   }
   
   // 공지사항 상세화면
   @GetMapping("sellerInquiryInfo")
   public String noticeDetail(@RequestParam(defaultValue = "0") int noticeCode,Model model, @ModelAttribute("cri") Criteria cri) {
      NoticeVO noticeVO = new NoticeVO();
      noticeVO.setNoticeCode(noticeCode);
      model.addAttribute("categories",service.getCategoryList());
      model.addAttribute("allCtg", service.getAllCategoryList());
      model.addAttribute("noticeInfo",customService.getNotice(noticeVO));
      return "page/seller/sellerInquiryInfo"; 
   }
   
   
//  판매자 상품문의페이지
   @GetMapping("productInquiry")
   public String productInquiry(Model model, ProductInquiryVO productInquiryVO, HttpSession session) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      model.addAttribute("sellerInquiry", productService.getProductInquiry(memberId));
      return "page/seller/productInquiry";
   }

//   판매자 상품문의 답해주기
   @PostMapping("addInquiryAns")
   @ResponseBody
   public List<String> addInqury(@RequestBody List<ProductInquiryVO> addList) {
      List<String> addInquryList = new ArrayList<String>();
      for (ProductInquiryVO productInquiryVO : addList) {
         int result = productService.updateSellerInquiry(productInquiryVO);
         if (result > 0) {
            // int 값을 String으로 변환하여 List에 추가
            addInquryList.add(String.valueOf(productInquiryVO.getQueCode()));
         }
      }

      return addInquryList;
   }

//   상품문의 답변 폼
   @GetMapping("inquiryAnsForm")
   public String productInquiryAnsForm() {
      return "page/seller/inquiryAnsForm";
   }

//  판매자 mypage
   @GetMapping("sellerMypage")
   public String findMember(Model model, HttpSession session) {

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      model.addAttribute("sellerInfo", productService.getSellerInfo(memberId));
      return "page/seller/sellerMypage";
   }

// 주문/발송 페이지
   @GetMapping("orderManagement")
   public String productOrder(Model model, HttpSession session) {

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      model.addAttribute("orderList", productService.getProductOrder(memberId));
      return "page/seller/orderManagement";
   }

//orderOptionManagement 선택옵션 리스트
   @GetMapping("orderOptionManagement")
   public String productOrderOption(Model model, DetailVO detailVO, HttpSession session) {

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      detailVO.setMemberId(memberId);

      List<DetailVO> orderList = productService.getOrderOptionList(detailVO);
      model.addAttribute("orderList", orderList);

      return "page/seller/orderManagement :: #orderChkList";
   }
   
//   주문상태에 따른 list
   @GetMapping("statusOrder")
   public String orderStatusList(Model model, DetailVO detailVO, HttpSession session) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      
      detailVO.setMemberId(memberId);
      System.out.println("@@@" + detailVO.getOrderStatus());
      model.addAttribute("orderList", productService.getStatusList(detailVO));
      
      return "page/seller/orderManagement :: #orderChkList";
   }
   
//   판매자 직접 취소처리
   @PostMapping("deleteOrderSelf")
   @ResponseBody
   public List<String> deleteOrderSelf(@RequestBody List<DetailVO> orderCancelList){
      List<String> CancelSelf = new ArrayList<String>();
      for(DetailVO detailVO : orderCancelList ) {
         int result = productService.deleteOrderSelf(detailVO);
         if(result >0) {
            CancelSelf.add(String.valueOf(detailVO.getOrderDetailCode()));
         }
      }
      return CancelSelf;
   }

//주문배송정보입력

   @PostMapping("updateDelivery")
   @ResponseBody
   public List<DetailVO> updateDeliveryInfo(@RequestBody List<DetailVO> detailList) {

      for (DetailVO detailVo : detailList) {
         int result = productService.updateDeliveryInfo(detailVo);

      }

      return detailList;

   }

//  주문상태변경
   @PostMapping("updateOrderStatus")
   @ResponseBody
   public List<String> updateOrder(@RequestBody List<DetailVO> orderStatus) {

      List<String> delList = new ArrayList<>();
      for (DetailVO detailVo : orderStatus) {
         int result = productService.updateOrderStatus(detailVo);

      }
      return delList;
   }

//  정산/통계 페이지
   @GetMapping("settlementManagement")
   public String getCalList(HttpSession session, ExchangeVO exchangeVO, ReturnVO returnVO) {

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      exchangeVO.setMemberId(memberId);
      returnVO.setMemberId(memberId);
      return "page/seller/settlementManagement";
   }

//  정산페이지
   @GetMapping("calculatePage")
   public String getCalculatePage(Model model, SellerCalVO sellerCalVO) {
      model.addAttribute("calList", productService.getCalList(sellerCalVO));
      return "page/seller/calculate";
   }

//  통계페이지
   @GetMapping("statisticsPage")
   public String getstatisticsPage(DetailVO detailVO, HttpSession session, Model model) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      model.addAttribute("staticList", productService.getStaticList(memberId));

      return "page/seller/statistics";
   }
   
//   월별 주문 금액
   @GetMapping("monthlyData")
   @ResponseBody
   public List<DetailVO> getMonthlyCount(DetailVO detialVO, Model model){
      System.out.println(detialVO.getMonth());
      List<DetailVO> list =productService.getMonthlyCount(detialVO);
      for(DetailVO vo : list) {
         System.out.println(vo);
      }
      return productService.getMonthlyCount(detialVO);
   }

//  상품 취소관리 페이지
   @GetMapping("cancelProduct") // Model model, CancelVO cancelVO
   public String cancelProdructs(Model model, HttpSession session) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      model.addAttribute("cancelInfo", productService.getCancelList(memberId));

      return "page/seller/cancelProduct";
   }

   // 상품 취소검색 기능
   @GetMapping("cancelOption") // Model model, CancelVO cancelVO
   public String cancelSeaList(Model model, CancelVO cancelVO, HttpSession session) {

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      cancelVO.setMemberId(memberId);

      model.addAttribute("cancelInfo", productService.getCancelSeaList(cancelVO));

      return "page/seller/cancelProduct :: #cancelList";
   }

//  교환 관리 페이지
   @GetMapping("exchangeList")
   public String exchangeProducts(Model model, ExchangeVO exchangeVO, ReturnVO returnVO, HttpSession session) {
      List<ExchangeVO> combinedInfo = new ArrayList<>();

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      exchangeVO.setMemberId(memberId);
      returnVO.setMemberId(memberId);

      combinedInfo.addAll(productService.getExchangeList(exchangeVO));
      combinedInfo.addAll(productService.getReturnList(returnVO));

      model.addAttribute("exReList", combinedInfo);

      return "page/seller/exchangeList";
   }

//교환/반품 상품 검색
   @GetMapping("exchangeOption")
   public String exchangeSeaList(Model model, ExchangeVO exchangeVO, HttpSession session) {

      List<ExchangeVO> combinedSearch = new ArrayList<>();

      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();

      exchangeVO.setMemberId(memberId);

      combinedSearch.addAll(productService.getReturnSeaList(exchangeVO));
      combinedSearch.addAll(productService.getExchangeSeaList(exchangeVO));
      model.addAttribute("exReList", combinedSearch);

      return "page/seller/exchangeList :: #exReSeaList";
   }

//  상품상세설명등록 페이지
   @GetMapping("productContent")
   public String productContent() {
      return "page/seller/productContent";
   }
   
//  상품상세설명등록 페이지
   @GetMapping("modifyProductContent")
   public String modifyProductContent(@RequestParam("productCode") String productCode,ImgsListVO imgsList, ProductVO productVO, Model model) {
      model.addAttribute("productCode", productCode);
      System.out.println(productCode);
      return "page/seller/modifyProductContent";
   }


//판매자 상품목록
   @GetMapping("productList")
   public String productList(Model model, HttpSession session) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      model.addAttribute("productList", productService.getproductList(memberId));
      // 모델에 상품 목록 추가
      return "page/seller/productList";
   }

//  조건순 order by
   @GetMapping("getOptionProduct")
   public String productDetail(Model model, HttpSession session, ProductVO productVO) {
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      
      productVO.setMemberId(memberId);
      
      List<ProductVO> productList = productService.getOptionProduct(productVO);
      model.addAttribute("productList", productList);
      return "page/seller/productList :: #sortList";
      
      
   }

   // 등록폼
   @GetMapping("insertProduct")
   public String productForm(Model model, CategoryVO categoryVO, CodeVO codeVO) {
      model.addAttribute("getCategoryList", mainPageService.getAllCategoryList());
      model.addAttribute("delivery", productService.getDeliveryList());
      System.out.println(model);
      return "page/seller/productForm";
   }

   
//   수정폼
   @GetMapping("updateProduct")
   public String modifyForm(Model model,CategoryVO categoryVO, CodeVO codeVO,ProductVO productVO,ImgsListVO imgsList) {
      model.addAttribute("getCategoryList", mainPageService.getAllCategoryList());
      model.addAttribute("delivery", productService.getDeliveryList());
      model.addAttribute("product", productService.updateProduct(productVO));
      model.addAttribute("detailImg", imgsList);
      return "page/seller/modifyForm";
      
   }
   
// 등록 ( 첫번째 카테고리
   @GetMapping("childCate")
   public String childCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #ChildCate";
   }

// 등록 ( 두번째 카테고리
   @GetMapping("childOfCate")
   public String childOfCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #ChildOfChildCate";
   }

   // 등록 ( 세번째 카테고리
   @GetMapping("thirdOfCate")
   public String thirdOfCate(CategoryVO categoryVO, Model model) {
      model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
      return "page/seller/productForm :: #thirdOfChildCate";
   }
   
   // 등록 ( 첫번째 카테고리
      @GetMapping("mochildCate")
      public String mochildCate(CategoryVO categoryVO, Model model) {
         model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
         return "page/seller/modifyForm :: #ChildCate";
      }

   // 등록 ( 두번째 카테고리
      @GetMapping("mochildOfCate")
      public String mochildOfCate(CategoryVO categoryVO, Model model) {
         model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
         return "page/seller/modifyForm:: #ChildOfChildCate";
      }

      // 등록 ( 세번째 카테고리
      @GetMapping("mothirdOfCate")
      public String mothirdOfCate(CategoryVO categoryVO, Model model) {
         model.addAttribute("getCategoryList", mainPageService.getchildCategory(categoryVO));
         return "page/seller/modifyForm :: #thirdOfChildCate";
      }

   // 상품 등록
   @PostMapping("insertProduct")
   public String addProduct(List<MultipartFile> files, ProductVO productVO, HttpServletRequest req,
         RedirectAttributes rtt, ImgsListVO imgsVO) {
      HttpSession session = req.getSession();
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");

      productVO.setMemberId(memberVO.getMemberId());

      productVO.setCategoryCode("FMC");
//      패션/남성/신발 여자/신발

      productService.addProduct(productVO);

      int i = 0;
      for (MultipartFile uploadFile : files) {
         if (uploadFile.getContentType().startsWith("image") == false) {
            System.err.println("this file is not image type");
            return null;
         }
         String originalName = uploadFile.getOriginalFilename();
         String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
         productVO.getProductImg().get(i).setImgName(fileName);

         // 날짜 폴더 생성
         String folderPath = makeFolder();
         String uuid = UUID.randomUUID().toString(); // 유니크한 이름 때문에
         productVO.getProductImg().get(i).setUploadName(uuid + "_" + fileName);

         String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
         // System.out.println("uploadFileName" + uploadFileName);
         productVO.getProductImg().get(i).setUploadPath(folderPath);

         String saveName = uploadPath + "/" + uploadFileName;

         Path savePath = Paths.get(saveName);
         try {
            uploadFile.transferTo(savePath); // 파일의 핵심
            // uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
            productVO.getProductImg().get(i).setProductCode(productVO.getProductCode());
            productVO.getProductImg().get(i).setImgOrder(i + 1);
            if (files.get(0) == uploadFile) {
               int idx = originalName.indexOf(".");

               FileOutputStream thumbnail = new FileOutputStream(
                     new File(uploadPath + "\\" + folderPath, "s_" + uuid + "_" + originalName));
               FileInputStream input = new FileInputStream(
                     new File(uploadPath + "\\" + folderPath, uuid + "_" + originalName));
               Thumbnailator.createThumbnail(input, thumbnail, 100, 100);

               thumbnail.close();

            }
            // System.out.println(productVO.getProductImg().get(i));
            productService.addProductImg(productVO.getProductImg().get(i));
            i++;

         } catch (IOException e) {
            e.printStackTrace();
         }

      }
      if (imgsVO != null) {
         for (int j = 0; j < imgsVO.getImgsVO().size(); j++) {
            imgsVO.getImgsVO().get(j).setImgOrder(j + 1);
            imgsVO.getImgsVO().get(j).setProductCode(productVO.getProductCode());
            productService.addProductImg(imgsVO.getImgsVO().get(j));
         }
      }

      rtt.addFlashAttribute("msg", "등륵성공");

      return "redirect:productList";
   }
//   상품수정
   @PostMapping("updateProduct")
   public String updateProduct(List<MultipartFile> files, ProductVO productVO, HttpServletRequest req,
         RedirectAttributes rtt, ImgsListVO imgsVO) {
      HttpSession session = req.getSession();
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");

      productVO.setMemberId(memberVO.getMemberId());
      
      productVO.setCategoryCode("MSU");

      productService.updateProductP(productVO);

      int i = 0;
      for (MultipartFile uploadFile : files) {
         String originalName = uploadFile.getOriginalFilename();
         String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
         productVO.getProductImg().get(i).setImgName(fileName);

         // 날짜 폴더 생성
         String folderPath = makeFolder();
         String uuid = UUID.randomUUID().toString(); // 유니크한 이름 때문에
         productVO.getProductImg().get(i).setUploadName(uuid + "_" + fileName);

         String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
         // System.out.println("uploadFileName" + uploadFileName);
         productVO.getProductImg().get(i).setUploadPath(folderPath);

         String saveName = uploadPath + "/" + uploadFileName;

         Path savePath = Paths.get(saveName);
         try {
            uploadFile.transferTo(savePath); // 파일의 핵심
            // uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
            productVO.getProductImg().get(i).setProductCode(productVO.getProductCode());
            productVO.getProductImg().get(i).setImgOrder(i + 1);
            if (files.get(0) == uploadFile) {
               int idx = originalName.indexOf(".");

               FileOutputStream thumbnail = new FileOutputStream(
                     new File(uploadPath + "\\" + folderPath, "s_" + uuid + "_" + originalName));
               FileInputStream input = new FileInputStream(
                     new File(uploadPath + "\\" + folderPath, uuid + "_" + originalName));
               Thumbnailator.createThumbnail(input, thumbnail, 100, 100);

               thumbnail.close();

            }
            // System.out.println(productVO.getProductImg().get(i));
            productService.updateProductImg(productVO.getProductImg().get(i));
            i++;

         } catch (IOException e) {
            e.printStackTrace();
         }

      }
      if (imgsVO.getImgsVO() != null) {
         System.out.println(imgsVO);
         for (int j = 0; j < imgsVO.getImgsVO().size(); j++) {
            imgsVO.getImgsVO().get(j).setImgOrder(j + 1);
            imgsVO.getImgsVO().get(j).setProductCode(productVO.getProductCode());
            productService.updateProductImg(imgsVO.getImgsVO().get(j));
         }
      }

      rtt.addFlashAttribute("msg", "등륵성공");
      
      return "redirect:productList";
      
   }

   // 상품 상세보기 사진 정보 보내기
   @PostMapping("insertDetailImg")
   public String addDetailImg(Model model, MultipartFile[] uploadFile, ImgsListVO imgVO) {
      System.out.println(uploadFile);
      for (var i = 0; i < imgVO.getImgsVO().size(); i++) {
         System.out.println(imgVO.getImgsVO().get(i));
      }

      int idx = 0;
      List<ImgsVO> imgsInfo = new ArrayList<ImgsVO>();

      for (MultipartFile files : uploadFile) {
         if (files.getContentType().startsWith("image") == false) {
            System.err.println("this file is not image type");
            return null;
         }

         String originalName = files.getOriginalFilename();

         String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
         imgVO.getImgsVO().get(idx).setImgName(fileName);

         String folderPath = makeFolder();
         String uuid = UUID.randomUUID().toString();
         imgVO.getImgsVO().get(idx).setUploadName(uuid + "_" + fileName);

         String uploadFileName = folderPath + '/' + uuid + "_" + fileName;
         imgVO.getImgsVO().get(idx).setUploadPath(folderPath);

         String saveName = uploadPath + "/" + uploadFileName;

         Path savePath = Paths.get(saveName);

         try {
            files.transferTo(savePath);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }
      model.addAttribute("img", imgVO);
      model.addAttribute("uploadFile", uploadFile);
      System.out.println(model);
      return "redirect:insertProductForm";
   }
   
   // 상품 상세보기 사진 정보 보내기
      @PostMapping("updateDetailImg")
      public String updateDetailImg(Model model, MultipartFile[] uploadFile, ImgsListVO imgVO) {
         System.out.println(uploadFile);
         for (var i = 0; i < imgVO.getImgsVO().size(); i++) {
            System.out.println(imgVO.getImgsVO().get(i));
         }

         int idx = 0;
         List<ImgsVO> imgsInfo = new ArrayList<ImgsVO>();

         for (MultipartFile files : uploadFile) {
            if (files.getContentType().startsWith("image") == false) {
               System.err.println("this file is not image type");
               return null;
            }

            String originalName = files.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
            imgVO.getImgsVO().get(idx).setImgName(fileName);

            String folderPath = makeFolder();
            String uuid = UUID.randomUUID().toString();
            imgVO.getImgsVO().get(idx).setUploadName(uuid + "_" + fileName);

            String uploadFileName = folderPath + '/' + uuid + "_" + fileName;
            imgVO.getImgsVO().get(idx).setUploadPath(folderPath);

            String saveName = uploadPath + "/" + uploadFileName;

            Path savePath = Paths.get(saveName);

            try {
               files.transferTo(savePath);
            } catch (IOException e) {
               e.printStackTrace();
            }

         }
         model.addAttribute("img", imgVO);
         model.addAttribute("uploadFile", uploadFile);
         System.out.println(model);
         return "redirect:modifyForm";
      }


   

//   선택전시상태변경
   @PostMapping("updateExStatus")
   @ResponseBody
   public List<String> productDelete(@RequestBody List<ProductVO> productList) {
      List<String> delList = new ArrayList<String>();
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

      return str.replace("-", "/");
   }

   // 리뷰등록
   @PostMapping("insertReview")
   @ResponseBody
   public String addReivew(MultipartFile[] files, ReviewVO review, ImgsVO imgsVO, Model model) {

      reviewService.addReview(review);

      for (MultipartFile uploadFile : files) {
         if (uploadFile.getContentType().startsWith("image") == false) {
            System.err.println("this file is not image type");
            return null;
         }

         String originalName = uploadFile.getOriginalFilename();
         System.out.println("originalName : " + originalName);
         String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
         imgsVO.setImgName(fileName);

         System.out.println("fileName : " + fileName);

         // 날짜 폴더 생성
         String folderPath = makeFolder();

         // UUID
         String uuid = UUID.randomUUID().toString(); // 유니크한 이름 때문에
         // 저장할 파일 이름 중간에 "_"를 이용하여 구분
         imgsVO.setUploadName(uuid + "_" + fileName);

         // System.out.println("uuid : " + uuid);

         String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
         // System.out.println("uploadFileName : " + uploadFileName);
         imgsVO.setUploadPath(folderPath);

         String saveName = uploadPath + "/" + uploadFileName;
         // System.out.println("saveName : " + saveName);

         Path savePath = Paths.get(saveName);
         // System.out.println("savePath : " + savePath);
         // Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
         // System.out.println("path : " + saveName);
         try {
            uploadFile.transferTo(savePath); // 파일의 핵심
            // uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
            imgsVO.setReviewCode(review.getReviewCode());
            reviewService.addReviewImg(imgsVO);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      return "insert";
   }

   private String makeFolder() {
      String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // 경로에서 사용하는 /는 인지 못함
      // LocalDate를 문자열로 포멧
      String folderPath = str; // <- 그래서 separator 사용
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

   @PostMapping("editReview")
   @ResponseBody
   public ReviewVO editReview(MultipartFile files, ReviewVO reviewVO, ImgsVO imgsVO, int reviewCode) {
      System.out.println("review" + reviewVO);

      if (files == null) {
         reviewService.editReview(reviewVO);
         System.out.println("reviewaaaaaaaaaaaaaaaaaaaaaaaaaa" + reviewVO);
         return reviewVO;
      } else {

         String originalName = files.getOriginalFilename();
         System.out.println("originalName : " + originalName);
         String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
         imgsVO.setImgName(fileName);

         System.out.println("fileName : " + fileName);

         // 날짜 폴더 생성
         String folderPath = makeFolder();

         // UUID
         String uuid = UUID.randomUUID().toString(); // 유니크한 이름 때문에
         // 저장할 파일 이름 중간에 "_"를 이용하여 구분
         imgsVO.setUploadName(uuid + "_" + fileName);

         // System.out.println("uuid : " + uuid);

         String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
         // System.out.println("uploadFileName : " + uploadFileName);
         imgsVO.setUploadPath(folderPath);

         String saveName = uploadPath + File.separator + uploadFileName;
         // System.out.println("saveName : " + saveName);

         Path savePath = Paths.get(saveName);
         // System.out.println("savePath : " + savePath);
         // Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
         // System.out.println("path : " + saveName);
         try {
            files.transferTo(savePath); // 파일의 핵심
            // uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
            imgsVO.setReviewCode(reviewVO.getReviewCode());
            reviewService.removeReviewImg(imgsVO);

            reviewService.addReviewImg(imgsVO);
            System.out.println("reviewbbbbbbbba" + reviewVO);

            reviewService.editReviewImg(imgsVO);
            System.out.println("reviewcccccccccccccc" + reviewVO);

            reviewService.editReview(reviewVO);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }
      return reviewVO;
   }

   

   
   
   // 리뷰 삭제
   @PostMapping("removeReview")
   @ResponseBody
   public String deleteReview(@RequestBody Integer reviewCode) {

      reviewService.removeReview(reviewCode);

      return "deleteReview";
   }

   // 리뷰 신고
   @PostMapping("reviewDeclare")
   @ResponseBody
   public String reviewDeclare(@RequestBody DeclareVO declareVO) {
      reviewService.addReviewDeclare(declareVO);
      return null;
   }

   // 리뷰 조회
   @GetMapping("reviewView")
   public String reviewView(Model model, String productCode, CodeVO codeVO, Criteria cri) {
      // 리뷰정보
      ReviewVO reviewVO = new ReviewVO();
      reviewVO.setProductCode(productCode);
      cri.setAmount(1);
      model.addAttribute("review", reviewService.getReviewList(reviewVO, cri));
      model.addAttribute("count", reviewService.countGetReview(reviewVO));
      model.addAttribute("reviewAvg", reviewService.starAvg(reviewVO));
      model.addAttribute("productCode", productCode);
      int totalReview = reviewService.countGetReview(reviewVO);
      model.addAttribute("pageMaker", new PageDTO(cri, totalReview));
      // 리뷰 신고
      reviewVO.setProductCode(productCode);
      model.addAttribute("codes", reviewService.reviewCodeList(codeVO));

      return "page/goods/review";

   }

   // qna 등록
   @PostMapping("insertInquiry")
   @ResponseBody
   public ProductInquiryVO addInquiry(Model model, @RequestBody ProductInquiryVO inquiryVO) {

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

      return inquiryVO;

   }

   // qna 삭제
   @PostMapping("removeInquiry")
   @ResponseBody
   public int removeInquiry(@RequestBody Integer queCode, RedirectAttributes rttr) {
      System.out.println(queCode);
      if (custominquiryService.removeInquiry(queCode)) {
         rttr.addFlashAttribute("result", "success");
      }
      return queCode;

   }

   // qna 조회
   @GetMapping("inquiryView")
   public String inquiryView(Criteria cri, Model model, String productCode) {

      ProductInquiryVO productInquiryVO = new ProductInquiryVO();
      productInquiryVO.setProductCode(productCode);
      cri.setAmount(10);
      model.addAttribute("inquiry", custominquiryService.getInquiryList(productInquiryVO, cri));
      model.addAttribute("inquiryCount", custominquiryService.countGetInquiry(productInquiryVO));
      int totalInquiry = custominquiryService.countGetInquiry(productInquiryVO);
      model.addAttribute("pageMaker", new PageDTO(cri, totalInquiry));
      model.addAttribute("productCode", productCode);
      return "page/goods/qna";
   }

   // 상품 단건 조회
   @GetMapping("goodDetail")
   public String getGoodDetail(String productCode, Model model, HttpSession session, ProductVO vo, OptionVO optionVO,
         CodeVO codeVO, Criteria cri) {

      // 상품정보
      ProductVO productVO = productService.goodsDetail(vo);
      model.addAttribute("goods", productVO);

      // 리뷰 별점
      ReviewVO reviewVO = new ReviewVO();
      reviewVO.setProductCode(productCode);
      model.addAttribute("reviewAvg", reviewService.starAvg(reviewVO));
      model.addAttribute("count", reviewService.countGetReview(reviewVO));

      // qna 수
      ProductInquiryVO productInquiryVO = new ProductInquiryVO();
      productInquiryVO.setProductCode(productCode);
      
      model.addAttribute("NqnaList", custominquiryService.qnaGetInq(productInquiryVO));
      model.addAttribute("inquiryCount", custominquiryService.countGetInquiry(productInquiryVO));

      // 옵션 조회
      optionVO.setProductCode(productCode);
      model.addAttribute("options", productService.getOptionList(optionVO));
      model.addAttribute("optionDetail", productService.getOptionDetail(optionVO));
      System.out.println(model);
      // 장바구니
      

      return "page/goods/goodDetail";
   }

   // 이미지 보여주기
   @GetMapping("/display")
   @ResponseBody
   public ResponseEntity<byte[]> getFile(String fileName) {
      File file = new File(uploadPath + fileName);

      ResponseEntity<byte[]> result = null;

      try {
         HttpHeaders header = new HttpHeaders();

         header.add("Content-Type", Files.probeContentType(file.toPath()));
         result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

      } catch (IOException e) {
         e.printStackTrace();
      }
      return result;
   }

   @PostMapping("insertImg")
   public String productdetailImg(Model model, ProductVO productVO, ImgsListVO imgsList, RedirectAttributes rttr) {
//      model.addAttribute("product", productVO);
      rttr.addFlashAttribute("product", productVO);
      rttr.addFlashAttribute("detailImg", imgsList);

      return "redirect:/insertProduct";
   }
   
   @PostMapping("updateImg")
   public String updatedetailImg(Model model, ProductVO productVO, ImgsListVO imgsList, RedirectAttributes rttr) {
      model.addAttribute("product", productVO);
      rttr.addFlashAttribute("product", productVO);
      rttr.addFlashAttribute("detailImg", imgsList);
      rttr.addFlashAttribute("productCode", productVO.getProductCode());
      System.out.println(productVO);
      return "redirect:/updateProduct?productCode=" + productVO.getProductCode();
   }
}