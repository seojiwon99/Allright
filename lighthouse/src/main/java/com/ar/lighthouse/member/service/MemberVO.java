package com.ar.lighthouse.member.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class MemberVO{
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberBirth;
	private String memberAddr;
	private String memberDetailsAddr;
	private String memberEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memberRegdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memberEnddate;
	private String businessNumber;
	private int memberAuthor;
	private String memberTel;
	private String authorities;
	
	
	
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_"+memberAuthor));
		return list;
	}
	public MemberVO hashPassword(PasswordEncoder passwordEncoder) {
		    this.memberPw = passwordEncoder.encode(this.memberPw);
		    return this;
	}
	List<MemberVO> memberList;
	

}
