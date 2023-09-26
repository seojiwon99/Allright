package com.ar.lighthouse.product.service;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.common.ImgsVO;

import lombok.Data;

@Data
public class ProductVO {
	 	private String productCode;
	 	private String memberId;
	 	private String categoryCode;
	 	private String productName;
	 	private String productContent;
	 	private String productLocation;
	 	private String productOrigin;
	 	private String productMakeorigin;
	 	private int deliveryCost;
	 	private int returnCost;
	 	private int exchangeCost;
	 	private String deliveryService;
	 	private String productBrand;
	 	private String productStatus;
	 	private String productExStatus;
	 	
	 	private List<ImgsVO> productImg;

	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	private Date productRegdate;
	 	
	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	private Date productUpdatedate;
	 	
	 	private int productCost;
	 	private int salePrice;
	 	String optionVal;
//	 	통계에 필요한
	 	private int total_cnt;
	 	List<OptionVO> option;

	 	List<DetailVO> detail;


	 	List<OptionDetailVO> optionDetail;

	 	
	 	
}
