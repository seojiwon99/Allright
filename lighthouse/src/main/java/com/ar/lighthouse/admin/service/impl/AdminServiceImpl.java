package com.ar.lighthouse.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.admin.mapper.AdminMapper;
import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.customsvc.mapper.CustomMapper;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	CustomMapper customMapper;
	
	@Autowired
	AdminMapper adminMapper;

	@Override
	public int addNotice(NoticeAdminVO noticeAdminVO) {
		return adminMapper.insertNotice(noticeAdminVO); 
	}

}
