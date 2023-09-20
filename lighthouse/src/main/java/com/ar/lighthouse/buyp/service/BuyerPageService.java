package com.ar.lighthouse.buyp.service;

import java.util.List;

public interface BuyerPageService {

	// 주문 목록 가져오기
	public List<DetailVO> getDetailList(String memberId);

	// 개인 정보 가져오기
	public BuyInfoVO getInfo(String memberId);

	// 취소,반품,교환내역 가져오기
	public List<TradeVO> getTradeList(String memberId);

	// 쿠폰 내역
	public List<CouponVO> getCouponList(String memberId);

	// 개인 정보 수정
	public int editInfo(BuyInfoVO buyInfoVO, String memberId);

	// 문의 내역
	public List<MyInquiryVO> getMyQuiryList(String memberId);

	// 찜 내역
	public List<WishVO> getWishList(String memberId);

	// 취소 상세
	public List<BuyCancelVO> getCancelList(String memberId);

	// 반품 상세
	public List<BuyReturnVO> getReturnList(String memberId);

	// 교환 상세
	public List<BuyExchangeVO> getExchangeList(String memberId);

	// 교환 신청
	public int addExchange(BuyExchangeVO excVO);

	// 교환 코드
	public List<CodeVO> getExchangeCode(String memberId);
	
	// 교환 페이지에 데이터 넘김
	public CodeVO getExchangePage(CodeVO codeVO);

	// 반품 코드
	public List<CodeVO> getReturnCode(String memberId);

	// 반품 페이지에 데이터 넘김
	public CodeVO getReturnPage(CodeVO codeVO);
	
	// 취소 코드
	public List<CodeVO> getCancelCode(String memberId);
	
	//취소 페이지에 데이터 넘김
	public CodeVO getCancelPage(CodeVO codeVO);
	
	//취소 신청
	public int addCancel(BuyCancelVO canVO);
}
