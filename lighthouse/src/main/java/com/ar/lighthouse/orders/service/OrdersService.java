package com.ar.lighthouse.orders.service;

import java.util.List;

public interface OrdersService {

	public OrdersVO getOrders(String memberId, int cartCode);
	
	public List<OrdersVO> getCoupon(String memberId);
	
}
