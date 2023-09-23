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
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
	
	@Autowired
	OrdersMapper ordersMapper;
	
	// 상품 주문 데이터 select
	@Override
	public OrdersVO getOrders(String memberId, int cartCode) {
		return ordersMapper.selectOrders(memberId, cartCode);
	}

	@Override
	//사용 기간 지난 쿠폰 삭제 후 Controller 전달
	public List<OrdersVO> getCoupon(String memberId) {
		List<OrdersVO> endDate = ordersMapper.selectCoupon(memberId);
		// 오라클 스케쥴러 활용 - 해볼것
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
				 ordersMapper.updatetNotCoupon(memberId, mycouponCode);
				
			 }
		}
		return ordersMapper.selectCoupon(memberId);
	}
	
	@Override
	// 사용한 쿠폰 N 데이터 변경
	public int editNotCoupon(String memberId, int mycouponCode) {
		return ordersMapper.updatetNotCoupon(memberId, mycouponCode);
	}
	
	@Override
	// 배송요청 사항 마스터 코드
	public List<CodeVO> getCode() {
		
		return ordersMapper.selectCode();
	}

	@Override
	//토스 페이먼트 데이터 DB저장
	public int addCredit(CreditVO creditVO) {
		return ordersMapper.insertCredit(creditVO);
	}

	@Override
	// 주문 총 결제 주문정보 insert
	public int addOrderPay(String memberId, OrderPayVO orderPayVO) {
		return ordersMapper.insertOrderPayVO(memberId, orderPayVO);
	}

	@Override
	// 주문 결제한 각 주문상품 상세정보
	public int addOrders(OrdersVO ordersVO) {
	int orderSuccess = ordersMapper.insertOrders(ordersVO);
		return ordersMapper.insertOrders(ordersVO);
	}

	@Override
	// 주문 상품 코드 파싱
	public int getOrderCode(String memberId) {
		return ordersMapper.selectOrderCode(memberId);
	}

	@Override
	// 주문 완료 후 장바구니 비우기
	public int removeCart(String memberId, int optionCode) {
		return ordersMapper.deleteCart(memberId, optionCode);
	}

}
