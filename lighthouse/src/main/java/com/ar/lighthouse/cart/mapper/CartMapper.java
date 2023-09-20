package com.ar.lighthouse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.cart.service.CartVO;
@Mapper
public interface CartMapper {
	
	List<CartVO> selectCartList(String memberId);
	
	int deleteCart(String memberId, int cartCode);
	
}
