package com.ar.lighthouse.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.member.mapper.MemberMapper;
import com.ar.lighthouse.member.service.MemberVO;

public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberMapper.selectMemberVO(username);
				
		return vo == null ? null : new CustomUser(vo);
	}
	
	
}
