package com.ar.lighthouse.buyp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.mapper.BuyerPageMapper;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;
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
	

}
