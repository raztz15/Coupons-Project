package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class EmailAlreadyExistsException extends Exception {
	
	public EmailAlreadyExistsException() {
		super("Email already exists in the system! pick another one!");
	}

}
