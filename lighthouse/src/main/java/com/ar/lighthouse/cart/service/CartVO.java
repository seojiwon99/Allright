package com.ar.lighthouse.cart.service;

import java.util.List;

import com.ar.lighthouse.product.service.OptionDetailVO;
import com.ar.lighthouse.product.service.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {

	private int cartCode;
	private int cartCount;

	private String memberId;

	// 옵션 조인해서 받은 값
	private int salePrice;
	private String optionLast;
	private int optionPrice;

	// 상품명 조인해서 받은 값
	private String productName;
	private int productCost;
	private int deliveryCost;

	private String productCode;
	
	//상품 이미지 조인해서 받은 값
	private String imgName;
	private String uploadName;
	private String uploadPath;

	// 상품을 장바구니로 보낼때 필요한 값
	private long optionDetailCode;
	List<ProductVO> pCode;
	private int maxOrder;
	private int minOrder;
	private int optionCount;

}
