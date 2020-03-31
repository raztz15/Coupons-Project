package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class PriceTooLowException extends Exception {
	
	public PriceTooLowException() {
		super("There are no coupons under this price for this company! \nSorry you can't afford us!");
	}
}
