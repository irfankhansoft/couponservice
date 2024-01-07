package com.khan.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khan.springcloud.model.Coupon;
import com.khan.springcloud.repos.CouponRepo;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

	@Autowired
	CouponRepo couponRepo;
	
	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		return couponRepo.save(coupon);
	}
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code")String code) {
		return couponRepo.findByCode(code);
	}
}
