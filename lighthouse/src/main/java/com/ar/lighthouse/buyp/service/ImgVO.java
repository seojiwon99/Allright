package com.ar.lighthouse.buyp.service;

import java.util.Date;

import lombok.Data;

@Data
public class ImgVO {

	private int imgCode;
	private String parentCode;
	private int imgOrder;
	private String imgName;
	private String uploadName;
	private String imgContent;
	private Date imgRegdate;
	private Date imgFromtable;
}
