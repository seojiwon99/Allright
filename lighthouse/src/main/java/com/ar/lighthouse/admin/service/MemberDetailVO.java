package com.ar.lighthouse.admin.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberDetailVO {
	private int declareCount;
	private String suspStatus;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberBirth;
	private String memberAddr;
	private String memberDetailsAddr;
	private String memberEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memberRegdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memberEnddate;
	private String businessNumber;
	private int memberAuthor;
	private String memberTel;
	
	//user 상세보기
	private Date suspRegdate;
	private Date suspEnddate;
	private String suspReason;
	private int suspCount;
	
	
}
