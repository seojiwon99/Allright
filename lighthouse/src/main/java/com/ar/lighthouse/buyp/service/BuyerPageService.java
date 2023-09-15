package com.ar.lighthouse.buyp.service;


import java.util.List;

public interface BuyerPageService {
	
	//주문 목록 가져오기
	public List<DetailVO> getDetailList(String memberId);
	
	//개인 정보 가져오기
	public BuyInfoVO getInfo(String memberId);
	
	//취소,반품,교환내역 가져오기
	public List<TradeVO> getTradeList(String memberId);
	
	//쿠폰 내역
	public List<CouponVO> getCouponList(String memberId);
	
	//개인 정보 수정
	public int editInfo(BuyInfoVO buyInfoVO, String memberId);
	
	//문의 내역
	public List<MyInquiryVO> getMyQuiryList(String memberId);

}
