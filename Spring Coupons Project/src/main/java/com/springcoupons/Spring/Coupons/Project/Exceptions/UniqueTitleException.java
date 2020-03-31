package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class UniqueTitleException extends Exception {
	
	public UniqueTitleException() {
		super("The title you picked for this coupon already exists \nPlease pick another one!");
	}

}
