package com.ar.lighthouse.member.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ar.lighthouse.member.service.MemberVO;

public class CustomUser extends User{
	
	private MemberVO memberVO;
	
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	public CustomUser(MemberVO memberVO) {
		super(memberVO.getMemberId(),memberVO.getMemberPw(), true, true, true, true, memberVO.getAuthorities().stream().map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList()));
		this.memberVO = memberVO;
	}

	public MemberVO getMemberVo() {
		return memberVO;
	}

	public void setMemberVo(MemberVO empvo) {
		this.memberVO = empvo;
	}
    
	
	
}