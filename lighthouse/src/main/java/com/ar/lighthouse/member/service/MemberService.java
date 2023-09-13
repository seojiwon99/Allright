package com.ar.lighthouse.member.service;


public interface MemberService {
	//멤버 회원가입
	public int MemberJoin(MemberVO memberVO);
	//멤버 중복 체크
	public int MemberCheck(String memberId);
	//멤버 로그인 체크
	public MemberVO memberLogin(MemberVO memberVO);
	//멤버 이메일 존재 체크
	public MemberVO memberEmailCheck(MemberVO memberVO);
	//멤버 이메일, 아이디 일치 체크
	public int memberCrossCheck(MemberVO memberVO);
}
