package com.ar.lighthouse.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.member.service.MemberVO;

@Mapper
public interface MemberMapper {
	//멤버 중복체크
	public int MemberCheck(String memberId);
	//멤버 회원가입
	public int MemberJoin(MemberVO memberVO);
}
