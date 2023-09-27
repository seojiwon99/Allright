package com.ar.lighthouse.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.DeliveryVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersVO;
import com.ar.lighthouse.orders.service.RefundVO;

@Mapper
public interface OrdersMapper {
	
	//장바구니에서 주문페이지 이동 후 필요 데이터 가져옴
	public OrdersVO selectOrders(String memberId, int cartCode); 
	
	//구매자 사용 가능 쿠폰 가져옴
	public List<OrdersVO> selectCoupon(String memberId);
	
	//배송 요청사항 코드 가져옴
	public List<CodeVO> selectCode();
	
	//사용기한 지난 쿠폰 사용N으로 변경 사용불가 구현, 결제 시 사용한 쿠폰 N으로 변경
	public int updatetNotCoupon(String memberId, int mycouponCode); 
	 
	//환불 받은 상품의 쿠폰 Y로 사용 가능 변경
	public int updateRefundCoupon(String memberId, int mycouponCode);
	
	//토스페이먼츠 결제 DB 데이터 저장
	public int insertCredit(CreditVO creditVO);
	
	//토스페이먼츠 환불 시 필요 데이터 페이먼츠키, 환불금액 select
	public RefundVO selectRefund(int orderCode, int orderDetailCode, String memberId);
	
	//토스페이먼츠 환불 DB 데이터 저장
	public int insertRefund(RefundVO refundVO);
	
	//주문 테이블 DB 저장
	public int insertOrderPayVO(@Param("memberId") String memberId,@Param("DeliveryVO") DeliveryVO deliveryVO);
	
	//주문 코드 파싱
	public int selectOrderCode(String memberId);
	
	//각 상품 주문 상세 내역 insert
	public int insertOrders(@Param("OrdersVO") OrdersVO ordersVO);
	
	//주문 결제 시 장바구니 비우기
	public int deleteCart(String memberId, int cartNum);
	
	//환불 credit 테이블 환불 가능 금액 업데이트
	public int updateTossRefundAmount(String paymentKey, int refundAmount);
	
	//환불 order 테이블 환불 가능 금액 업데이트
	public int updateOrderRefundAmount(int orderCode, int refundAmount);
}
