package com.ar.lighthouse.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.OrdersVO;

@Mapper
public interface OrdersMapper {

	public OrdersVO selectOrders(String memberId, int cartCode); 
	public List<OrdersVO> selectCoupon(String memberId);
	public List<CodeVO> selectCode();
	public int insertDeadCoupon(String memberId, int mycouponCode); 
	public int insertCredit(CreditVO creditVO);
}
