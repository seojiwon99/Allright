package com.ar.lighthouse.orders.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.orders.mapper.OrdersMapper;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	OrdersMapper ordersMapper;
	
	@Override
	public OrdersVO getOrders(String memberId, int cartCode) {
		
		return ordersMapper.selectOrders(memberId, cartCode);
	}

	@Override
	public List<OrdersVO> getCoupon(String memberId) {
		
		return ordersMapper.selectCoupon(memberId);
	}

}
