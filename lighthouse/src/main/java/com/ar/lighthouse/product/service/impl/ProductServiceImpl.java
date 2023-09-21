package com.ar.lighthouse.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
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
		int result = productMapper.insertProduct(productVO);
		List<OptionVO> optionVO = new ArrayList();
		if(result > 0) {
			int length = 0;
			String code = productVO.getProductCode();
			// System.out.println(code);
			for(int i = 0; i< productVO.getOption().size(); i++) {
				String value = productVO.getOption().get(i).getOptionValue();
				String[] optVal = value.split(",");
				for(int j =0; j<optVal.length; j++) {
					
					OptionVO test = new OptionVO();
					test.setProductCode(code);
					test.setOptionOrder(length + 1);
					test.setOptionName(productVO.getOption().get(i).getOptionName());
					test.setOptionValue(optVal[j]);
					test.setOptionCount(1);
					length++;
					productMapper.insertOption(test);
				}
				;
				productVO.getOption().get(i).setProductCode(code);
				
				// System.out.println(productVO.getOption().get(i));
			}
			// productMapper.insertOption(productVO.getOption());
		}
		return 1;
	}

	// 상품이미지 등록
	@Override
	public void addProductImg(ImgsVO imgVO) {
		productMapper.insertProductImg(imgVO);
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


	@Override
	public List<OptionVO> getOptionList(OptionVO optionVO) {
		return productMapper.getOptionList(optionVO);
	}


	// 택배사 코드 가져오기
	@Override
	public List<CodeVO> getDeliveryList() {
		return productMapper.selectDeliveryList();
	}


	
	
}
