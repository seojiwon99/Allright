package com.ar.lighthouse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.ImgsVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;

@Mapper
public interface ProductMapper {
	
	public List<ProductVO> selectProductList(ProductVO productVO);
	List<ProductVO> getProductsByMemberId(String memberId);
//	order by
	public List<ProductVO> selectOptionProduct(ProductVO productVO);
	
//	sellerInfo
	List<MemberVO> selectSellerInfo(MemberVO memberVO); 
	
	
//	등록
	public int insertProduct(ProductVO productVO);
	public int insertProduct(OptionVO optionVO);
	
//	사진등록
	public int insertImages(ImgsVO imgsVO);
	
//	수정
	public int modifyProduct(ProductVO productVO);
	
//	전시상태 수정
	public int updateExStatus(ProductVO productVO);
	
	//상품 단건조회페이지
	public ProductVO selectInfo(ProductVO productVO);

//	상품주문목록
	List<DetailVO> selectOrderDetail(ProductVO productVO);
	
//	주문목록 택배사 입력
	int updateDeliveryInfo(DetailVO detailVO);

//	취소건 확인
	public List<CancelVO> selectCancelList(CancelVO cancelVO);
	
//	교환건 확인
	public List<ExchangeVO> selectExchangeList(ExchangeVO exchangeVO);

//  반품건 확인
	public List<ReturnVO> selectReturnList(ReturnVO returnVO);


}