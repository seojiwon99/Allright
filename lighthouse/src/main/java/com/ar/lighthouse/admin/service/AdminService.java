package com.ar.lighthouse.admin.service;

import java.util.List;
import java.util.Map;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.customsvc.service.FaqVO;
import com.ar.lighthouse.customsvc.service.InquiryVO;
import com.ar.lighthouse.customsvc.service.NoticeVO;
import com.ar.lighthouse.main.service.EventImgVO;

public interface AdminService {
	
	public List<NoticeAdminVO> getAdminNoticeList(Criteria cri, NoticeAdminVO noticeAdminVO);
	//공지 개수
	public int getTotalNoticeCount(NoticeAdminVO noticeAdminVO);
	//공지등록
	public int addNotice(NoticeAdminVO noticeAdminVO);
	//FAQ 등록
	public int addFaq(FaqVO faqVO);
	//FAQ 리스트
	public List<FaqVO> getAdminFaqList(Criteria cri, FaqVO faqVO);
	//FAQ 개수
	public int getTotalFaqCount(FaqVO faqVO);
	//공지 상세
	public NoticeAdminVO getNoticeDetail(NoticeAdminVO noticeAdminVO);
	//FAQ 상세
	public FaqVO getFaqDetail(FaqVO faqVO);
	//공지 수정
	public int editNotice(NoticeAdminVO noticeAdminVO);
	//FAQ 수정
	public int editFaq(FaqVO faqVO);
	
	
	//신고 목록
	public List<DeclareVO> getDeclareList(int amount, int pageNum, String declareContent, String declareReason); 
	//신고 개수
	public int getTotalDeclareCount(DeclareVO declareVO);
	//처리된신고 목록
	public List<DeclareVO> getClearDeclareList(int amount, int pageNum, String declareContent, String declareReason); 
	//처리된 신고 개수
	public int getTotalClearDeclareCount(DeclareVO declareVO);	
	//신고 디테일
	public DeclareVO getDeclareDetail(DeclareVO declareVO);
	
	public int editSuspendUser(String suspStatus); 
	
	//정지 사유 목록
	public List<SuspendReasonVO> getSuspReason();//보류
	//정지 등록
	public int addSuspend(SuspendVO suspendVO);
	//정지 후 신고 완료 처리
	public int editDeclareStatus(SuspendVO suspendVO);
	//회원페이지에서 정지
	public int addSuspendByAdmin(SuspendVO suspendVO);
	//정지 해제
	public int editSuspendStatus(String memberId);
	
	
	//문의 목록
	public List<InquiryVO> getInquiryList(int amount, int pageNum, String customInquiryTitle);
	//문의 개수
	public int getTotalInquiryCount(InquiryVO inquiryVO);
	//처리된 문의 목록
	public List<InquiryVO> getClearInquiryList(int amount, int pageNum, String customInquiryTitle);
	//문의 상세보기
	public InquiryVO getInquiryDetail(InquiryVO inquiryVO);
	//문의 답변 등록
	public int editCustomInquiry(InquiryVO inquiryVO);
	
	
	//구매자 리스트
	public List<MemberDetailVO> getBuyerList(int amount, int pageNum, String memberId, String memberName, String memberTel, String businessNumber, int memberAuthor);
	//판매자 리스트
	public List<MemberDetailVO> getSellerList(int amount, int pageNum, String memberId, String memberName, String memberTel, String businessNumber, int memberAuthor);	
	//유저 수
	public int getTotalUserCount(MemberDetailVO memberDetailVO);
	//클릭 시 상세보기
	public MemberDetailVO getMemberDetailValue(String memberId);
	
	
	
	//상품 목록
	public List<ProductDetailVO> getProductList(int amount, int pageNum, String memberId, String memberTel, String businessNumber, String productCode);
	//상품 개수
	public int getTotalProductCount(ProductDetailVO productDetailVO);
	//상품 삭제
	public int removeProductByAdmin(String productCode);
	
	
	//배너 관리
	public List<EventImgVO> getEventBannerList();
	//배너 등록
	public int addBanner(EventImgVO eventImgVO);
	//배너 수정
	public int editBanner(EventImgVO eventImgVO);
	//배너 path
	public String findBannerPath(int eventImgCode);
	
}
