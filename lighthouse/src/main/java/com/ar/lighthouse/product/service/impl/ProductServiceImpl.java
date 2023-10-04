package com.ar.lighthouse.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.common.ImgsVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.mapper.ProductMapper;
import com.ar.lighthouse.product.service.CancelVO;
import com.ar.lighthouse.product.service.ExchangeVO;
import com.ar.lighthouse.product.service.OptionDetailVO;
import com.ar.lighthouse.product.service.OptionVO;
import com.ar.lighthouse.product.service.ProductService;
import com.ar.lighthouse.product.service.ProductVO;
import com.ar.lighthouse.product.service.ReturnVO;
import com.ar.lighthouse.product.service.SellerCalVO;
import com.ar.lighthouse.productinquiry.service.ProductInquiryVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;

	@Override
	public List<ProductVO> getproductList(String memberId) {
		return productMapper.selectProductList(memberId);
	}

	@Override
	public int updateExStatus(ProductVO productVO) {
		return productMapper.updateExStatus(productVO);
	}

//  order by list

	@Override
	public List<ProductVO> getOptionProduct(String memberId) {
		return productMapper.selectOptionProduct(memberId);
	}

//  상품테이블 등록
//  상품테이블 등록
	@Override
	public int addProduct(ProductVO productVO) {
		int result = productMapper.insertProduct(productVO);
		List<OptionVO> optionVO = new ArrayList();
		if (result > 0) {
			String code = productVO.getProductCode();
			if (productVO.getOption() == null || productVO.getOption().size() == 0) {
				System.out.println("@@@@@@@@@@@@@@@@");
				OptionVO noptionVO = new OptionVO();
				noptionVO.setProductCode(code);
				noptionVO.setOptionName("없음");
				noptionVO.setOptionValue("없음");

				productMapper.insertOption(noptionVO);
			} else {
				for (int i = 0; i < productVO.getOption().size(); i++) {

//               if(productVO.getOption().get(i).getOptionCount() == 0) {
//                  productVO.getOption().get(i).setOptionSellStatus("N");
//               }
					// value 짜르기
//            String value = productVO.getOption().get(i).getOptionValue();
//            String[] optVal = value.split(",");
//            for(int j =0; j<optVal.length; j++) {
//               
//               OptionVO test = new OptionVO();
//               test.setProductCode(code);
//               test.setOptionOrder(length + 1);
//               test.setOptionName(productVO.getOption().get(i).getOptionName());
//               test.setOptionValue(optVal[j]);
//               test.setOptionCount(1);
//               length++;
//               productMapper.insertOption(test);
//            }
					// System.out.println(productVO.getOption().get(i));
					productVO.getOption().get(i).setProductCode(code);
					System.out.println(productVO.getOption().get(i));
					productMapper.insertOption(productVO.getOption().get(i));

					// System.out.println(productVO.getOption().get(i));
				}
				if (productVO.getOptionDetail() != null || productVO.getOptionDetail().size() != 0) {
					for (int i = 0; i < productVO.getOptionDetail().size(); i++) {
						productVO.getOptionDetail().get(i).setProductCode(code);
						productMapper.insertOptionDetail(productVO.getOptionDetail().get(i));
					}
				} else {
					OptionDetailVO detailVO = new OptionDetailVO();
					detailVO.setProductCode(code);
					detailVO.setOptionLast("없음");
					detailVO.setOptionPrice(0);
					detailVO.setOptionCount(productVO.getProductCount());
					productMapper.insertOptionDetail(detailVO);

				}

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

	// 제품 상세 단건조회
	@Override
	public ProductVO goodsDetail(ProductVO productVO) {
		return productMapper.selectInfo(productVO);
	}

	@Override
	public List<MemberVO> getSellerInfo(String memberId) {
		return productMapper.selectSellerInfo(memberId);
	}

//		취소건 목록
	@Override
	public List<CancelVO> getCancelList(String memberId) {
		return productMapper.selectCancelList(memberId);
	}

	// 주문/발주 목록
	@Override
	public List<DetailVO> getProductOrder(String memberId) {
		return productMapper.selectOrderDetail(memberId);
	}

	// 교환건 목록
	@Override
	public List<ExchangeVO> getExchangeList(ExchangeVO exchangeVO) {
		return productMapper.selectExchangeList(exchangeVO);
	}

	@Override
	public List<ExchangeVO> getReturnList(ReturnVO returnVO) {
		return productMapper.selectReturnList(returnVO);
	}

	@Override
	public int updateDeliveryInfo(DetailVO detailVO) {
		return productMapper.updateDeliveryInfo(detailVO);
	}

	@Override
	public List<OptionVO> getOptionList(OptionVO optionVO) {
		List<OptionVO> list = productMapper.selectOptionList(optionVO);
		for (OptionVO i : list) {
			String[] op = i.getOptionValue().split(",");
			List<OptionDetailVO> detailList = new ArrayList<OptionDetailVO>();
			for (String o : op) {
				detailList.add(new OptionDetailVO(o.trim()));
			}
			i.setDetailVO(detailList);
		}
		return list;
	}

	// 택배사 코드 가져오기
	@Override
	public List<CodeVO> getDeliveryList() {
		return productMapper.selectDeliveryList();
	}

	@Override
	public List<ProductInquiryVO> getProductInquiry(String memberId) {
		return productMapper.selectSellerInquiry(memberId);
	}

	@Override
	public int updateSellerInquiry(ProductInquiryVO productInquiryVO) {
		return productMapper.updateInquiryAns(productInquiryVO);
	}

	// orderManagement
	@Override
	public List<DetailVO> getOrderOptionList(DetailVO detailVO) {
		return productMapper.selectOrderOptionList(detailVO);
	}

//      주문상태변경
	@Override
	public int updateOrderStatus(DetailVO detailVO) {
		return productMapper.updateOrderStatus(detailVO);
	}

//      취소 검색
	@Override
	public List<CancelVO> getCancelSeaList(CancelVO cancelVO) {
		return productMapper.cancelOptionList(cancelVO);
	}

//      교환검색
	@Override
	public List<ExchangeVO> getExchangeSeaList(ExchangeVO exchangeVO) {
		return productMapper.exchangeSeaList(exchangeVO);
	}

//      반품검색
	@Override
	public List<ExchangeVO> getReturnSeaList(ExchangeVO exchangeVO) {
		return productMapper.returnSeaList(exchangeVO);
	}

	// 정산건 확인
	@Override
	public List<SellerCalVO> getCalList(SellerCalVO sellerCalVO) {
		return productMapper.selectCalList(sellerCalVO);
	}

	@Override
	public int addOption(OptionVO optionVO) {
		return 0;
	}

//	취소건 목록
	@Override
	public List<DetailVO> getStaticList(String memberId) {
		return productMapper.selectStatsList(memberId);
	}

	@Override
	public List<OptionDetailVO> getOptionDetail(OptionVO optionVO) {
		return productMapper.selectOptionDetail(optionVO);
	}

	@Override
	public int updateCancelList(CancelVO cancelVO) {
		return productMapper.updateCancelList(cancelVO);
	}
	
	// 석연 - 취소 완료 시 Y로 상태 변경
    @Override
	public int editCancelOk(String cancelCode) {
		return productMapper.updateCancelOk(cancelCode);

	}

}