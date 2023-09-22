/* 
 개발자 : 강민호
 개발일자 : 2023/09/14
 		구매자 마이페이지
 */
package com.ar.lighthouse.buyp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ar.lighthouse.buyp.service.BuyCancelVO;
import com.ar.lighthouse.buyp.service.BuyExchangeVO;
import com.ar.lighthouse.buyp.service.BuyInfoVO;
import com.ar.lighthouse.buyp.service.BuyReturnVO;
import com.ar.lighthouse.buyp.service.BuyerPageService;
import com.ar.lighthouse.buyp.service.CodeVO;
import com.ar.lighthouse.buyp.service.CouponVO;
import com.ar.lighthouse.buyp.service.DetailVO;
import com.ar.lighthouse.buyp.service.MyInquiryVO;
import com.ar.lighthouse.buyp.service.TradeVO;
import com.ar.lighthouse.buyp.service.WishVO;
import com.ar.lighthouse.member.service.MemberVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BuyerPageController {

	@Autowired
	BuyerPageService buyerPageService;

	// 주문목록
	@GetMapping("page/buyer/orderList")
	public String orderList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<DetailVO> orderList = buyerPageService.getDetailList(memberId);
		model.addAttribute("orderList", orderList);

		return "/page/buyer/orderList";
	}

	// 구매자 개인정보
	@GetMapping("page/buyer/personalInfo")
	public String personalInfo(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		BuyInfoVO personalInfo = buyerPageService.getInfo(memberId);
		model.addAttribute("personalInfo", personalInfo);

		return "/page/buyer/personalInfo";
	}

	// 취소/반품/교환
	@GetMapping("page/buyer/tradeList")
	public String TradeList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<TradeVO> tradeList = buyerPageService.getTradeList(memberId);
		model.addAttribute("tradeList", tradeList);

		return "/page/buyer/tradeList";
	}

	// 쿠폰내역
	@GetMapping("page/buyer/myCoupon")
	public String MyCoupon(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<CouponVO> myCoupon = buyerPageService.getCouponList(memberId);
		model.addAttribute("myCoupon", myCoupon);

		return "/page/buyer/myCoupon";
	}

	// 문의내역
	@GetMapping("page/buyer/myInquiry")
	public String myInquiry(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<MyInquiryVO> myInquiry = buyerPageService.getMyQuiryList(memberId);
		model.addAttribute("myInquiry", myInquiry);

		return "/page/buyer/myInquiry";
	}

	// 찜내역
	@GetMapping("page/buyer/wishList")
	public String wishList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<WishVO> wishList = buyerPageService.getWishList(memberId);
		model.addAttribute("wishList", wishList);

		return "/page/buyer/wishList";
	}

	// 취소 상세
	@GetMapping("page/buyer/cancelList")
	public String cancelList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<BuyCancelVO> cancelList = buyerPageService.getCancelList(memberId);

		model.addAttribute("cancelList", cancelList);

		return "/page/buyer/cancelList";
	}

	// 반품 상세
	@GetMapping("page/buyer/returnList")
	public String ReturnList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<BuyReturnVO> returnList = buyerPageService.getReturnList(memberId);

		model.addAttribute("returnList", returnList);

		return "/page/buyer/returnList";
	}

	// 교환 상세
	@GetMapping("page/buyer/exchangeList")
	public String ExchangeList(Model model, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		List<BuyExchangeVO> exchangeList = buyerPageService.getExchangeList(memberId);

		model.addAttribute("exchangeList", exchangeList);

		return "/page/buyer/exchangeList";
	}

	// 교환 신청 페이지
	@GetMapping("page/buyer/exchange")
	public String exchangeForm(Model model, HttpSession session, @RequestParam int orderDetailCode) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		CodeVO codeVO = new CodeVO();
		codeVO.setOrderDetailCode(orderDetailCode);
		codeVO.setMemberId(memberId);
		model.addAttribute("exchange", buyerPageService.getExchangePage(codeVO));

		List<CodeVO> codeList = buyerPageService.getExchangeCode(memberId);
		model.addAttribute("codeList", codeList);

		return "page/buyer/exchange";
	}

	// 취소 신청 페이지
	@GetMapping("page/buyer/cancel")
	public String cancelForm(Model model, HttpSession session, @RequestParam int orderDetailCode) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		CodeVO codeVO = new CodeVO();
		codeVO.setOrderDetailCode(orderDetailCode);
		codeVO.setMemberId(memberId);
		model.addAttribute("cancel", buyerPageService.getCancelPage(codeVO));

		List<CodeVO> codeList = buyerPageService.getCancelCode(memberId);
		model.addAttribute("codeList", codeList);

		return "page/buyer/cancel";
	}

	// 반품 신청 페이지
	@GetMapping("page/buyer/return")
	public String returnForm(Model model, HttpSession session, @RequestParam int orderDetailCode) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		String memberId = memberVO.getMemberId();

		CodeVO codeVO = new CodeVO();
		codeVO.setOrderDetailCode(orderDetailCode);
		codeVO.setMemberId(memberId);
		model.addAttribute("return", buyerPageService.getReturnPage(codeVO));

		List<CodeVO> codeList = buyerPageService.getReturnCode(memberId);
		model.addAttribute("codeList", codeList);

		return "page/buyer/return";
	}

	// 교환
	@PostMapping("buyer/exchangeInsert")

	public ResponseEntity<String> addExchange(@RequestBody BuyExchangeVO excVO, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		excVO.setMemberId(memberVO.getMemberId());

		int insertExchange = buyerPageService.addExchange(excVO);
		return insertExchange == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 취소
	@PostMapping("buyer/cancelInsert")
	public ResponseEntity<String> addCancel(@RequestBody BuyCancelVO canVO, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		canVO.setMemberId(memberVO.getMemberId());

		int insertCancel = buyerPageService.addCancel(canVO);
		return insertCancel == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 반품
	@PostMapping("buyer/returnInsert")
	public ResponseEntity<String> addReturn(@RequestBody BuyReturnVO retVO, HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		retVO.setMemberId(memberVO.getMemberId());

		int insertReturn = buyerPageService.addReturn(retVO);
		return insertReturn == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 개인정보수정
	@PostMapping("buyer/personalInfoEdit")
	public ResponseEntity<String> editInfo(@RequestBody BuyInfoVO buyInfoVO, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		buyInfoVO.setMemberId(memberVO.getMemberId());
		
		int updateInfo = buyerPageService.editInfo(buyInfoVO);
		return updateInfo == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("buyer/cancelUpdate")
	public ResponseEntity<String> editCancel(@RequestBody BuyCancelVO canVO, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		canVO.setMemberId(memberVO.getMemberId());
		
		int updateCancel = buyerPageService.editCancel(canVO);
		return updateCancel == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("buyer/returnUpdate")
	public ResponseEntity<String> editReturn(@RequestBody BuyReturnVO retVO, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		retVO.setMemberId(memberVO.getMemberId());
		
		int updateReturn = buyerPageService.editReturn(retVO);
		return updateReturn == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("buyer/exchangeUpdate")
	public ResponseEntity<String> editExchange(@RequestBody BuyExchangeVO excVO, HttpSession session) {
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
		excVO.setMemberId(memberVO.getMemberId());
		
		int updateExchange = buyerPageService.editExchange(excVO);
		return updateExchange == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
