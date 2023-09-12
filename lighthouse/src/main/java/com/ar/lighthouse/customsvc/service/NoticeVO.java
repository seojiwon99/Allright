package com.ar.lighthouse.customsvc.service;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {

	private int noticeCode;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date up_enddate;
	private String noticeType;
	private Date noticeRegdate;
	private Date noticeUpdatedate;
	
}
