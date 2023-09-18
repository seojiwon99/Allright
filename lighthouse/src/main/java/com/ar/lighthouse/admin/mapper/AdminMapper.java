package com.ar.lighthouse.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.admin.service.NoticeAdminVO;


@Mapper
public interface AdminMapper {
	public int insertNotice(NoticeAdminVO noticeAdminVO);
}
