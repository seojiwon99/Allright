package com.ar.lighthouse.cart.service;

import java.util.List;

import com.ar.lighthouse.product.service.OptionDetailVO;

public interface CartService {

	List<CartVO> cartGetList(String memberId);

	int removeCart(String memberId, int cartCode);

	//cart로
	public int addCart(CartVO cartVO);
	
	//cart 옵션 디테일코드
	public OptionDetailVO getOptionCode(OptionDetailVO optionDetailVO);
	
	//cart 수
	public int checkCart(CartVO vo);
}
