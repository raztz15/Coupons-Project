package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class UserDontExistsException extends Exception {
	
	public UserDontExistsException() {
		super("User Don't exist in the system! \nTry again!");
	}

}
