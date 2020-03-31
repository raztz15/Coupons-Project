package com.springcoupons.Spring.Coupons.Project.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcoupons.Spring.Coupons.Project.Exceptions.PriceTooLowException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueTitleException;
import com.springcoupons.Spring.Coupons.Project.Facade.CompanyFacade;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;

@RestController
@RequestMapping(path= "company")
public class CompanyService extends ClientContoller {
	
	@Autowired
	CompanyFacade companyFacade;
	
	public CompanyService() {
		super();
	}

	public CompanyService(CompanyFacade companyFacade) {
		super();
		this.companyFacade = companyFacade;
	}
	
	@PostMapping(path= "/addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		try {
			companyFacade.addCoupon(coupon);
		} catch (UniqueTitleException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Coupon>(coupon, HttpStatus.CREATED);
	}
	
	@PutMapping(path= "/updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
		companyFacade.updateCoupon(coupon);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	
	@DeleteMapping(path= "/deleteCoupon/{couponID}")
	public ResponseEntity<Void> deleteCoupon(@PathVariable("couponID") int couponID) {
		companyFacade.deleteCoupon(couponID);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path= "/coupons/{companyID}")
	public ResponseEntity<List<Coupon>> getCompanyCoupon(@PathVariable("companyID") int companyID) {
		return new ResponseEntity<List<Coupon>>(companyFacade.getAllCompanyCoupons(companyID), HttpStatus.OK);
	}
//	asking nirrrrr
	@GetMapping(path= "/coupons/{category}/{company}")
	public ResponseEntity<List<Coupon>> getCompanyCouponByCategory(@PathVariable("category") Category category, @PathVariable("company") Company company) {
		return new ResponseEntity<List<Coupon>>(companyFacade.getAllCompanyCouponsByCategory(category, company), HttpStatus.OK);
	}
	
	@GetMapping(path= "/coupons/{company}/{maxPrice}")
	public ResponseEntity<?> getCompanyCouponByMaxPrice(@PathVariable("company") Company company, @PathVariable("maxPrice") double maxPrice) {
		try {
			return new ResponseEntity<List<Coupon>>(companyFacade.getAllCompanyCouponsByMaxPrice(company, maxPrice), HttpStatus.OK);
		} catch (PriceTooLowException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Company> getCompanyDetails(@PathVariable("companyID") int companyID) {
		return new ResponseEntity<Company>(companyFacade.getCompanyDetails(companyID), HttpStatus.OK);
	}
}
