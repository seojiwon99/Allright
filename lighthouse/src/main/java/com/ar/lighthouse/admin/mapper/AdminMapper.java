package com.ar.lighthouse.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.admin.service.DeclareVO;
import com.ar.lighthouse.admin.service.MemberDetailVO;
import com.ar.lighthouse.admin.service.NoticeAdminVO;
import com.ar.lighthouse.admin.service.ProductDetailVO;
import com.ar.lighthouse.admin.service.SuspendReasonVO;
import com.ar.lighthouse.admin.service.SuspendVO;
import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.service.FaqVO;
import com.ar.lighthouse.customsvc.service.InquiryVO;


@Mapper
public interface AdminMapper {
	
	//공지 등록
	public int insertNotice(NoticeAdminVO noticeAdminVO);
	//faq 등록
	public int insertFaq(FaqVO faqVO);
	//공지 상세
	public NoticeAdminVO selectNoticeDetail(NoticeAdminVO noticeAdminVO);
	//FAQ 상세
	public FaqVO selectFaqDetail(FaqVO faqVO);
	//공지 수정
	public int updateNotice(NoticeAdminVO noticeAdminVO);
	//FAQ 수정
	public int updateFaq(FaqVO faqVO);
	
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
	//신고 상세보기
	public DeclareVO selectDeclareDetail(DeclareVO declareVO);
	//신고 처리, 정지하기
	public int updateSuspendUser(String suspStatus);
	//정지 사유 목록
	public List<SuspendReasonVO> selectSuspReason(); //보류
	//정지 등록
	public int insertSuspend(SuspendVO suspendVO);
	//정지 후 신고 완료 처리
	public int updateDeclareStatus(SuspendVO suspendVO);

	
	
					/*Inquiry*/
	//문의 개수
	public List<InquiryVO> selectInquiryList(@Param("amount") int amount, @Param("pageNum") int pageNum, @Param("customInquiryTitle") String customInquiryTitle);
	//문의 목록
	public int selectTotalClearInquiryCount(InquiryVO inquiryVO);
	//처리된 문의 개수
	public List<InquiryVO> selectClearInquiryList(@Param("amount") int amount,  @Param("pageNum") int pageNum,@Param("customInquiryTitle")  String customInquiryTitle);
	//문의 상세보기
	public InquiryVO selectInquiryDetail(InquiryVO inquiryVO);
	//문의 등록
	public int updateCustomInquiry(InquiryVO inquiryVO);
	
					/*User*/
	//구매자 
	public List<MemberDetailVO> selectMemberList(@Param("amount") int amount, @Param("pageNum") int pageNum
			, @Param("memberId") String memberId, @Param("memberName") String memberName,
			@Param("memberTel") String memberTel, @Param("businessNumber") String businessNumber, @Param("memberAuthor") int memberAuthor); 
	
	//유저 수
	public int selectTotalUserCount(MemberDetailVO memberDetailVO);
	
	//유저 페이지에서 정지
	public int insertSuspendByAdmin(SuspendVO suspendVO);
	//정지 유저 해제
	public int updateSuspendStatus(String memberId);
	
	
					/*Product*/
	//상픔 목록
	public List<ProductDetailVO> selectProductList(@Param("amount") int amount,  @Param("pageNum") int pageNum
			, @Param("memberId") String memberId, @Param("memberTel") String memberTel
			, @Param("businessNumber") String businessNumber, @Param("productCode") String productCode);
	//상품 수
	public int selectTotalProductCount(ProductDetailVO productDetailVO);
	
	//공지 목록
	public List<NoticeAdminVO>selectAdminNoticeList(@Param("cri") Criteria cri, @Param("noticeAdminVO") NoticeAdminVO noticeAdminVO);
	//공지 수
	public int selectTotalNoticeCount(NoticeAdminVO noticeAdminVO);
}
