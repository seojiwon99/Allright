package com.ar.lighthouse.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.member.service.MemberVO;

@Mapper
public interface MemberMapper {
	//멤버 회원가입
	public int insertMember(MemberVO memberVO);
	//멤버 중복체크
	public int selectMemberCheck(String memberId);
	//멤버 로그인
	public MemberVO MemberLogin(MemberVO memberVO);
	//멤버 존재 체크 
	public int MemberLoginBefore(MemberVO memberVO);
	//멤버 이메일 체크
	public MemberVO selectMemberEmailCheck(MemberVO memberVO);
	//멤버 아이디,이메일 크로스체크
	public int selectMemberCrossCheck(MemberVO memberVO);
	
	public int updateMemberPassword(MemberVO memberVO);
}
