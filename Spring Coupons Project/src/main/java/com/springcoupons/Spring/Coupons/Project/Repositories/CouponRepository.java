package com.springcoupons.Spring.Coupons.Project.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;

/**
 * methods for Hibernate to connect with the DB 
 * @author Owner DEKEL RAZ ZABARI
 *
 */
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

/**
 * 
 * @param companyID
 * @return list of coupons by companyID
 */
	List<Coupon> findByCompanyId(int companyID);
	
/**
 * 
 * @param category
 * @param company
 * @return list of coupons by category and company
 */
	List<Coupon> findByCategoryAndCompany(Category category, Company company);
	
/**
 * 
 * @param company
 * @param maxPrice
 * @return list of coupons by max price and company
 */
	List<Coupon> findByCompanyAndPriceLessThanEqual(Company company, double maxPrice);

}
