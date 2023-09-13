package com.ar.lighthouse.buyp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.mapper.BuyerPageMapper;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.DetailVO;
@Service
public class BuyerPageServiceImpl implements BuyerPageService {
	
	@Autowired
	BuyerPageMapper buyerPageMapper;
	
	
	@Override
	public List<DetailVO> getDetailList(String memberId) {
		return buyerPageMapper.selectDetailList(memberId);
	}

	@Override
	public BuyInfoVO getInfo() {
		return buyerPageMapper.selectBuyInfo();
	}


}
