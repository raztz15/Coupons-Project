package com.springcoupons.Spring.Coupons.Project.Exceptions;

public class UniqueNameException extends Exception {
	
	public UniqueNameException() {
		
		super("Error! Name/Email already exists in the system \nPick another one!");
	}

}
