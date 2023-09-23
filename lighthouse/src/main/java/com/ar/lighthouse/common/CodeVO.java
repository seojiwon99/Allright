package com.ar.lighthouse.common;

import lombok.Data;

@Data
public class CodeVO {
	
	private String masterCode;
	private String codeId;
	private String codeName;
	
	private int paymentPrice;
	private int orderCode;
	private int orderDetailCode;	
	private String productName;
	private int orderCnt;
	private String memberId;
	
	//교환
	private String exchangeCode;
	private String exchangeReason;
	private String exchangeStatus;
	
	//취소
	private String cancelCode;
	private String cancelReason;
	private String cancelStatus;
	
	//반품
	private String returnCode;
	private String returnReason;
	private String returnStatus;
}
