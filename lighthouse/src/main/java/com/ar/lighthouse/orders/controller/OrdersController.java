package com.ar.lighthouse.orders.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.DeliveryVO;
import com.ar.lighthouse.orders.service.OrderDeliveryVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;
import com.ar.lighthouse.orders.service.RefundVO;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	

	
	// 장바구니에서 구매 상품 가져옴.
	@PostMapping("orders/pay")
	public String orderList(HttpServletRequest request, Model model, @RequestParam (name = "cartCode") int[] cartCode) {
		
		System.out.println(cartCode);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		
		List<CodeVO> codeList = ordersService.getCode();
		
		int[] lists = cartCode;
		System.out.println(cartCode);
		List<OrdersVO> orderGet = new ArrayList<OrdersVO>();
		for(int cartNum : lists)  {
			orderGet.add((ordersService.getOrders(memberId, cartNum)));  
		}
		int productNum = 0;
		String productName = "";
		if(orderGet.size() != 1) {
		productNum = orderGet.size()-1;
			productName = orderGet.get(0).getProductName()  + " 외 " + productNum + "건";
		} else {
			productName = orderGet.get(0).getProductName();	
		}
		model.addAttribute("productName",productName);
		model.addAttribute("orderGet", orderGet);
		session.setAttribute("orderGet", orderGet);
		
		List<OrdersVO> couponList = ordersService.getCoupon(memberId);
		session.setAttribute("couponList", couponList);
		model.addAttribute("couponList",couponList);
		
		// 배송지 마스터 코드 전송
		model.addAttribute("codeList",codeList);
		 
		return "/page/orders/ordersPay";
	}
	
	//배송지, 배송 주문 테이블 Session 저장
	@PostMapping("orders/save")
	@ResponseBody
	public void ordersSave(HttpServletRequest req, @RequestBody OrderDeliveryVO orderDeliveryVO) { //RequestBody는 한번만 사용가능. 여러 값 받을 VO 필요
		HttpSession session = req.getSession();
		session.setAttribute("orderPayVO", orderDeliveryVO.getOrderPayVO());
		session.setAttribute("deliveryVO", orderDeliveryVO.getDeliveryVO());
	}
	
	//토스페이 먼츠 결제 승인 및 DB 저장
	@GetMapping("orders/credit") 
	public String creditTest(CreditVO creditVO, HttpSession session) throws IOException, InterruptedException {
		
		
		
		//주문, 주문결제 내역 페이지 데이터 저장
		List<OrdersVO> orderList = (List<OrdersVO>) session.getAttribute("orderGet");
		List<OrderPayVO> orderPayVO = (List<OrderPayVO>) session.getAttribute("orderPayVO");
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		List<OrdersVO> couponList = (List<OrdersVO>) session.getAttribute("couponList");
		DeliveryVO deliveryVO = (DeliveryVO) session.getAttribute("deliveryVO");
		
		
		//orderPayVO 쿠폰 사용 시 N으로 변경
		String memberId = memberVO.getMemberId();
		
		int mycouponCode = 0;
		StringBuffer str = new StringBuffer();
		for(OrderPayVO couponNum : orderPayVO) {		
			mycouponCode = couponNum.getMycouponCode();
			str.append(" , " + couponNum.getProductCode());
			int couponUse = ordersService.editNotCoupon(memberId, mycouponCode);
		}
		

		//주문 데이터 저장 method (배송 등) 총 주문 결제 정보
		ordersService.addOrderPay(memberId, deliveryVO);
		
		
		
		//주문 코드 파싱
		int orderCode =	ordersService.getOrderCode(memberId);
		int orderSuccess = 0;
		
		//토스결제
		tossPayment(creditVO, orderCode);
		
		//쿠폰 할인 상품
		 for (OrderPayVO pay : orderPayVO) {
			 for(OrdersVO order : orderList) {
				 if(pay.getProductCode().equals(order.getProductCode())) {
					 	order.setOrderCode(orderCode);
						order.setOptionCouponCheck("Y");
						order.setMycouponCode(mycouponCode);
						order.setOrderPrice(pay.getProductSalePrice());
						order.setDiscountPrice(pay.getCouponPrice());
						order.setPaymentPrice(pay.getProductSalePrice() - pay.getCouponPrice() + order.getDeliveryCost());
						orderSuccess  = ordersService.addOrders(order);
						if(orderSuccess > 0) {
							ordersService.removeCart(memberId, order.getOptionCode());
						}
					 } else {
						 continue;
					 }
			 }
		 }
		 
		// 할인 받지 않은 상품
			for(OrdersVO order : orderList) {
				if(str.toString().contains(order.getProductCode())) { //쿠폰 할인 받은 상품
					continue;
				}else {
					System.out.println(order); // 쿠폰 할인 받지 않은 상품
					order.setOrderCode(orderCode);
					order.setOptionCouponCheck("N");
					order.setOrderPrice(order.getCartCount() *(order.getProductCost() + order.getOptionPrice() - order.getSalePrice()));
					order.setPaymentPrice(order.getCartCount() * (order.getProductCost() + order.getOptionPrice() - order.getSalePrice()) + order.getDeliveryCost());
					orderSuccess = ordersService.addOrders(order);	
				}
				if(orderSuccess > 0) {
					ordersService.removeCart(memberId, order.getOptionCode());
				}
			}	
			return "redirect:/page/buyer/orderList";
		
	}
	
	
	
	
	//토스 결제 method
		public CreditVO tossPayment(CreditVO creditVO, int orderCode) throws IOException, InterruptedException {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Long amount = creditVO.getAmount();
			String paymentKey = creditVO.getPaymentKey();
			String orderId = creditVO.getOrderId();
			
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
					.header("Authorization", "Basic dGVzdF9za19RYmdNR1p6b3J6ZTVXZzJwQWVsVmw1RTFlbTRkOg==")
					.header("Content-Type", "application/json")
					.method("POST", HttpRequest.BodyPublishers.ofString("{\"amount\":\""+amount+"\",\"orderId\":\""+orderId+"\",\"paymentKey\":\""+paymentKey+"\"}"))
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			String creditRs = response.body();
			JSONParser jsonParser = new JSONParser();
			Object obj;
			JSONObject jsonObject = null;

			try {
				obj = jsonParser.parse(creditRs);
				jsonObject = (JSONObject) obj;
			} catch (ParseException e) {
				e.printStackTrace();
			}
	     
	     
			Date creDate = null;
			try {
				creDate = simpleDateFormat.parse((String)jsonObject.get("approvedAt"));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
	     	creditVO.setPaymentKey(paymentKey);
	     	creditVO.setType((String)jsonObject.get("type"));
	     	creditVO.setOrderId(orderId);
	     	creditVO.setOrderName((String)jsonObject.get("orderName"));
	     	creditVO.setMethod((String)jsonObject.get("method"));
	     	creditVO.setCurrency((String)jsonObject.get("currency"));
	     	creditVO.setMId((String)jsonObject.get("mId"));
			creditVO.setBalanceAmount((Long)jsonObject.get("balanceAmount"));
			creditVO.setTotalAmount((Long)jsonObject.get("totalAmount"));
			creditVO.setApprovedAt(creDate);
			creditVO.setOrderCode(orderCode);
			
			ordersService.addCredit(creditVO);
			return creditVO;
		}
		
		@GetMapping("orders/cancel")
		public String orderCancel(RefundVO refundVO) throws IOException, InterruptedException {
		String paymentKey =refundVO.getPaymentKey();
		int orderCode = 0;
		int orderDetailCode = 0;
		String cancelReason = "단순변심";
		int cancelAmount = 0; //부분 취소할 금액. select 해서 삼풍에서 가져오기.
		String refundType = ""; // 취소, 반품 구분 C , R
		String refundTypecode =""; // 취소, 반품 구분 C , R
		//주문 상품 delete 삭제 환불 승인될 경우.
		// orders balandeAmount 값 빼서 총값은 그대로 두기. 업데이트 하기
		// 상품 지우기 전 쿠폰 Y로 바꾸기.
		// 환불테이블에 업데이트 하기.
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create("https://api.tosspayments.com/v1/payments/GvaE2lKMZ7DLJOpm5Qrl7Zzo5X62jNVPNdxbWnYzqR4gA6Xy/cancel")) //   payments/뒤에 넣기 paymentKey
				    .header("Authorization", "Basic dGVzdF9za19RYmdNR1p6b3J6ZTVXZzJwQWVsVmw1RTFlbTRkOg==")
				    .header("Content-Type", "application/json")
				    .method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\""+ cancelReason +"\"}")) // cancelReason 값 받아와야함.  // cancelAmount":"5000 부분 취소할 경우 추가
				    .build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println(response.body());
			return "/page/seller/cancelProduct";
		}
}
