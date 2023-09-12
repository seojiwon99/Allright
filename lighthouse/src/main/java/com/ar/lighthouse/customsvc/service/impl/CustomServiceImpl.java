package com.ar.lighthouse.customsvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.customsvc.mapper.CustomMapper;
import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.FaqVO;

@Service
public class CustomServiceImpl implements CustomService{

	@Autowired
	CustomMapper customMapper;
	
	@Override
	public List<FaqVO> getFaqList() {
		return customMapper.selectAllFaq();
	}

}
