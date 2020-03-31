package com.springcoupons.Spring.Coupons.Project.Facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.rmi.CORBA.UtilDelegate;

import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAlreadyPurchasedException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAmountException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponEndDateExceptions;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@Component
public class CustomerFacade extends ClientFacade {

	private Customer customer;

// 											CONSTRUCTOR
	public CustomerFacade() {
		super();
	}
	
	public CustomerFacade(Customer customer) {
		super();
		this.customer = customer;
	}
// getter of customer
	public Customer getCustomer() {
		return customer;
	}
// getter of customerID	
	public int getCustomerID() {
		return customer.getId();
	}
// 											METHODS
	
/**
 * login method
 */
	@Override
	public boolean login(String email, String password) {
		if (customersDBDAO.isCustomerExists(email, password)) {
			Customer c = customersDBDAO.getCustomerByEmailAndPassword(email, password);
			customer = c;
			return true;
		}
		return false;
	}
/**
 * adding coupon to the purchases history, checking if the amount of the coupon is bigger than 0
 * and decrement the amount of the coupon in the DB 
 * @param coupon
 * @throws CouponAmountException
 * @throws CouponEndDateExceptions
 * @throws CouponAlreadyPurchasedException
 */
	public void purchaseCoupon(Coupon coupon)
			throws CouponAmountException, CouponEndDateExceptions, CouponAlreadyPurchasedException {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		
		List<Coupon> coupons = customer.getCoupons();
		for (Coupon currentCoupon : coupons) {

			if (currentCoupon.getId() == coupon.getId()) {
				throw new CouponAlreadyPurchasedException();
			}
		}

		if (coupon.getEndDate().before(date)) {
			throw new CouponEndDateExceptions();
		}

		if (coupon.getAmount() <= 0) {
			throw new CouponAmountException();
		}
		coupon.setAmount(coupon.getAmount() - 1);
		couponsDBDAO.updateCoupon(coupon);
		couponsDBDAO.addCouponPurchase(customer, coupon.getId());
	}
/**
 * 
 * @return all customer coupons from the DB
 */
	public List<Coupon> getCustomerCoupons() {
		return customer.getCoupons();
	}
/**
 * 
 * @param category
 * @param customer
 * @return all customer coupons from the DB by category
 */
	public List<Coupon> getCustomerCouponsByCategory(Category category, Customer customer) {
		return couponsDBDAO.getAllCustomerCouponsByCategory(category, customer);
	}
/**
 * 
 * @param maxPrice
 * @param customer
 * @return all customer coupons from the DB by maximum price
 */
	public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice, Customer customer) {
		return couponsDBDAO.getAllCustomerCouponsByMaxPrice(customer, maxPrice);
	}
/**
 * 
 * @return customer details that logged in to the system
 */
	public Customer getCustomerDetails() {
		return customer;
	}
}
