package com.ar.lighthouse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ar.lighthouse.cart.service.CartVO;
import com.ar.lighthouse.product.service.OptionDetailVO;
@Mapper
public interface CartMapper {
	
	List<CartVO> selectCartList(String memberId);
	
	int deleteCart(String memberId, int cartCode);
	
	public int insertCart(CartVO cartVO);
	
	OptionDetailVO selectOptionCode(OptionDetailVO optionDetailVO);
	
	public int checkCart(CartVO vo);
}
