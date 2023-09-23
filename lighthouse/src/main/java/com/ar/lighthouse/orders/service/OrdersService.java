package com.ar.lighthouse.orders.service;

import java.util.List;

import com.ar.lighthouse.buyp.service.CodeVO;

public interface OrdersService {

	public OrdersVO getOrders(String memberId, int cartCode);
	
	public List<OrdersVO> getCoupon(String memberId);
	
	public List<CodeVO> getCode();
	
	
	
}
