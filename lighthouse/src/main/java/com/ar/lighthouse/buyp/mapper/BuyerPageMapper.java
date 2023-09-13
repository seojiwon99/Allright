package com.ar.lighthouse.buyp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.DetailVO;

@Mapper
public interface BuyerPageMapper {
	
	public List<DetailVO> selectDetailList(String memberId);
	
	public BuyInfoVO selectBuyInfo();

}
