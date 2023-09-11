package com.ar.lighthouse.member.service;


public interface MemberService {
	//멤버 회원가입
	public int MemberJoin(MemberVO memberVO);
	//멤버 중복 체크
	public int MemberCheck(String memberId);
}
