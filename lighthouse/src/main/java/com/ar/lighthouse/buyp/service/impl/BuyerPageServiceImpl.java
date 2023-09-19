package com.ar.lighthouse.buyp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.mapper.BuyerPageMapper;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.CancelVO;
import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.ExchangeVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.ReturnVO;
import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;

import co.elastic.clients.elasticsearch.ml.Page;
@Service
public class BuyerPageServiceImpl implements BuyerPageService {
	
	@Autowired
	BuyerPageMapper buyerPageMapper;
	
	
	@Override
	public List<DetailVO> getDetailList(String memberId) {
		return buyerPageMapper.selectDetailList(memberId);
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
	public int editInfo(BuyInfoVO buyInfoVO, String memberId) {
		return buyerPageMapper.updateInfo(buyInfoVO, memberId);
	}


	@Override
	public List<CouponVO> getCouponList(String memberId) {
		return buyerPageMapper.selectCouponList(memberId);
	}


	@Override
	public List<MyInquiryVO> getMyQuiryList(String memberId) {
		return buyerPageMapper.selectMyInquiryList(memberId);
	}


	@Override
	public List<WishVO> getWishList(String memberId) {
		return buyerPageMapper.selectWishList(memberId);
	}


	@Override
	public List<CancelVO> getCancelList(String memberId) {
		return buyerPageMapper.selectCancelList(memberId);
	}


	@Override
	public List<ReturnVO> getReturnList(String memberId) {
		return buyerPageMapper.selectReturnList(memberId);
	}


	@Override
	public List<ExchangeVO> getExchangeList(String memberId) {
		return buyerPageMapper.selectExchangeList(memberId);
	}


	@Override
	public int addExchange(ExchangeVO excVO) {
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
	public List<CodeVO> getCancelCode(String memberId) {
		return buyerPageMapper.selectCancelCode(memberId);
	}
	
	
	

}
