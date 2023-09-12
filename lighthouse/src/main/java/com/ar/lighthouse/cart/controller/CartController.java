package com.ar.lighthouse.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.cart.service.CartVO;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("cart/cartView/{memberId}")
	public String cartView(@PathVariable String memberId, Model model) {
		List<CartVO> list = cartService.cartGetList(memberId);
		model.addAttribute("list", list);
		
		return "/page/cart/cartView";
	}
	@GetMapping("cart/delete/")
	public String cartDelete(String memberId, int cartCode, HttpServletRequest request) {
		memberId = "user123456";
		cartService.removeCart(memberId, cartCode);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
