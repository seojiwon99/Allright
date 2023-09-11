package com.ar.lighthouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("test")
	public void Test() {
		
	}
	
	@GetMapping("page/body")
	public void Body() {
		
	}
}
