package com.ar.lighthouse.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadFileChkMapper {
	
	// 이미지 확인
	public List<ImgsVO>getImgsFiles();
}
