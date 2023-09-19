package com.ar.lighthouse.buyp.service;

import lombok.Data;

@Data
public class CodeVO {
	
	private String masterCode;
	private String codeId;
	private String codeName;
	
	private String exchangeCode;
	
	private int orderCode;
	private int orderDetailCode;	
	private String productName;
	private String memberId;	
}
