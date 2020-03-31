package com.springcoupons.Spring.Coupons.Project.Facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DBDAO.CompaniesDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CouponsDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CustomersDBDAO;
import com.springcoupons.Spring.Coupons.Project.Exceptions.NameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueNameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UserDontExistsException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.EmailAlreadyExistsException;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@Component
public class AdminFacade extends ClientFacade {

//											CONSTRUCTOR
	public AdminFacade() {
		super();
	}
//											METHODS
	
//	login method
	@Override
	public boolean login(String email, String password) {
		if (email == "admin@admin.com" && password == "admin")
			return true;
		return false;
	}

/**
 * cheking if the name and email of the company already exist before adding the company to the system
 * @param newCompany
 * @throws UniqueNameException
 */
	public void addCompany(Company newCompany) throws UniqueNameException {
		if (companiesDBDAO.isCompanyExistsByNameOrEmail(newCompany.getName(), newCompany.getEmail())) {
			throw new UniqueNameException();
		} else {
			companiesDBDAO.addCompany(newCompany);
		}
	}

/**
 * updating the company's details in the DB (no option of updating the ID)
 * @param company
 * @throws NameException
 */
	public void updateCompany(Company company) throws NameException {
		if (companiesDBDAO.isCompanyExistsByName(company.getName())) {
			companiesDBDAO.updateCompany(company);
		} else {

			throw new NameException();
		}
	}

/**
 * deleting company, company's coupons and purchases history of its coupons from the DB
 * @param companyID
 */
	public void deleteCompany(int companyID) {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		List<Customer> customers = customersDBDAO.getAllCustomers();
		for (Coupon coupon : coupons) {
			if (coupon.getCompany().getId() == companyID) {
				for (Customer customer : customers) {
					customer.getCoupons().remove(coupon);
					customersDBDAO.updateCustomer(customer);
				}
				couponsDBDAO.deleteCoupon(coupon.getId());
			}
		}
		companiesDBDAO.deleteCompany(companyID);
	}

/**
 * 
 * @return list of all companies from the DB
 */
	public ArrayList<Company> getAllCompanies() {
		return companiesDBDAO.getAllCompanies();
	}

/**
 * 
 * @param companyID
 * @return an object of company from the DB by ID
 * @throws UserDontExistsException
 */
	public Company getOneCompany(int companyID) throws UserDontExistsException {
		if (companiesDBDAO.isCompanyExistsByID(companyID))
			return companiesDBDAO.getOneCompany(companyID);
		throw new UserDontExistsException();

	}

/**
 * checking if the email of the customer already exists before adding the customer to the system
 * @param customer
 * @throws EmailAlreadyExistsException
 */
	public void addCustomer(Customer customer) throws EmailAlreadyExistsException {
		if (customersDBDAO.isCustomerExistsByEmail(customer.getEmail())) {
			throw new EmailAlreadyExistsException();
		} else {
			customersDBDAO.addCustomer(customer);
		}
	}

/**
 * updating the customer's details in the DB (no option of updating the ID)
 * @param customer
 */
	public void updateCustomer(Customer customer) {
		customersDBDAO.updateCustomer(customer);
	}

/**
 * deleting customer and all of his purchases history from the DB
 * @param customerID
 */
	public void deleteCustomer(int customerID) {
		Customer c = customersDBDAO.getOneCustomer(customerID);
		if (c.getCoupons() != null) {
			c.removeAllPurchases();
			customersDBDAO.updateCustomer(c);
		}
		customersDBDAO.deleteCustomer(customerID);
	}

/**
 * 
 * @return list of all customers from the DB 
 */
	public ArrayList<Customer> getAllCustomers() {
		return customersDBDAO.getAllCustomers();
	}

/**
 * 
 * @param customerID
 * @return an object of customer from the DB
 */
	public Customer getOneCustomer(int customerID) {
		return customersDBDAO.getOneCustomer(customerID);
	}

}
