package com.ar.lighthouse.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public int MemberCheck(String memberId);
}
