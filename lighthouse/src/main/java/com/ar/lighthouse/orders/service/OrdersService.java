package com.ar.lighthouse.orders.service;

import java.util.List;

public interface OrdersService {

	public List<OrdersVO> getOrders(String memberId);
	
}
