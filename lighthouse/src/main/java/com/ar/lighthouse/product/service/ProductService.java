package com.ar.lighthouse.product.service;

import java.util.List;

import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

public interface ProductService {
	
//	
	public List<ProductVO> getproductList(String memberId);
	
	List<MemberVO> getSellerInfo(String memberId);
	
	public List<ProductVO> getOptionProduct(String memberId);
	
//	전시상태변경
	public int updateExStatus(ProductVO productVO);
	
	// 상품등록	
	public int addProduct(ProductVO productVO);
	
	public int addOption(OptionVO optionVO);
	
	// 상품 이미지 등록
	public void addProductImg(ImgsVO imgVO);
	
//	orderManagement
	List<DetailVO> getOrderOptionList(DetailVO detailVO);
	
//	판매자 상품 문의건
	List<ProductInquiryVO> getProductInquiry(String memberId);
	
//  상품 문의 답변해주기
	int updateSellerInquiry(ProductInquiryVO productInquiryVO);
	
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
	List<DetailVO> getStaticList(String memberId);
	
	
	//  옵션 VO 리스트
	public List<OptionVO> getOptionList(OptionVO optionVO);
	
	// 옵션 디테일
	public List<OptionDetailVO> getOptionDetail(OptionVO optionVO);
	
	// 택배사 코드 가져오기
	List<CodeVO> getDeliveryList();
	
	//취소 승인 시 승인 여부 Y로 변경 - 석연
	public int editCancelOk(String cancelCode);
}