package com.ar.lighthouse.buyp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;

@Mapper
public interface BuyerPageMapper {
	
	public List<DetailVO> selectDetailList(String memberId);
	
	public BuyInfoVO selectBuyInfo(String memberId);

	public List<TradeVO> selectTradeList(String memberId);
	
	public List<CouponVO> selectCouponList(String memberId);
	
	public int updateInfo(BuyInfoVO buyInfoVO, String memberId);
	
	public List<MyInquiryVO> selectMyInquiryList(String memberId);
	
	public List<WishVO> selectWishList(String memberId);
}
