package com.ar.lighthouse.orders.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@GetMapping("/page/orders/ordersPay")
	public String orderPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		List<OrdersVO> couponList = ordersService.getCoupon(memberId);
		
		model.addAttribute("couponList",couponList);
		
		return "/page/orders/ordersPay :: #couponPage";
	}
	
	
	@PostMapping("orders/pay")
	public String orderList(HttpServletRequest request, Model model, @RequestParam (name = "cartCode") int[] cartCode) {
		
		System.out.println(cartCode);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		
		List<CodeVO> codeList = ordersService.getCode();
		
		int[] lists = cartCode;
		System.out.println(cartCode);
		List<OrdersVO> get = new ArrayList<OrdersVO>();
		for(int cartNum : lists)  {
			System.out.println(cartNum);
			get.add((ordersService.getOrders(memberId, cartNum)));  
		}
		int productNum = 0;
		String productName = "";
		if(get.size() != 1) {
		productNum = get.size()-1;
			productName = get.get(0).getProductName()  + " 외 " + productNum + "건";
		} else {
			productName = get.get(0).getProductName();	
		}
		model.addAttribute("productName",productName);
		model.addAttribute("get", get);
		model.addAttribute("codeList",codeList);
		 
		return "/page/orders/ordersPay";
	}
	
	@GetMapping("orders/credit") //파라미터 이름 VO 값을 받고 /
	public String creditTest(CreditVO creditVO) throws IOException, InterruptedException {
//		int amount = creditVO.getAmount();
//		String paymentKey = creditVO.getPaymentKey();
//		String orderId = creditVO.getOrderId();
//		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
//				.header("Authorization", "Basic dGVzdF9za19RYmdNR1p6b3J6ZTVXZzJwQWVsVmw1RTFlbTRkOg==")
//				.header("Content-Type", "application/json")
//				.method("POST", HttpRequest.BodyPublishers.ofString("{\"amount\":amount,\"orderId\":\"orderId\",\"paymentKey\":\"paymentKey\"}"))
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
//		System.out.println(creditVO);
		
		return "/page/buyer/orderList";
	}
	
}
