package com.springcoupons.Spring.Coupons.Project.Login;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springcoupons.Spring.Coupons.Project.Facade.AdminFacade;
import com.springcoupons.Spring.Coupons.Project.Facade.ClientFacade;
import com.springcoupons.Spring.Coupons.Project.Facade.CompanyFacade;
import com.springcoupons.Spring.Coupons.Project.Facade.CustomerFacade;

@Service
public class LoginManager {
	
	
	@Autowired
	private AdminFacade adminUser;
	@Autowired
	private CompanyFacade companyUser;
	@Autowired
	private CustomerFacade customerUser;

//	an empty CTOR for SPRING to initialize the class 
	public LoginManager() {
		
	}
	
/**
*
* @param email
* @param password
* @param clientType
* @return client facade type (administrator / company / customer)
*/
	public ClientFacade login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Administrator:
			if(adminUser.login(email, password)) {
				return adminUser;
			} break;
		case Company:
			if(companyUser.login(email, password)) {
				return companyUser;
			} break;
		case Customer:
			if(customerUser.login(email, password)) {
				return customerUser;
			} break;
		} 
		return null;
	}

}
