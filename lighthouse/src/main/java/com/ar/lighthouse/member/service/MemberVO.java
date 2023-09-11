package com.ar.lighthouse.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class MemberVO {
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
}
