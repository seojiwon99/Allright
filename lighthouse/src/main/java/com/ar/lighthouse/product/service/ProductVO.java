package com.ar.lighthouse.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.common.CodeVO;
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
	private String productExstatus;
	private String productMakeorigin;
	private Integer deliveryCost;
	private Integer returnCost;
	private Integer exchangeCost;
	private String deliveryService;
	private String productBrand;
	private String productStatus;


	private Integer productCount;
	private List<ImgsVO> productImg;
	private Integer salePrice;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productRegdate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productUpdatedate;

	private Integer productCost;
	String optionVal;
//	 	통계에 필요한
	private int total_cnt;
	List<OptionVO> option;

	List<DetailVO> detail;

	List<OptionDetailVO> optionDetail;
	
	private String businessNumber;
	//메인 썸네일 뿌릴 때 필요
	private String imgContent;
	private String uploadPath;
	private String uploadName;
	private int orderCnt;
	
	List<CategoryVO> category;
	
	List<CodeVO> code;
}
