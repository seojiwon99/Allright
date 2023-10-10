package com.ar.lighthouse.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.member.service.MemberVO;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("cart/cartView")
	public String cartView(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		 MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		 String memberId = memberVO.getMemberId();
		List<CartVO> list = cartService.cartGetList(memberId);
		
		model.addAttribute("list", list);
		
		return "/page/cart/cartView";
	}
	@GetMapping("cart/delete")
	public String cartDelete(int cartCode, HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		 MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		 String memberId = memberVO.getMemberId();
		cartService.removeCart(memberId, cartCode);
		String referer = request.getHeader("Referer");
		
		List<CartVO> list = cartService.cartGetList(memberId);
		model.addAttribute("list", list);
		return "/page/cart/cartView :: #test";
	}
	
	@PostMapping("cart/delete")
	public String cartSelectDel(@RequestBody List<CartVO> cartList, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		 MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		 String memberId = memberVO.getMemberId();
		List<CartVO> lists = cartList;
		
		for(CartVO cartVO : lists) {
			cartService.removeCart(memberId, cartVO.getCartCode());
		}

		List<CartVO> list = cartService.cartGetList(memberId);
		model.addAttribute("list",list);
		
		return "/page/cart/cartView :: #test";
		
	}
}
