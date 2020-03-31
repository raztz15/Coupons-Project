package com.springcoupons.Spring.Coupons.Project.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;

/**
 * methods for Hibernate to connect with the DB 
 * @author Owner DEKEL RAZ ZABARI
 *
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

/**
 * 
 * @param email
 * @param password
 * @return an object of company by mail and password
 */
	Company findByEmailAndPassword(String email, String password);
	
/**
 * 
 * @param companyName
 * @param companyEmail
 * @return an object of company by name or email
 */
	Company findCompanyByNameOrEmail(String companyName, String companyEmail);
	
/**
 * 
 * @param companyName
 * @return an object of company by name
 */
	Company findCompanyByName(String companyName);
	
/**
 * 
 * @param companyID
 * @return an object of company by ID 
 */
	Company findCompanyById(int companyID);

}
