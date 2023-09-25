package com.ar.lighthouse.orders.service;

import java.util.List;

import com.ar.lighthouse.common.CodeVO;

public interface OrdersService {
	
	//주문 결제 페이지 데이터 전달
	public OrdersVO getOrders(String memberId, int cartCode);
	
	//구매자 쿠폰 데이터 전달
	public List<OrdersVO> getCoupon(String memberId);
	
	//배달 요청사항 코드 전달
	public List<CodeVO> getCode();
	
	//결제 시 결제DB 데이터 저장 데이터 전달 - 토스페이먼츠
	public int addCredit(CreditVO creditVO);
	
	//결제한 총 주문 테이블 DB 넣을 데이터
	public int addOrderPay(String memberId, DeliveryVO deliveryVO);
	
	//주문 상품 코드 파싱
	public int getOrderCode(String memberId);
	
	//사용한 쿠폰 N으로 변경
	public int editNotCoupon(String memberId, int mycouponCode);
	
	//각 주문 결제 상품별 insert
	public int addOrders(OrdersVO ordersVO);
	
	//주문 후 장바구니 삭제
	public int removeCart(String memberId, int optionCode);
}
