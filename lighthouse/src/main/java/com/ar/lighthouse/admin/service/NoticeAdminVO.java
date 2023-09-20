package com.ar.lighthouse.admin.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeAdminVO {

	private int noticeCode;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date upEnddate;
	private String noticeType;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date noticeRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date noticeUpdatedate;
	
}
