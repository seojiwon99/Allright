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

import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	//coupon 관련 정보 가져옴
	@GetMapping("/page/orders/ordersPay")
	public String orderPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		List<OrdersVO> couponList = ordersService.getCoupon(memberId);
		session.setAttribute("couponList", couponList);
		model.addAttribute("couponList",couponList);
		
		return "/page/orders/ordersPay :: #couponPage";
	}
	
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
		
		// 배송지 마스터 코드 전송
		model.addAttribute("codeList",codeList);
		 
		return "/page/orders/ordersPay";
	}
	
	//배송지, 배송 주문 테이블 Session 저장
	@PostMapping("orders/save")
	@ResponseBody
	public void ordersSave(HttpServletRequest req, @RequestBody OrderPayVO ordersPay) {
		HttpSession session = req.getSession();
		session.setAttribute("ordersPay", ordersPay);
	}
	
	//토스페이 먼츠 결제 승인 및 DB 저장 후 주문 내역으로 리다이렉트
	@GetMapping("orders/credit") //파라미터 이름 VO 값을 받고 /
	public String creditTest(CreditVO creditVO, HttpSession session) throws IOException, InterruptedException {
		
		//토스결제
		tossPayment(creditVO);
		
		//주문, 주문결제 내역 페이지 데이터 저장
		List<OrdersVO> orderList = (List<OrdersVO>) session.getAttribute("orderGet");
		OrderPayVO orderPayVO = (OrderPayVO) session.getAttribute("ordersPay");
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		List<OrdersVO> couponList = (List<OrdersVO>) session.getAttribute("couponList");
		
		//orderPayVO 쿠폰 사용 시 N으로 변경
		String memberId = memberVO.getMemberId();
		int mycouponCode = orderPayVO.getMycouponCode();
		int couponUse = ordersService.editNotCoupon(memberId, mycouponCode);		
		String optionCouponCheck = "";
		if(couponUse > 0 ) {
			optionCouponCheck = "Y";
		}else {
			optionCouponCheck = "N";
		}
		
		//주문 데이터 저장 method (배송 등) 총 주문 결제 정보
		ordersService.addOrderPay(memberId, orderPayVO);
		
		//주문 코드 파싱
		int orderCode =	ordersService.getOrderCode(memberId);
		int orderSuccess = 0;
		// 각 주문 내역에 대한 정보 저장
		for(OrdersVO order : orderList) {
			if(order.getProductCode().equals(orderPayVO.getProductCode()) ) {
				order.setOrderCode(orderCode);
				order.setOptionCouponCheck(optionCouponCheck);
				order.setMycouponCode(mycouponCode);
				order.setOrderPrice(orderPayVO.getProductSalePrice());
				order.setDiscountPrice(orderPayVO.getCouponPrice());
				order.setPaymentPrice(orderPayVO.getProductSalePrice() - orderPayVO.getCouponPrice());
				System.out.println("if= " + order);
				orderSuccess  = ordersService.addOrders(order);
			}else {
				System.out.println(order);
				order.setOrderCode(orderCode);
				order.setOptionCouponCheck("N");
				order.setOrderPrice(order.getCartCount() *(order.getProductCost() - order.getSalePrice()));
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
		public CreditVO tossPayment(CreditVO creditVO) throws IOException, InterruptedException {
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
			
			ordersService.addCredit(creditVO);
			return creditVO;
		}
}
