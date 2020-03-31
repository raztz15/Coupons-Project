package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class CouponAlreadyPurchasedException extends Exception {
	
	public CouponAlreadyPurchasedException() {
		super("Coupon already purchased! \nPlease pick another one!");
	}

}
