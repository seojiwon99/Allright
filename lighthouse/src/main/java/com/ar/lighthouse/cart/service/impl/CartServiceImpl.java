package com.ar.lighthouse.cart.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.cart.mapper.CartMapper;
import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.cart.service.CartUpdateVO;
import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.product.service.OptionDetailVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper cartMapper;
	
	@Override
	public List<CartVO> cartGetList(String memberId) {
		return cartMapper.selectCartList(memberId);
	}

	@Override
	public int removeCart(String memberId, int cartCode) {
		cartMapper.deleteCart(memberId, cartCode);
		return 0;
	}

	@Override
	public int addCart(CartVO cartVO) {
		return cartMapper.insertCart(cartVO);
	}

	@Override
	public OptionDetailVO getOptionCode(OptionDetailVO optionDetailVO) {
		return cartMapper.selectOptionCode(optionDetailVO);
	}

	@Override
	public int checkCart(CartVO vo) {
		return cartMapper.checkCart(vo);
	}

	@Override
	public int editCartCnt(CartUpdateVO cartUpdate) {
		return cartMapper.updateCartCnt(cartUpdate);
	}

}
