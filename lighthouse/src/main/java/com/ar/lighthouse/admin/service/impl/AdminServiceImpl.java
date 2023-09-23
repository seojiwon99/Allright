package com.ar.lighthouse.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.admin.mapper.AdminMapper;
import com.ar.lighthouse.admin.service.AdminService;
import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.admin.service.ProductDetailVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.mapper.CustomMapper;
import com.ar.lighthouse.customsvc.service.InquiryVO;

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

	@Override
	public List<DeclareVO> getDeclareList(int amount, int pageNum, String declareContent, String declareReason) {
		return adminMapper.selectDeclareList(amount, pageNum, declareContent, declareReason);
	}

	@Override
	public int getTotalDeclareCount(DeclareVO declareVO) {
		return adminMapper.selectTotalDeclareCount(declareVO);
	}

	@Override
	public List<DeclareVO> getClearDeclareList(int amount, int pageNum, String declareContent, String declareReason) {
		return adminMapper.selectClearDeclareList(amount, pageNum, declareContent, declareReason);
	}

	@Override
	public int getTotalClearDeclareCount(DeclareVO declareVO) {
		return adminMapper.selectTotalClearDeclareCount(declareVO);
	}

	@Override
	public List<InquiryVO> getInquiryList(int amount, int pageNum, String customInquiryTitle) {
		return adminMapper.selectInquiryList(amount, pageNum, customInquiryTitle);
	}

	@Override
	public int getTotalInquiryCount(InquiryVO inquiryVO) {
		return adminMapper.selectTotalClearInquiryCount(inquiryVO);
	}

	@Override
	public List<InquiryVO> getClearInquiryList(int amount, int pageNum, String customInquiryTitle) {
		return adminMapper.selectClearInquiryList(amount, pageNum, customInquiryTitle);
	}

	@Override
	public List<MemberDetailVO> getBuyerList(int amount, int pageNum, String memberId, String memberName,
			String memberTel, String businessNumber, int memberAuthor) {
		return adminMapper.selectMemberList(amount, pageNum, memberId, memberName, memberTel, businessNumber, memberAuthor);
	}

	@Override
	public List<MemberDetailVO> getSellerList(int amount, int pageNum, String memberId, String memberName,
			String memberTel, String businessNumber, int memberAuthor) {
		return null;
	}

	@Override
	public int getTotalUserCount(MemberDetailVO memberDetailVO) {
		return adminMapper.selectTotalUserCount(memberDetailVO);
	}

	@Override
	public List<ProductDetailVO> getProductList(int amount, int pageNum, String memberId, String memberTel, String businessNumber, String productCode) {
		return adminMapper.selectProductList(amount, pageNum, memberId, memberTel, businessNumber, productCode);
	}

	@Override
	public int getTotalProductCount(ProductDetailVO productDetailVO) {
		return adminMapper.selectTotalProductCount(productDetailVO);
	}

	@Override
	public List<NoticeAdminVO> getAdminNoticeList(Criteria cri, NoticeAdminVO noticeAdminVO) {
		return adminMapper.selectAdminNoticeList(cri, noticeAdminVO);
	}

	@Override
	public int getTotalNoticeCount(NoticeAdminVO noticeAdminVO) {
		return adminMapper.selectTotalNoticeCount(noticeAdminVO);
	}

}
