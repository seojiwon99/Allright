package com.ar.lighthouse.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.DeliveryVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersVO;

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
	 
	
	//토스페이먼츠 결제 DB 데이터 저장
	public int insertCredit(CreditVO creditVO);
	
	//주문 테이블 DB 저장
	public int insertOrderPayVO(@Param("memberId") String memberId,@Param("DeliveryVO") DeliveryVO deliveryVO);
	
	//주문 코드 파싱
	public int selectOrderCode(String memberId);
	
	//각 상품 주문 상세 내역 insert
	public int insertOrders(@Param("OrdersVO") OrdersVO ordersVO);
	
	//주문 결제 시 장바구니 비우기
	public int deleteCart(String memberId, int optionCode);
	
}
