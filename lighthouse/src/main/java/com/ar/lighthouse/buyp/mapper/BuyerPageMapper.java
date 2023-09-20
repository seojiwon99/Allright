package com.ar.lighthouse.buyp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerCancelVO;
import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.ExchangeVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.ReturnVO;
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
	
	public List<BuyerCancelVO> selectCancelList(String memberId);
	
	public List<ReturnVO> selectReturnList(String memberId);
	
	public List<ExchangeVO> selectExchangeList(String memberId);
	
	public int insertExchange(ExchangeVO excVO);
	
	public List<CodeVO> selectExchangeCode(String memberId);
	
	public List<CodeVO> selectCancelCode(String memberId);
	
	public List<CodeVO> selectReturnCode(String memberId);
}
