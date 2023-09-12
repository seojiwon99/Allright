package com.ar.lighthouse.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.member.mapper.MemberMapper;
import com.ar.lighthouse.member.service.MemberService;
import com.ar.lighthouse.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	
	@Override
	public int MemberCheck(String memberId) {
		return memberMapper.MemberCheck(memberId);
	}


	@Override
	public int MemberJoin(MemberVO memberVO) {
		return memberMapper.MemberJoin(memberVO);
	}


	@Override
	public MemberVO memberLogin(MemberVO memberVO) {
		if(memberMapper.MemberLoginBefore(memberVO)==0) {
			MemberVO nullMember = new MemberVO();
			return nullMember;
		}
		return memberMapper.MemberLogin(memberVO);
	}
	
}
