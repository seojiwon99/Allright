package com.ar.lighthouse.buyp.service;

import java.util.List;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.Criteria;

public interface BuyerPageService {

	// 주문 목록 가져오기
	public List<DetailVO> getDetailList(String memberId, Criteria cri);
	
	// 주문 갯수 가져오기 (페이징)
	public int getDetailCnt(String memberId);

	// 개인 정보 가져오기
	public BuyInfoVO getInfo(String memberId);

	// 취소,반품,교환내역 가져오기
	public List<TradeVO> getTradeList(String memberId);

	// 쿠폰 내역
	public List<CouponVO> getCouponList(String memberId, Criteria cri);

	// 개인 정보 수정
	public int editInfo(BuyInfoVO buyInfoVO);

	// 문의 내역
	public List<MyInquiryVO> getMyQuiryList(String memberId, Criteria cri);

	// 찜 내역
	public List<WishVO> getWishList(String memberId, Criteria cri);

	// 취소 상세
	public List<BuyCancelVO> getCancelList(Criteria cri,String memberId);

	// 반품 상세
	public List<BuyReturnVO> getReturnList(Criteria cri,String memberId);

	// 교환 상세
	public List<BuyExchangeVO> getExchangeList(Criteria cri,String memberId);

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

	// 반품 신청
	public int addReturn(BuyReturnVO retVO);

	// 취소 코드
	public List<CodeVO> getCancelCode();

	// 취소 페이지에 데이터 넘김
	public CodeVO getCancelPage(CodeVO codeVO);

	// 취소 신청
	public int addCancel(BuyCancelVO canVO);

	// 주문 취소
	public int removeCancel(BuyCancelVO canVO);

	// 반품 취소
	public int removeReturn(BuyReturnVO retVO);

	// 교환 취소
	public int removeExchange(BuyExchangeVO excVO);

	// 찜 취소
	public int removeWish(int favoriteCode);

	// 전체 갯수 가져오기..?
	public int getPageCnt(String memberId);

	// 주문 목록 option 선택
	public List<DetailVO> getOptionList(DetailVO detailVO, Criteria cri);

	// 찜 insert
	public int addWish(WishVO wishVO);

	// 찜 중복체크
	public int checkWish(WishVO wishVO);

	//쿠폰 전체 갯수
	public int getCouponCnt(String memberId);
	
	//문의 전체 갯수
	public int getInqCnt(String memberId);
	
	public int totalCancelCnt(String memberId);
	
	public int totalReturnCnt(String memberId);
	
	public int totalExchangeCnt(String memberId);
	
	public int getOptionCnt(DetailVO detailVO);
}
