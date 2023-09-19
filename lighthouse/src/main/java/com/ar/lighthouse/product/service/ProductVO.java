package com.ar.lighthouse.product.service;


import java.util.Date;
import java.util.List;
import com.ar.lighthouse.buyp.service.DetailVO;

import org.springframework.format.annotation.DateTimeFormat;


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
	 	
	 	
	 	List<OptionVO> option;

	 	
	 	
}
