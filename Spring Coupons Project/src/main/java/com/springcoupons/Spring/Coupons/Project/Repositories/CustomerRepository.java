package com.springcoupons.Spring.Coupons.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcoupons.Spring.Coupons.Project.beans.Customer;

/**
 * methods for Hibernate to connect with the DB 
 * @author Owner DEKEL RAZ ZABARI
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
/**
 * 
 * @param email
 * @return an object of customer by email
 */
	Object findByEmail(String email);
	
/**
 * 
 * @param email
 * @param password
 * @return an object of customer by email and password
 */
	Customer findByEmailAndPassword(String email, String password);
}
