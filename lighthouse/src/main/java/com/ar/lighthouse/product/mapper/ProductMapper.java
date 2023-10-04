package com.ar.lighthouse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.OptionDetailVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;
import com.ar.lighthouse.product.service.SellerCalVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

@Mapper
public interface ProductMapper {

	public List<ProductVO> selectProductList(String memberId);

//		order by
	public List<ProductVO> selectOptionProduct(String memberId);

//		sellerInfo
	List<MemberVO> selectSellerInfo(String memberId);

//		orderManagement
	List<DetailVO> selectOrderOptionList(DetailVO detailVO);

//		cancelO
	List<CancelVO> cancelOptionList(CancelVO cancelVO);

//		return,exchange 조건검색
	List<ExchangeVO> exchangeSeaList(ExchangeVO exchangeVO);

	List<ExchangeVO> returnSeaList(ExchangeVO exchangeVO);

	// 옵션 등록
	public int insertOption(OptionVO optionVO);

	// 옵션 상세등록
	public int insertOptionDetail(OptionDetailVO optionDetailVO);

	// 상품이미지 등록
	public void insertProductImg(ImgsVO imgsVO);

//		등록
	public int insertProduct(ProductVO productVO);

//		사진등록
	public int insertImages(ImgsVO imgsVO);

//		수정
	public int modifyProduct(ProductVO productVO);

//		전시상태 수정
	public int updateExStatus(ProductVO productVO);

	// 상품 단건조회페이지
	public ProductVO selectInfo(ProductVO productVO);

//		상품주문목록
	List<DetailVO> selectOrderDetail(String memberId);

//		주문상태 변경
	int updateOrderStatus(DetailVO detailVO);

//		나의 상품에 온 문의	
	List<ProductInquiryVO> selectSellerInquiry(String memberId);

//		문의 답변 주기
	int updateInquiryAns(ProductInquiryVO productInquiryVO);

//		주문목록 택배사 입력
	int updateDeliveryInfo(DetailVO detailVO);

//		취소건 확인
	public List<CancelVO> selectCancelList(String memberId);

//		취소 거부
	int updateCancelList(CancelVO cancelVO);

//		교환건 확인
	public List<ExchangeVO> selectExchangeList(ExchangeVO exchangeVO);

	// 반품건 확인
	public List<ExchangeVO> selectReturnList(ReturnVO returnVO);

//		정산데이터
	public List<SellerCalVO> selectCalList(SellerCalVO sellerCalVO);

//		통계 목록
	public List<DetailVO> selectStatsList(String memberId);

	// 상품옵션 vo 리스트
	public List<OptionVO> getOptionList(OptionVO optionVO);

	// 택배사 목록 가졍괴
	public List<CodeVO> selectDeliveryList();

	// 옵션리스트
	public List<OptionVO> selectOptionList(OptionVO optionVO);

	public List<OptionDetailVO> selectOptionDetail(OptionVO optionVO);
	
	//취소 승인 시 Y로 승인 상태 변경 - 석연
	public int updateCancelOk(String cancelCode);

}