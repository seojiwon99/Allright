package com.ar.lighthouse.coupon.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.lighthouse.coupon.mapper.CouponMapper;
import com.ar.lighthouse.coupon.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	CouponMapper couponMapper;
	
	@Override
	public int addCoupon(String memberId) {
		return couponMapper.insertCoupon(memberId);
	}

	
	
}
