package com.ar.lighthouse.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.service.InquiryVO;


@Mapper
public interface AdminMapper {
	
	//공지 등록
	public int insertNotice(NoticeAdminVO noticeAdminVO);
	
					/*Declare*/	
	//신고 목록
	public List<DeclareVO> selectDeclareList(@Param("amount") int amount, @Param("pageNum") int pageNum
			, @Param("declareContent") String declareContent, @Param("declareReason") String declareReason);
	//신고 개수
	public int selectTotalDeclareCount(DeclareVO declareVO);
	//처리된 신고 목록
	public List<DeclareVO> selectClearDeclareList(@Param("amount") int amount, @Param("pageNum") int pageNum
			, @Param("declareContent") String declareContent, @Param("declareReason") String declareReason);
	//처리된 신고 개수
	public int selectTotalClearDeclareCount(DeclareVO declareVO);
	
					/*Inquiry*/
	//문의 개수
	public List<InquiryVO> selectInquiryList(@Param("amount") int amount, @Param("pageNum") int pageNum, @Param("customInquiryTitle") String customInquiryTitle);
	//문의 목록
	public int selectTotalClearInquiryCount(InquiryVO inquiryVO);
	//처리된 문의 개수
	public List<InquiryVO> selectClearInquiryList(@Param("amount") int amount,  @Param("pageNum") int pageNum,@Param("customInquiryTitle")  String customInquiryTitle);
	
					/*User*/
	//구매자 
	public List<MemberDetailVO> selectMemberList(@Param("amount") int amount, @Param("pageNum") int pageNum
			, @Param("memberId") String memberId, @Param("memberName") String memberName,
			@Param("memberTel") String memberTel, @Param("businessNumber") String businessNumber, @Param("memberAuthor") int memberAuthor); 
	
	//유저 수
	public int selectTotalUserCount(MemberDetailVO memberDetailVO);
}
