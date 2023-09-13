package com.ar.lighthouse.customsvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.service.FaqVO;
import com.ar.lighthouse.customsvc.service.InquiryVO;
import com.ar.lighthouse.customsvc.service.NoticeVO;

@Mapper
public interface CustomMapper {
	
	// faq 목록 가져오기
	public List<FaqVO> selectAllFaq(@Param("faqType") String faqType);
	
	// 공지사항 목록 가져오기
	public List<NoticeVO> selectAllNotice(Criteria cri);
	
	// 공지 사항 전체 갯수
	public int getTotalCount(Criteria cri);
	
	// 1:1문의 등록
	public int insertInq(InquiryVO inqVO);
	
}
