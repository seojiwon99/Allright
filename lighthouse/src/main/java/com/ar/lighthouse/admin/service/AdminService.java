package com.ar.lighthouse.admin.service;

import java.util.List;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.service.InquiryVO;

public interface AdminService {
	//공지등록
	public int addNotice(NoticeAdminVO noticeAdminVO);
	//신고 목록
	public List<DeclareVO> getDeclareList(int amount, int pageNum, String declareContent, String declareReason); 
	//신고 개수
	public int getTotalDeclareCount(DeclareVO declareVO);
	//처리된신고 목록
	public List<DeclareVO> getClearDeclareList(int amount, int pageNum, String declareContent, String declareReason); 
	//처리된 신고 개수
	public int getTotalClearDeclareCount(DeclareVO declareVO);	
	
	//문의 목록
	public List<InquiryVO> getInquiryList(int amount, int pageNum, String customInquiryTitle);
	//문의 개수
	public int getTotalInquiryCount(InquiryVO inquiryVO);
	//처리된 문의 목록
	public List<InquiryVO> getClearInquiryList(int amount, int pageNum, String customInquiryTitle);
	
	//구매자 리스트
	public List<MemberDetailVO> getBuyerList(int amount, int pageNum, String memberId, String memberName, String memberTel, String businessNumber, int memberAuthor);
	//판매자 리스트
	public List<MemberDetailVO> getSellerList(int amount, int pageNum, String memberId, String memberName, String memberTel, String businessNumber, int memberAuthor);	
	//유저 수
	public int getTotalUserCount(MemberDetailVO memberDetailVO);
	
	
	
}
