package com.ar.lighthouse.orders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

	@GetMapping("orders/pay")
	public String orderPage( ) {
		
		return "/page/orders/ordersPay";
	}
	
}
