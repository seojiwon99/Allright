package com.ar.lighthouse.admin.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DeclareVO {
	int declareCode;
	int reviewCode;
	String productCode;
	String memberId;
	String declareReason;
	String declareContent;
	String declareMember;
	String declareStatus;
 	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date declareRegdate;
 	String suspendStatus;
 	
 	//디테일에 쓰임.
 	int declareCount;
 	String reviewContent;
 	int memberAuthor; // 피신고자 권한.
 	
	
}
