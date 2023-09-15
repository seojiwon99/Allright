package com.ar.lighthouse.member.test;

import com.ar.lighthouse.member.service.MemberVO;

public class MemberTest {
	public static void main(String[] args) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("saleking123");
		memberVO.setBusinessNumber("5048600471");
		
		System.out.println(memberVO);
		
	}
}
