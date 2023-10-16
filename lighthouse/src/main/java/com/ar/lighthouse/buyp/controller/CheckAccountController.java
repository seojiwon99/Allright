package com.ar.lighthouse.buyp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit2.http.GET;

@Controller
public class CheckAccountController {
	
	@Autowired
	CheckAccountService checkAccountService;
	
	
	
	@PostMapping("/checkAccount")
	@ResponseBody
	public Map<Object, Object> CheckAccount(@RequestParam("bank_code") String bank_code, @RequestParam("bank_num") String bank_num) {
		HashMap<Object, Object> map = new HashMap<>();
		map = checkAccountService.getAccessToken1(bank_code, bank_num);
	
		String bankHolderInfo = (String) map.get("bankHolderInfo");
		
		//객체에 error키에 따른 밸류 저장
		Object errorObj = map.get("error");
		if (errorObj instanceof String) {
		   //String이라면
			String errorStr = (String) errorObj;
		    int error1 = Integer.parseInt(errorStr);
		    map.put("errormsg", String.valueOf(error1));
		} 

		if(bankHolderInfo !=null){
			map.put("bankHolderInfo", bankHolderInfo);
		}else {
			//map.put("없는 정보입니다.", bankHolderInfo);
			//System.out.println("없다");
			//bankHolderInfo="없는 정보입니다.";
			map.put("bankHolderInfo", bankHolderInfo);
			
		}
		
			
		//map.put("bankHolderInfo", bankHolderInfo);
		System.out.println("예금주"+bankHolderInfo);
		
		return map;
	}
	
	@GetMapping("/bankList")
	@ResponseBody
	public List<JSONObject> bankList(){
		System.out.println("asdasda");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		List<JSONObject> list = checkAccountService.bankList();
		return list;
	}

}
