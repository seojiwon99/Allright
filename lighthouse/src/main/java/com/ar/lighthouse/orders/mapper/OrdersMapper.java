package com.ar.lighthouse.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.orders.service.OrdersVO;

@Mapper
public interface OrdersMapper {

	public OrdersVO selectOrders(String memberId, int cartCode); 
	
}
