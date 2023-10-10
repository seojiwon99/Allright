package com.ar.lighthouse.buyp.service;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BuyInfoVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String memberBirth;
	private String memberAddr;
	private String memberDetailsAddr;
	private String memberEmail;
	private String memberTel;
}
