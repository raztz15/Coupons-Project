package com.springcoupons.Spring.Coupons.Project.Facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.Exceptions.PriceTooLowException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueNameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueTitleException;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@Component
public class CompanyFacade extends ClientFacade {

	private int companyID;

// 											CONSTRUCTOR
	
// an empty CTOR for SPRING to initialize this class
	public CompanyFacade() {
		super();
	}

// CTOR getting companyID
	public CompanyFacade(int companyID) {
		super();
		this.companyID = companyID;
	}
// getter of company ID
	public int getCompanyID() {
		return companyID;
	}
//											METHODS
	
//	login method
	@Override
	public boolean login(String email, String password) {
		if (companiesDBDAO.isCompanyExistsByEmailAndPassword(email, password)) {
			Company c = companiesDBDAO.getOneCompanyByEmailAndPassword(email, password);
			companyID = c.getId();
			return true;
		}
		return false;
	}
/**
 * checking if the name of the coupon exists before adding it to the system
 * @param coupon
 * @throws UniqueTitleException
 */
	public void addCoupon(Coupon coupon) throws UniqueTitleException {
		List<Coupon> coupons = couponsDBDAO.getAllCompanyCoupons(companyID);
		for (int i = 0; i < coupons.size(); i++) {
			if (coupons.get(i).getTitle().equals(coupon.getTitle())) {
				throw new UniqueTitleException();

			} else {
				couponsDBDAO.addCoupon(coupon);
			}
		}

	}
/**
 * first getting the coupon from the DB into JAVA object and then updating the coupon's details
 * @param coupon
 */
	public void updateCoupon(Coupon coupon) {
		couponsDBDAO.updateCoupon(coupon);
	}
/**
 * deleting coupon and its purchases history from the DB
 * @param couponID
 */
	public void deleteCoupon(int couponID) {
		Coupon coupon = couponsDBDAO.getOneCoupon(couponID);
		List<Customer> customersCoupon = coupon.getCustomers();
		for (Customer customer : customersCoupon) {
				couponsDBDAO.deleteCouponPurchase(customer, couponID);
				break;
			}
		couponsDBDAO.deleteCoupon(couponID);
		}
/**
 * 
 * @param companyID
 * @return list of all company coupons from the DB
 */
	public List<Coupon> getAllCompanyCoupons(int companyID) {
		return couponsDBDAO.getAllCompanyCoupons(companyID);
	}
/**
 * 
 * @param category
 * @param company
 * @return list of all company coupons by category from the DB
 */
	public List<Coupon> getAllCompanyCouponsByCategory(Category category, Company company) {
		return couponsDBDAO.getAllCompanyCouponsByCategory(category, company);
	}
/**
 * 
 * @param company
 * @param maxPrice
 * @return all company coupons from the DB by maximun price
 * @throws PriceTooLowException
 */
	public List<Coupon> getAllCompanyCouponsByMaxPrice(Company company, double maxPrice) throws PriceTooLowException {
		return couponsDBDAO.getAllCouponsByMaxPrice(company, maxPrice);
	}
/**
 * 
 * @param companyID
 * @return the company details the logged in to the system
 */
	public Company getCompanyDetails(int companyID) {
		return companiesDBDAO.getOneCompany(companyID);
	}
}
