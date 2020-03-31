package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class CouponEndDateExceptions extends Exception {
	
	public CouponEndDateExceptions() {
		super("Coupon has already expired! Sorry!");
	}

}
