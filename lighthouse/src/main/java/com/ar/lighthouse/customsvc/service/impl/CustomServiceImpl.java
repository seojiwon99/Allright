package com.ar.lighthouse.customsvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.mapper.CustomMapper;
import com.ar.lighthouse.customsvc.service.CustomService;
import com.ar.lighthouse.customsvc.service.FaqVO;
import com.ar.lighthouse.customsvc.service.InquiryVO;
import com.ar.lighthouse.customsvc.service.NoticeVO;

@Service
public class CustomServiceImpl implements CustomService{

	@Autowired
	CustomMapper customMapper;
	
	@Override
	public List<FaqVO> getFaqList(String faqType,Criteria cri) {
		return customMapper.selectFaqList(faqType,cri);
	}
	
	@Override
	public int getTotalFaqCount(String faqType) {
		return customMapper.getTotalFaqCount(faqType);
	}

	@Override
	public List<NoticeVO> getNoticeList(Criteria cri) {
		return customMapper.selectNoticeList(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return customMapper.getTotalCount(cri);
	}

	@Override
	public int addInquiry(InquiryVO inqVO) {
		return customMapper.insertInquiry(inqVO);
	}

	@Override
	public NoticeVO getNotice(NoticeVO noticeVO) {
		return customMapper.selectNotice(noticeVO);
	}

	@Override
	public List<FaqVO> getTypeList() {
		return customMapper.selectFaqTypeList();
	}

	
	

}
