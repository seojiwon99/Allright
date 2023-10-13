package com.ar.lighthouse.cart.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.cart.service.CartService;
import com.ar.lighthouse.cart.service.CartUpdateVO;
import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.product.service.OptionDetailVO;
import com.fasterxml.jackson.annotation.JsonFormat;

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

		return "page/cart/cartView";
	}

	@GetMapping("cart/delete")
	public String cartDelete(int cartCode, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		cartService.removeCart(memberId, cartCode);
		String referer = request.getHeader("Referer");

		List<CartVO> list = cartService.cartGetList(memberId);
		model.addAttribute("list", list);
		return "page/cart/cartView :: #test";
	}

	@PostMapping("cart/delete")
	public String cartSelectDel(@RequestBody List<CartVO> cartList, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
		List<CartVO> lists = cartList;

		for (CartVO cartVO : lists) {
			cartService.removeCart(memberId, cartVO.getCartCode());
		}

		List<CartVO> list = cartService.cartGetList(memberId);
		model.addAttribute("list", list);

		return "page/cart/cartView :: #test";

	}

	@PostMapping("cart/addCart")
	@ResponseBody
	public List<Long> addCart(CartVO cartVO, @RequestBody List<OptionDetailVO> optionDetailVO, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();
	
		List<OptionDetailVO> optionList = new ArrayList<OptionDetailVO>();
		for (OptionDetailVO odv : optionDetailVO) {
			OptionDetailVO vo = cartService.getOptionCode(odv);
			vo.setOptionOrder(odv.getOptionCount());
			optionList.add(vo);
		}
		List<Long> lastODC = new ArrayList<Long>();
		for (OptionDetailVO otc : optionList) {
			CartVO cart = new CartVO();
			cart.setCartCount(otc.getOptionOrder());
			cart.setOptionDetailCode(otc.getOptionDetailCode());

			cart.setMemberId(memberId);
			if (cartService.checkCart(cart) > 0) {
				lastODC.add(cart.getOptionDetailCode());
			} else {
				cartService.addCart(cart);
			}

		}
		// cartService.addCart(cartVO);
		// lastODC <-- 받아서 값이 없다? 다 들어감. 값이 있다? optionDetailCode 가 안들어간거
		return lastODC;
	}

	@GetMapping("/cart/cartCheck")
	@ResponseBody
	public int cartCheck(CartVO vo, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		vo.setMemberId(memberId);

		return cartService.checkCart(vo);
	}
	
	@PostMapping("/cart/update")
	@ResponseBody
	public int cartCountUpdate(@RequestBody CartUpdateVO cartUpdateVO) {
		cartService.editCartCnt(cartUpdateVO);
		
		return 0;
	}

}
