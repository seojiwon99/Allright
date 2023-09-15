package com.ar.lighthouse.buyp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ImgVO {

	private int imgCode;
	private String parentCode;
	private int imgOrder;
	private String imgName;
	private String uploadName;
	private String imgContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date imgRegdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date imgFromtable;
}
