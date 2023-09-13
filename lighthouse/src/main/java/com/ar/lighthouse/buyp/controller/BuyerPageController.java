package com.ar.lighthouse.buyp.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.TradeVO;

@Controller
public class BuyerPageController {
	
	@Autowired
	BuyerPageService buyerPageService;
	
//	@GetMapping("page/buyer/orderList")
//	public void Body() {
//		
//	}
	
	//주문목록	
	@GetMapping("page/buyer/orderList/{memberId}")
	public String orderList(@PathVariable String memberId, Model model) {
		List<DetailVO> orderlist = buyerPageService.getDetailList(memberId);
		model.addAttribute("orderList", orderlist);
		
		return "/page/buyer/orderList";
	}
	
	//구매자 개인정보
	@GetMapping("page/buyer/personalInfo/{memberId}")
	public String personalInfo(@PathVariable String memberId, Model model) {
		BuyInfoVO personalInfo = buyerPageService.getInfo(memberId);
		model.addAttribute("personalInfo", personalInfo);
		
		return "/page/buyer/personalInfo";
	}
	
	//취소/반품/교환
	@GetMapping("page/buyer/tradeList/{memberId}")
	public String TradeList(@PathVariable String memberId ,Model model) {
		List<TradeVO> tradeList = buyerPageService.getTradeList(memberId);

		model.addAttribute("tradeList", tradeList);
		return "/page/buyer/tradeList";
	}
	
	
}
