package com.ar.lighthouse.buyp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.BuyInfoVO;

import com.ar.lighthouse.buyp.service.BuyCancelVO;
import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.BuyExchangeVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.BuyReturnVO;

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

	public List<BuyCancelVO> selectCancelList(String memberId);
	
	public List<BuyReturnVO> selectReturnList(String memberId);
	
	public List<BuyExchangeVO> selectExchangeList(String memberId);
	
	public int insertExchange(BuyExchangeVO excVO);

	
	public List<CodeVO> selectExchangeCode(String memberId);
	
	public CodeVO exchangeCodePage(CodeVO codeVO);
	
	public List<CodeVO> selectReturnCode(String memberId);
	
	public CodeVO returnCodePage(CodeVO codeVO);
	
	public List<CodeVO> selectCancelCode(String memberId);
	
	public CodeVO cancelCodePage(CodeVO codeVO);
	
	public int insertCancel(BuyCancelVO canVO);
}
