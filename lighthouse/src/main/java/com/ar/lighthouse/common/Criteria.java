package com.ar.lighthouse.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Criteria {
	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 Date fromDate;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 Date toDate;
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
	
}
