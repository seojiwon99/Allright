package com.ar.lighthouse.orders.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	
	@GetMapping("orders/pay")
	public String orderList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		List<OrdersVO> get =ordersService.getOrders(memberId);
		//List<CartVO> lists = cartCode;
//		List<OrdersVO> get = new ArrayList<OrdersVO>();
//		for(CartVO cartVO : lists)  {
//			get.add((ordersService.getOrders(memberId, cartVO.getCartCode())));  
//			
//		}
		
		model.addAttribute("get", get);
		return "/page/orders/ordersPay";
	}
	
}
