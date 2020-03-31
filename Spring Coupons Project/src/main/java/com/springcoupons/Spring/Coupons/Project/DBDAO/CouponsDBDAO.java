package com.springcoupons.Spring.Coupons.Project.DBDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DAO.CouponsDAO;
import com.springcoupons.Spring.Coupons.Project.Repositories.CouponRepository;
import com.springcoupons.Spring.Coupons.Project.Repositories.CustomerRepository;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;
@Component
@Scope("prototype")
public class CouponsDBDAO implements CouponsDAO{

	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private CustomersDBDAO customerDBDAO;

	@Override
	public void addCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}
	
	@Override
	public void updateCoupon(Coupon coupon) {
		if(couponRepo.existsById(coupon.getId())) {
			couponRepo.save(coupon);
		}
	}

	@Override
	public void deleteCoupon(int couponID) {
		Coupon c = getOneCoupon(couponID);
		c.setCompany(null);
		updateCoupon(c);
		couponRepo.deleteById(couponID);
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() {
		return (ArrayList<Coupon>) couponRepo.findAll();
	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		Optional<Coupon> c = couponRepo.findById(couponID);
		if(c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void addCouponPurchase(Customer c, int couponID) {
		Coupon coupon = getOneCoupon(couponID);
		c = customerDBDAO.getOneCustomer(c.getId());
		c.addCouponPurchase(coupon);
		coupon.addPurchase(c);
//		updateCoupon(coupon);
//		customerDBDAO.updateCustomer(c);
		
	}

	@Override
	public void deleteCouponPurchase(Customer c, int couponID) {
		c.deleteCouponPurchase(couponID);
		customerDBDAO.updateCustomer(c);
		
	}
	
	@Override
	public List<Coupon> getAllCompanyCoupons(int companyID) {
		return couponRepo.findByCompanyId(companyID);
	}
	
	@Override
	public List<Coupon> getAllCompanyCouponsByCategory(Category category, Company company) {
		return couponRepo.findByCategoryAndCompany(category, company);
	}
	
	@Override
	public List<Coupon> getAllCouponsByMaxPrice(Company company, double maxPrice) {
		return couponRepo.findByCompanyAndPriceLessThanEqual(company, maxPrice);
		
	}
	
	@Override
	public List<Coupon> getAllCustomerCouponsByCategory(Category category, Customer customer) {
		List<Coupon> coupons = customer.getCoupons();
		List<Coupon> coupons2 = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if(coupon.getCategory().equals(category)) {
				coupons2.add(coupon);
			}
		}
		 return coupons2;
	}
	
	@Override
	public List<Coupon> getAllCustomerCouponsByMaxPrice(Customer customer, double maxPrice) {
		List<Coupon> coupons = customer.getCoupons();
		List<Coupon> coupons2 = new ArrayList<>();
 		for (Coupon coupon : coupons) {
			if(coupon.getPrice() <= maxPrice) {
				coupons2.add(coupon);
			} 
 		} return coupons2;
	}
}
