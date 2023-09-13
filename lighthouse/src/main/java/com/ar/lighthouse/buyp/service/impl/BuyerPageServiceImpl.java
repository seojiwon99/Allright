package com.ar.lighthouse.buyp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.mapper.BuyerPageMapper;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.TradeVO;
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


}
