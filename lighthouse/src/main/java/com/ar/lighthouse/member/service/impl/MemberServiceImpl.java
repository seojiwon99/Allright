package com.ar.lighthouse.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.member.mapper.MemberMapper;
import com.ar.lighthouse.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	
	@Override
	public int MemberCheck(String memberId) {
		return memberMapper.MemberCheck(memberId);
	}
	
}
