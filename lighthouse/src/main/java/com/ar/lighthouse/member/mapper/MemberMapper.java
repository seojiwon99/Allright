package com.ar.lighthouse.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ar.lighthouse.member.service.MemberVO;

@Mapper
public interface MemberMapper {
	//멤버 중복체크
	public int MemberCheck(String memberId);
	//멤버 회원가입
	public int MemberJoin(MemberVO memberVO);
	//멤버 로그인
	public MemberVO MemberLogin(MemberVO memberVO);
}
