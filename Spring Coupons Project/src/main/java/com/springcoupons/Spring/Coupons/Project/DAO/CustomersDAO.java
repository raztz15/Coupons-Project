package com.springcoupons.Spring.Coupons.Project.DAO;

import java.util.ArrayList;

import com.springcoupons.Spring.Coupons.Project.beans.Customer;
/**
 * Interface class for customer connection to DB that has the following methods.
 * @author Owner Dekel Raz Zabari
 *
 */
public interface CustomersDAO {

/**
 * adding customer to the DB
 * @param customer
 */
	void addCustomer(Customer customer);
	
/**
 * removing customer from the DB
 * @param customerID
 */
	void deleteCustomer(int customerID);
	
/**
 * update customer in the DB
 * @param customer
 */
	void updateCustomer(Customer customer);
	
/**
 * 
 * @return a list of customers from the DB
 */
	ArrayList<Customer> getAllCustomers();
	
/**
 * 
 * @param customerID
 * @return an object of customer from the DB
 */
	Customer getOneCustomer(int customerID);
	
/**
 * 
 * @param email
 * @param password
 * @return true or false if the customer exists in the DB by mail and password
 */
	boolean isCustomerExists(String email, String password);
	
/**
 * 
 * @param email
 * @return true or false if the customer exists in the DB by mail
 */
	boolean isCustomerExistsByEmail(String email);
	
/**
 * 
 * @param email
 * @param password
 * @return an object of customer from the DB
 */
	Customer getCustomerByEmailAndPassword(String email, String password);

}
