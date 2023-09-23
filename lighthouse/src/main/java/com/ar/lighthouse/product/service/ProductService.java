package com.ar.lighthouse.product.service;

import java.util.List;

import com.ar.lighthouse.buyp.service.DetailVO;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;

import com.ar.lighthouse.member.service.MemberVO;

public interface ProductService {
	
//	
	public List<ProductVO> getproductList(ProductVO productVO);
	List<ProductVO> getProductsByMemberId(String memberId);
	
	List<MemberVO> getSellerInfo(MemberVO memberVO);
	
	public List<ProductVO> getOptionProduct(ProductVO productVO);
	
//	전시상태변경
	public int updateExStatus(ProductVO productVO);
	
	// 상품등록	
	public int addProduct(ProductVO productVO);
	public int addOption(OptionVO optionVO);
	
	// 상품 이미지 등록
	public void addProductImg(ImgsVO imgVO);
	
//	orderManagement
	List<DetailVO> getOrderOptionList(DetailVO detailVO);
	
	//상품 단건 상세페이지
	public ProductVO goodsDetail(ProductVO productVO);

//	상품주문목록
	List<DetailVO> getProductOrder(ProductVO productVO);
	
//	주문배송정보수정
	public int updateDeliveryInfo(DetailVO detailVO);
	
//	취소건 목록
	List<CancelVO> getCancelList(CancelVO cancelVO);
	
//	교환건 목록
	List<ExchangeVO> getExchangeList(ExchangeVO exchangeVO);
	

//	cancelSearch
	List<CancelVO> getCancelSeaList(CancelVO cancelVO);
	
//	exchangeSearch
	List<ExchangeVO> getExchangeSeaList(ExchangeVO exchangeVO);
//	returnSearch
	List<ReturnVO> getReturnSeaList(ReturnVO returnVO);
//	주문상태수정
	public int updateOrderStatus(DetailVO detailVO);
	

//	반품건 목록
	List<ReturnVO> getReturnList(ReturnVO returnVO);
	
//	정산건 목록
	List<SellerCalVO> getCalList(SellerCalVO sellerCalVO);
	
	
	
	
	//  옵션 VO 리스트
	public List<OptionVO> getOptionList(OptionVO optionVO);
	
	// 택배사 코드 가져오기
	List<CodeVO> getDeliveryList();
}
