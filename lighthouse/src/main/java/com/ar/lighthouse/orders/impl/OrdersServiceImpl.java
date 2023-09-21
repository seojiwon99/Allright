package com.ar.lighthouse.orders.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.orders.mapper.OrdersMapper;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
	
	@Autowired
	OrdersMapper ordersMapper;
	
	@Override
	public OrdersVO getOrders(String memberId, int cartCode) {
		
		return ordersMapper.selectOrders(memberId, cartCode);
	}

	@Override
	public List<OrdersVO> getCoupon(String memberId) {
		List<OrdersVO> endDate = ordersMapper.selectCoupon(memberId);
		
		LocalDate accuseDate = LocalDate.now();
		String sysDate = accuseDate.format(DateTimeFormatter.ofPattern("yy/MM/dd"));
		for(int i =0; i<endDate.size(); i++) {
			 Date endDateDate = endDate.get(i).getEndDate();
			 String endDateStr = simpleDateFormat.format(endDateDate);
			 
			 if(endDateStr.compareTo(sysDate) >= 0 ) {
				 // 날짜 비교 기준 날짜 보다 endDateStr이 현재보다 같거나 이전날짜 		 
			 } else {
				 // endDateStr이 현재보다 지난 날짜 -- 기간 지날 날짜는 안보이게함.
				 int mycouponCode = endDate.get(i).getMycouponCode();
				 ordersMapper.insertDeadCoupon(memberId, mycouponCode);
				
			 }
		}
		return ordersMapper.selectCoupon(memberId);
	}

	@Override
	public List<CodeVO> getCode() {
		
		return ordersMapper.selectCode();
	}

}
