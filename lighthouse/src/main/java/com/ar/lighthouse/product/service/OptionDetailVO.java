package com.ar.lighthouse.product.service;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OptionDetailVO {


	private Long optionDetailCode;
	private String productCode;
	private Integer optionOrder;
	private String optionLast;
	private Integer optionPrice;
	private String optionSellStatus;
	private String optionExStatus;
	private Integer optionCount;
	private Integer minOrder;
	private Integer maxOrder;

	   private String optionName;
	   private String optionValue;
	   private int salePrice;


	public OptionDetailVO(String optionName) {
		super();
		this.optionName = optionName;
	}
	   
	  

}
