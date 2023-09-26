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
	
	//토스페이먼츠 결제 DB 데이터 저장
	public int addCredit(CreditVO creditVO);
	
	//토스페이먼츠 환불 시 필요 데이터 페이먼츠키, 환불금액 select
	public RefundVO getRefund(int orderCode, int orderDetailCode, String memberId);
	
	//토스페이먼츠 환불 DB 테이블 저장
	public int addRefund(RefundVO refundVO);
	
	//결제한 총 주문 테이블 DB 넣을 데이터
	public int addOrderPay(String memberId, DeliveryVO deliveryVO);
	
	//주문 상품 코드 파싱
	public int getOrderCode(String memberId);
	
	//사용한 쿠폰 N으로 변경
	public int editNotCoupon(String memberId, int mycouponCode);
	
	//환불된 쿠폰 Y으로 변경
	public int editRefundCoupon(String memberId, int mycouponCode);
	
	//각 주문 결제 상품별 insert
	public int addOrders(OrdersVO ordersVO);
	
	//주문 후 장바구니 삭제
	public int removeCart(String memberId, int cartNum);
	
	//환불 후 환불 가능 금액 credit 테이블 업데이트
	public int editTossRefundAmount(String paymentKey, Long refundAmount);
	
	//환불 후 환불 가능 금액 orders 테이블 업데이트
	public int editOrderRefundAmount(String paymentKey, Long refundAmount);
}
