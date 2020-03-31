package com.springcoupons.Spring.Coupons.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springcoupons.Spring.Coupons.Project.Exceptions.UserDontExistsException;
import com.springcoupons.Spring.Coupons.Project.Login.LoginManager;

public class CouponProjectTest {

	public static void main(String[] args) throws UserDontExistsException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponsProjectApplication.class, args);
		
		
		LoginManager loginManager = context.getBean(LoginManager.class);
		
		
		
	}

}
