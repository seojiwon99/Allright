package com.ar.lighthouse.buyp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.mapper.BuyerPageMapper;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;

import com.ar.lighthouse.buyp.service.BuyCancelVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.BuyExchangeVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.BuyReturnVO;

import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.Criteria;

@Service
public class BuyerPageServiceImpl implements BuyerPageService {

	@Autowired
	BuyerPageMapper buyerPageMapper;

	@Override
	public List<DetailVO> getDetailList(String memberId, Criteria cri) {
		return buyerPageMapper.selectDetailList(memberId, cri);
	}

	@Override
	public BuyInfoVO getInfo(String memberId) {
		return buyerPageMapper.selectBuyInfo(memberId);
	}

	@Override
	public List<TradeVO> getTradeList(String memberId) {
		return buyerPageMapper.selectTradeList(memberId);
	}

	@Override
	public int editInfo(BuyInfoVO buyInfoVO) {
		return buyerPageMapper.updateInfo(buyInfoVO);
	}

	@Override
	public List<CouponVO> getCouponList(String memberId, Criteria cri) {
		return buyerPageMapper.selectCouponList(memberId, cri);
	}

	@Override
	public List<MyInquiryVO> getMyQuiryList(String memberId, Criteria cri) {
		return buyerPageMapper.selectMyInquiryList(memberId, cri);
	}

	@Override
	public List<WishVO> getWishList(String memberId, Criteria cri) {
		return buyerPageMapper.selectWishList(memberId, cri);
	}

	@Override
	public List<BuyCancelVO> getCancelList(Criteria cri, String memberId) {
		return buyerPageMapper.selectCancelList(cri, memberId);
	}

	@Override
	public List<BuyReturnVO> getReturnList(Criteria cri, String memberId) {

		return buyerPageMapper.selectReturnList(cri, memberId);
	}

	@Override
	public List<BuyExchangeVO> getExchangeList(Criteria cri, String memberId) {
		return buyerPageMapper.selectExchangeList(cri, memberId);
	}

	@Override
	public int addExchange(BuyExchangeVO excVO) {
		return buyerPageMapper.insertExchange(excVO);
	}

	@Override
	public List<CodeVO> getExchangeCode(String memberId) {
		return buyerPageMapper.selectExchangeCode(memberId);
	}

	@Override
	public List<CodeVO> getReturnCode(String memberId) {
		return buyerPageMapper.selectReturnCode(memberId);
	}

	@Override
	public List<CodeVO> getCancelCode() {
		return buyerPageMapper.selectCancelCode();
	}

	@Override
	public CodeVO getExchangePage(CodeVO codeVO) {
		return buyerPageMapper.exchangeCodePage(codeVO);
	}

	@Override
	public CodeVO getReturnPage(CodeVO codeVO) {
		return buyerPageMapper.returnCodePage(codeVO);
	}

	@Override
	public CodeVO getCancelPage(CodeVO codeVO) {
		return buyerPageMapper.cancelCodePage(codeVO);
	}

	@Override
	public int addCancel(BuyCancelVO canVO) {
		return buyerPageMapper.insertCancel(canVO);
	}

	@Override
	public int addReturn(BuyReturnVO retVO) {
		return buyerPageMapper.insertReturn(retVO);
	}

	@Override
	public int removeCancel(BuyCancelVO canVO) {
		return buyerPageMapper.deleteCancel(canVO);
	}

	@Override
	public int removeReturn(BuyReturnVO retVO) {
		return buyerPageMapper.deleteReturn(retVO);
	}

	@Override
	public int removeExchange(BuyExchangeVO excVO) {
		return buyerPageMapper.deleteExchange(excVO);
	}

	@Override
	public int removeWish(int favoriteCode) {
		return buyerPageMapper.deleteWish(favoriteCode);
	}

	@Override
	public int getPageCnt(String memberId) {
		return buyerPageMapper.getPageCnt(memberId);
	}

	@Override
	public List<DetailVO> getOptionList(DetailVO detailVO) {
		return buyerPageMapper.selectOptionList(detailVO);
	}

	// 찜 등록
	@Override
	public int addWish(WishVO wishVO) {
		return buyerPageMapper.insertWish(wishVO);
	}

	@Override
	public int checkWish(WishVO wishVO) {
		return buyerPageMapper.checkWish(wishVO);
	}
  
  @Override
	public int getCouponCnt(String memberId) {
		return buyerPageMapper.getCouponCnt(memberId);
	}


	@Override
	public int getInqCnt(String memberId) {
		return buyerPageMapper.getInqCnt(memberId);
	}


	@Override
	public int getDetailCnt(String memberId) {
		return buyerPageMapper.selectDetailCnt(memberId);
	}

	@Override
	public int totalCancelCnt(String memberId) {
		return buyerPageMapper.totalCancelCnt(memberId);
	}

	@Override
	public int totalReturnCnt(String memberId) {
		return buyerPageMapper.totalReturnCnt(memberId);
	}

	@Override
	public int totalExchangeCnt(String memberId) {
		return buyerPageMapper.totalExchangeCnt(memberId);
	}



}
