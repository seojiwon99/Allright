package com.ar.lighthouse.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.cart.mapper.CartMapper;
import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.cart.service.CartVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper cartMapper;
	
	@Override
	public List<CartVO> cartGetList(String memberId) {
		return cartMapper.cartList(memberId);
	}

}
