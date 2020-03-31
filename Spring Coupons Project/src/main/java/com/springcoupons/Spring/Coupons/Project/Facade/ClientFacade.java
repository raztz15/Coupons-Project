package com.springcoupons.Spring.Coupons.Project.Facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.springcoupons.Spring.Coupons.Project.DBDAO.CompaniesDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CouponsDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CustomersDBDAO;


public abstract class ClientFacade {
	
	@Autowired
	protected CompaniesDBDAO companiesDBDAO;
	@Autowired
	protected CustomersDBDAO customersDBDAO;
	@Autowired
	protected CouponsDBDAO couponsDBDAO;
	
	public boolean login(String email, String password) {
		return false;
	}

}
