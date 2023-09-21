package com.ar.lighthouse.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.mapper.ProductMapper;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper productMapper;
	


	@Override
	public List<ProductVO> getproductList(ProductVO productVO) {
		return  productMapper.selectProductList(productVO);
	}


	@Override
	public int updateExStatus(ProductVO productVO) {
		return productMapper.updateExStatus(productVO);
	}

//  order by list
	@Override
	public List<ProductVO> getOptionProduct(ProductVO productVO) {
		return productMapper.selectOptionProduct(productVO);
	}

//  상품테이블 등록
	@Override
	public int addProduct(ProductVO productVO) {
		return productMapper.insertProduct(productVO);
	}
//	옵션테이블 등록
	@Override
	public int addOption(OptionVO optionVO) {
		return productMapper.insertProduct(optionVO);
	}


	
	//제품 상세 단건조회
	@Override
	public ProductVO goodsDetail(ProductVO productVO) {
		return productMapper.selectInfo(productVO);
	}


	@Override
	public List<ProductVO> getProductsByMemberId(String memberId) {
		return productMapper.getProductsByMemberId(memberId);
	}


	@Override
	public List<MemberVO> getSellerInfo(MemberVO memberVO) {
		return productMapper.selectSellerInfo(memberVO);
	}

//	취소건 목록
	@Override
	public List<CancelVO> getCancelList(CancelVO cancelVO) {
		return productMapper.selectCancelList(cancelVO);
	}

//  주문/발주 목록
	@Override
	public List<DetailVO> getProductOrder(ProductVO productVO) {
		return productMapper.selectOrderDetail(productVO);
	}

// 교환건 목록
	@Override
	public List<ExchangeVO> getExchangeList(ExchangeVO exchangeVO) {
		return productMapper.selectExchangeList(exchangeVO);
	}


	@Override
	public List<ReturnVO> getReturnList(ReturnVO returnVO) {
		return productMapper.selectReturnList(returnVO);
	}


	@Override
	public int updateDeliveryInfo(DetailVO detailVO) {
		return productMapper.updateDeliveryInfo(detailVO);
	}

// orderManagement
	@Override
	public List<DetailVO> getOrderOptionList(DetailVO detailVO) {
		return productMapper.selectOrderOptionList(detailVO);
	}


	@Override
	public List<OptionVO> getOptionList(OptionVO optionVO) {
		return productMapper.getOptionList(optionVO);
	}




	
	
}
