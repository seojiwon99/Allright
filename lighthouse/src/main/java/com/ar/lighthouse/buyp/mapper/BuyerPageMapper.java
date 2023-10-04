package com.ar.lighthouse.buyp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.BuyCancelVO;
import com.ar.lighthouse.buyp.service.BuyExchangeVO;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyReturnVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.Criteria;

@Mapper
public interface BuyerPageMapper {
	
	public List<DetailVO> selectDetailList(String memberId, Criteria cri);
	
	public BuyInfoVO selectBuyInfo(String memberId);

	public List<TradeVO> selectTradeList(String memberId);
	
	public List<CouponVO> selectCouponList(String memberId, Criteria cri);
	
	public int updateInfo(BuyInfoVO buyInfoVO);
	
	public List<MyInquiryVO> selectMyInquiryList(String memberId, Criteria cri);
	
	public List<WishVO> selectWishList(String memberId, Criteria cri);

	public List<BuyCancelVO> selectCancelList(String memberId);
	
	public List<BuyReturnVO> selectReturnList(String memberId);
	
	public List<BuyExchangeVO> selectExchangeList(String memberId);
	
	public int insertExchange(BuyExchangeVO excVO);

	public List<CodeVO> selectExchangeCode(String memberId);
	
	public CodeVO exchangeCodePage(CodeVO codeVO);
	
	public List<CodeVO> selectReturnCode(String memberId);
	
	public CodeVO returnCodePage(CodeVO codeVO);
	
	public int insertReturn(BuyReturnVO retVO);
	
	public List<CodeVO> selectCancelCode();
	
	public CodeVO cancelCodePage(CodeVO codeVO);
	
	public int insertCancel(BuyCancelVO canVO);
	
	public int deleteCancel(BuyCancelVO canVO);
	
	public int deleteReturn(BuyReturnVO retVO);
	
	public int deleteExchange(BuyExchangeVO excVO);
	
	public int deleteWish(int favoriteCode);

	public int getPageCnt(Criteria cri);
	
	public List<DetailVO> selectOptionList(DetailVO detailVO);
	
	public int getCouponCnt(Criteria cri);
	
	public int getInqCnt(Criteria cri);
	
	public int selectPageList(Criteria cri);
	
	public int selectDetailCnt(Criteria cri);
	
}
