package com.ar.lighthouse.customsvc.service;

import java.util.List;

import com.ar.lighthouse.common.Criteria;

public interface CustomService {
	
	// Faq 목록 가져오기
	public List<FaqVO> getFaqList(String faqType);
	
	// 공지사항 목록 가져오기
	public List<NoticeVO> getNoticeList(Criteria cri);
	
	// 공지사항 정보
	public NoticeVO getNotice(NoticeVO noticeVO);
	
	// 전체 갯수 가져오기
	public int getTotalCount(Criteria cri);
	
	// 1:1문의 등록
	public int addInquiry(InquiryVO inqVO);
	
}
