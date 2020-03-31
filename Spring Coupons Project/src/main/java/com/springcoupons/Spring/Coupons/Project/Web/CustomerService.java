package com.springcoupons.Spring.Coupons.Project.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAlreadyPurchasedException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAmountException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponEndDateExceptions;
import com.springcoupons.Spring.Coupons.Project.Exceptions.PriceTooLowException;
import com.springcoupons.Spring.Coupons.Project.Facade.CustomerFacade;
import com.springcoupons.Spring.Coupons.Project.Web.ClientContoller;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@RestController
@RequestMapping("customer")
public class CustomerService extends ClientContoller {

	@Autowired
	CustomerFacade customerFacade;

	public CustomerService() {
		super();
	}

	public CustomerService(CustomerFacade customerFacade) {
		super();
		this.customerFacade = customerFacade;
	}

	@PostMapping(path = "/purchase")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon) {
		try {
			customerFacade.purchaseCoupon(coupon);
		} catch (CouponAmountException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (CouponEndDateExceptions e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (CouponAlreadyPurchasedException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Coupon>(coupon, HttpStatus.CREATED);

	}

	@GetMapping(path = "/coupons")
	public ResponseEntity<List<Coupon>> getCustomerCoupons() {
		return new ResponseEntity<List<Coupon>>(customerFacade.getCustomerCoupons(), HttpStatus.OK);
	}

	@GetMapping(path = "/coupons/{category}/{customer}")
	public ResponseEntity<List<Coupon>> getCustomerCouponsByCategory(@PathVariable("category") Category category,
			@PathVariable("customer") Customer customer) {
		return new ResponseEntity<List<Coupon>>(customerFacade.getCustomerCouponsByCategory(category, customer),
				HttpStatus.OK);
	}

	@GetMapping(path = "/coupons/{maxPrice}/{customer}")
	public ResponseEntity<List<Coupon>> getCustomerCouponsByMaxPrice(@PathVariable("maxPrice") double maxPrice,
			@PathVariable("customer") Customer customer) {
		return new ResponseEntity<List<Coupon>>(customerFacade.getCustomerCouponsByMaxPrice(maxPrice, customer),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Customer> getCustomerDetails() {
		return new ResponseEntity<Customer>(customerFacade.getCustomerDetails(), HttpStatus.OK);
	}
}
