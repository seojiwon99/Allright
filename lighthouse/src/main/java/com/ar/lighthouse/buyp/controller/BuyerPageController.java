/* 
 개발자 : 강민호
 개발일자 : 2023/09/14
 		구매자 마이페이지
 */
package com.ar.lighthouse.buyp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.TradeVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BuyerPageController {
	
	@Autowired
	BuyerPageService buyerPageService;
	
//	@GetMapping("page/buyer/mypage")
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
	
	//개인정보수정
	@PostMapping("page/buyer/personalInfo/{memberId}")
	public ResponseEntity<String> editInfo(BuyInfoVO buyInfoVO, String memberId){
		memberId = "user123456";
		int updateInfo = buyerPageService.editInfo(buyInfoVO, memberId);
		return updateInfo == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
