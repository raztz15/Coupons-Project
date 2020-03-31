package com.springcoupons.Spring.Coupons.Project.DAO;

import java.util.ArrayList;
import java.util.List;

import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;
/**
 * Interface class for coupon connection to DB that has the following methods.
 * @author Owner Dekel Raz Zabari
 *
 */
public interface CouponsDAO {
	
/**
 * adding coupon to the DB
 * @param coupon
 */
	void addCoupon(Coupon coupon);
	
/**
 * deleting coupon from the DB
 * @param couponID
 */
	void deleteCoupon(int couponID);
	
/**
 * updating coupon in the DB
 * @param coupon
 */
	void updateCoupon(Coupon coupon);
	
/**
 * 
 * @return list of all the coupons from the DB
 */
	ArrayList<Coupon> getAllCoupons();

/**
 * 
 * @param couponID
 * @return coupon by ID from the DB
 */
	Coupon getOneCoupon(int couponID);
	
/**
 * adding customer and coupon object to the join table to create purchase history
 * @param c
 * @param couponID
 */
	void addCouponPurchase(Customer c, int couponID);
	
/**
 * removing customer and coupon object from the join table to remove the purchase history
 * @param c
 * @param couponID
 */
	void deleteCouponPurchase(Customer c, int couponID);
	
/**
 * 
 * @param companyID
 * @return all the coupons of the company
 */
	List<Coupon> getAllCompanyCoupons(int companyID);
	
/**
 * 
 * @param category
 * @param company
 * @return all company coupons by category
 */
	List<Coupon> getAllCompanyCouponsByCategory(Category category, Company company);
	
/**
 * 
 * @param company
 * @param maxPrice
 * @return all company coupons by max price
 */
	List<Coupon> getAllCouponsByMaxPrice(Company company, double maxPrice);
	
/**
 * 
 * @param category
 * @param customer
 * @return all customer coupons by category
 */
	List<Coupon> getAllCustomerCouponsByCategory(Category category, Customer customer);
	
/**
 * 
 * @param customer
 * @param maxPrice
 * @return all customer coupons by max price
 */
	List<Coupon> getAllCustomerCouponsByMaxPrice(Customer customer, double maxPrice);
}
