package com.ar.lighthouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("test")
	public String Test() {
		return "page/test";
	}
	
	@GetMapping("page/body")
	public void Body() {
		
	}
}
