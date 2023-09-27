package com.ar.lighthouse.main.service;

import java.util.Date;

import lombok.Data;

@Data
public class EventImgVO {

	int eventImgCode;
	int imgOrder;
	String imgName;
	String uploadName;
	String imgContent;
	Date imgRegdate;
	int eventCode;
	int bannerOrder;
	String bannerType;
	String bannerDelete;
	String uploadPath;
}
