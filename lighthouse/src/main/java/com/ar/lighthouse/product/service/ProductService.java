package com.ar.lighthouse.product.service;

import java.util.List;
import java.util.Map;

import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

public interface ProductService {
	
//	
	public List<ProductVO> getproductList(String memberId);
	
	List<MemberVO> getSellerInfo(String memberId);
	
//	수정폼
	public List<ProductVO> updateProduct(ProductVO productVO);
	
	public List<ProductVO> getOptionProduct(ProductVO productVO);
	
	public List<DetailVO> getStatusList(DetailVO detailVO);
	
//	전시상태변경
	public int updateExStatus(ProductVO productVO);
	
	// 상품등록	
	public int addProduct(ProductVO productVO);
	
	public int addOption(OptionVO optionVO);
	

	// 상품 이미지 등록
	public void addProductImg(ImgsVO imgVO);
	
//	상품수정
	public int updateProductP(ProductVO productVO);
//	상품 이미지 수정
	public void updateProductImg(ImgsVO imgVO);
	
//	orderManagement
	List<DetailVO> getOrderOptionList(DetailVO detailVO);
	
//	상품 문의건
	List<ProductInquiryVO> getProductInquiry(String memberId);
//	문의 검색
	List<ProductInquiryVO> getSeaInquiry(ProductInquiryVO productInquiryVO);
	
//  상품 문의 답변해주기
	int updateSellerInquiry(ProductInquiryVO productInquiryVO);
	
//	판매자 문의 내역
	List<MyInquiryVO> getSellerInquiry(String memberId);
//	판매자 문의 검색
	public List<MyInquiryVO> getSeaSellerInqu(MyInquiryVO myInquiryVO);
	
	//상품 단건 상세페이지
	public ProductVO goodsDetail(ProductVO productVO);

//	상품주문목록
	List<DetailVO> getProductOrder(String memberId);
	
//	주문배송정보수정
	public int updateDeliveryInfo(DetailVO detailVO);
	
//	취소건 목록
	List<CancelVO> getCancelList(String memberId);
	
//	교환건 목록
	List<ExchangeVO> getExchangeList(ExchangeVO exchangeVO);
	

//	cancelSearch
	List<CancelVO> getCancelSeaList(CancelVO cancelVO);
	
//	exchangeSearch
	List<ExchangeVO> getExchangeSeaList(ExchangeVO exchangeVO);
//	returnSearch
	List<ExchangeVO> getReturnSeaList(ExchangeVO exchangeVO);
//	주문상태수정
	public int updateOrderStatus(DetailVO detailVO);
	

//	반품건 목록
	List<ExchangeVO> getReturnList(ReturnVO returnVO);
	
//	정산건 목록
	List<SellerCalVO> getCalList(SellerCalVO sellerCalVO);
//	취소건 목록
	List<DetailVO> getStaticList(String memberId, String preBetw, String suBetw);

//	월별 주문 건수
	List<DetailVO> getMonthlyCount(DetailVO detailVO);

//  주문 판매자 직접 취소
	int deleteOrderSelf(DetailVO detailVO);
	

	//  옵션 VO 리스트
	public List<OptionVO> getOptionList(OptionVO optionVO);
	
	// 옵션 디테일
	public List<OptionDetailVO> getOptionDetail(OptionDetailVO optionDetailVO);
	
	// 택배사 코드 가져오기
	List<CodeVO> getDeliveryList();
	
	//취소 승인 시 승인 여부 Y로 변경 - 석연
	public int editCancelOk(String cancelCode);
	

	public int editReturnOk(String returnCode);

	// 신고 체크
	public Map<String, Object> sellerChk(String memberId);

	
}