package com.ar.lighthouse.cart.service;

import java.util.List;

public interface CartService {
	
	List<CartVO> cartGetList(String memberId);
	int removeCart(String memberId, int cartCode);
}
