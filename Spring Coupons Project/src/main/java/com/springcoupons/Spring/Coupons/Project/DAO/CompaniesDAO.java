package com.springcoupons.Spring.Coupons.Project.DAO;

import java.util.ArrayList;

import com.springcoupons.Spring.Coupons.Project.beans.Company;
/**
 * Interface class for company connection to DB that has the following methods.
 * @author Owner Dekel Raz Zabari
 *
 */
public interface CompaniesDAO {
	
/**
 * method for adding a company object to the DB 
 * @param company
 */
	void addCompany(Company company);

/**
 * delete company from the DB
 * @param companyID
 */
	void deleteCompany(int companyID);
	
/**
 * update company in the DB
 * @param company
 */
	void updateCompany(Company company);
	
/**
 * 
 * @return list of all companies from the DB
 */
	ArrayList<Company> getAllCompanies();
	
/**
 * 
 * @param companyID
 * @return object of company from the DB
 */
	Company getOneCompany(int companyID);
	
/**
 * 
 * @param companyName
 * @return company by name
 */
	Company getOneCompanyByName(String companyName);
	
/**
 * checking if company exists in DB by email and password
 * @param email
 * @param password
 * @return
 */
	boolean isCompanyExistsByEmailAndPassword(String email, String password);
	
/**
 * 
 * @param companyName
 * @param companyEmail
 * @return checking if company exists in DB by name or email
 */
	boolean isCompanyExistsByNameOrEmail(String companyName, String companyEmail);
	
/**
 * 
 * @param companyName
 * @return checking if company exists in DB by name
 */
	boolean isCompanyExistsByName(String companyName);
	
/**
 * 
 * @param email
 * @param password
 * @return company by email and password
 */
	Company getOneCompanyByEmailAndPassword(String email, String password);

/**
 * checking if the company exist by ID in the DB
 * @param companyID
 * @return
 */
	boolean isCompanyExistsByID(int companyID);
}
