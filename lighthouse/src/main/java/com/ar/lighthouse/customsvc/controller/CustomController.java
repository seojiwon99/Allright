package com.ar.lighthouse.customsvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.customsvc.service.CustomService;

@Controller
public class CustomController {
	
	@Autowired
	CustomService customService;
	
	@GetMapping("custom/faqList")
	public String faqList(Model model) {
		model.addAttribute("faqList", customService.getFaqList());
		return "page/custom/faqList";
	}
	
}
