package com.ar.lighthouse.buyp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ar.lighthouse.buyp.service.BuyerPageService;

@Controller
public class BuyerPageController {
	
	@Autowired
	BuyerPageService buyerPageService;
	
//	@GetMapping("page/buyer/orderList")
//	public void Body() {
//		
//	}
	

	@GetMapping("page/buyer/orderList/{memberId}")
	public String orderList(@PathVariable String memberId ,Model model) {
		model.addAttribute("orderList", buyerPageService.getDetailList(memberId));
		return "page/buyer/orderList";
	}
}
