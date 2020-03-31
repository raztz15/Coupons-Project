package com.springcoupons.Spring.Coupons.Project.DBDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DAO.CustomersDAO;
import com.springcoupons.Spring.Coupons.Project.Repositories.CustomerRepository;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@Component
@Scope("prototype")
public class CustomersDBDAO implements CustomersDAO {

	@Autowired
	private CustomerRepository customerRepo;

	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public void deleteCustomer(int customerID) {
		customerRepo.deleteById(customerID);
	}

	public void updateCustomer(Customer customer) {
		if (customerRepo.existsById(customer.getId()))
			customerRepo.save(customer);
	}

	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) customerRepo.findAll();
	}

	public Customer getOneCustomer(int customerID) {
		Optional<Customer> c = customerRepo.findById(customerID);
		if (c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}

	public boolean isCustomerExists(String email, String password) {
		if (customerRepo.findByEmailAndPassword(email, password) != null)
			return true;
		return false;
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) {
		if (customerRepo.findByEmail(email) != null)
			return true;
		return false;
	}
	
	@Override
	public Customer getCustomerByEmailAndPassword(String email, String password) {
		return customerRepo.findByEmailAndPassword(email, password);
	}
	
}
