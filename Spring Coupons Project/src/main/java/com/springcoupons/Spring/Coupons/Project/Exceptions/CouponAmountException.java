package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class CouponAmountException extends Exception {
	
	public CouponAmountException() {
		super("This coupon has reached its maximum limit");
	}
}
