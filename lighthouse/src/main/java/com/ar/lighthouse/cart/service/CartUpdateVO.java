package com.ar.lighthouse.cart.service;

import java.util.List;

import com.ar.lighthouse.product.service.OptionDetailVO;
import com.ar.lighthouse.product.service.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartUpdateVO {

	private int cartCode;
	private int cartCount;

}
