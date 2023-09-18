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
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@GetMapping("/page/orders/ordersPay")
	public Model orderPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		List<OrdersVO> couponList = ordersService.getCoupon(memberId);
		model.addAttribute("couponList",couponList);
		
		return model.addAttribute("couponList",couponList);
	}
	
	
	@PostMapping("orders/pay")
	public String orderList(HttpServletRequest request, Model model, @RequestParam (name = "cartCode") int[] cartCode) {
		
		System.out.println(cartCode);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		//List<OrdersVO> get =ordersService.getOrders(memberId);
		int[] lists = cartCode;
		System.out.println(cartCode);
		List<OrdersVO> get = new ArrayList<OrdersVO>();
		for(int cartNum : lists)  {
			System.out.println(cartNum);
			get.add((ordersService.getOrders(memberId, cartNum)));  
		}
		model.addAttribute("get", get);
		
		return "/page/orders/ordersPay";
	}
	
}
