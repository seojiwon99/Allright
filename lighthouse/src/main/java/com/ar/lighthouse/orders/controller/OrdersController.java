package com.ar.lighthouse.orders.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class OrdersController {

	@GetMapping("orders/pay")
	public String orderPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		
		return "/page/orders/ordersPay";
	}
	
}
