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
	public int addMember(MemberVO memberVO) {
		return memberMapper.insertMember(memberVO);
	}

	
	@Override
	public int getMemberCheck(String memberId) {
		return memberMapper.selectMemberCheck(memberId);
	}


	@Override
	public MemberVO memberLogin(MemberVO memberVO) {
		if(memberMapper.MemberLoginBefore(memberVO)==0) {
			MemberVO nullMember = new MemberVO();
			return nullMember;
		}
		return memberMapper.MemberLogin(memberVO);
	}


	@Override
	public MemberVO getMemberEmailCheck(MemberVO memberVO) {
		MemberVO tmpVO = new MemberVO();
		tmpVO = memberMapper.selectMemberEmailCheck(memberVO);
		if(tmpVO == null) {
			return new MemberVO();
		}else {
			return tmpVO;
		}
	}


	@Override
	public int getMemberCrossCheck(MemberVO memberVO) {
		return memberMapper.selectMemberCrossCheck(memberVO);
	}


	@Override
	public boolean editMemberPassword(MemberVO memberVO) {
		if(memberMapper.updateMemberPassword(memberVO)>0) {
			return true;
		}else{
			return false;
		}
	}
	
	
}
