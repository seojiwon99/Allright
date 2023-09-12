package com.ar.lighthouse.customsvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.customsvc.service.FaqVO;

@Mapper
public interface CustomMapper {
	
	// faq 목록 가져오기
	public List<FaqVO> selectAllFaq();
	
}
