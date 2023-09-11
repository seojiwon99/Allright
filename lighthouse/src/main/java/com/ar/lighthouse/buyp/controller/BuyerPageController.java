package com.ar.lighthouse.buyp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyerPageController {
	@GetMapping("page/buyer/mypage")
	public void Body() {
		
	}
	

//	@GetMapping("page/buyer/personal_info")
//	public String info() {
//		
//		return "page/buyer/personal_info";
//	}
}
