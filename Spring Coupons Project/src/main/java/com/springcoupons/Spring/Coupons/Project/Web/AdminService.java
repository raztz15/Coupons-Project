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

import com.springcoupons.Spring.Coupons.Project.Exceptions.EmailAlreadyExistsException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.NameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueNameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UserDontExistsException;
import com.springcoupons.Spring.Coupons.Project.Facade.AdminFacade;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@RestController
@RequestMapping("admin")
public class AdminService extends ClientContoller {

	@Autowired
	AdminFacade adminFacade;

	public AdminService() {
		super();
	}

	public AdminService(AdminFacade adminFacade) {
		super();
		this.adminFacade = adminFacade;
	}

	@PostMapping(path = "/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		try {
			adminFacade.addCompany(company);
		} catch (UniqueNameException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Company>(company, HttpStatus.CREATED);
	}

	@PutMapping(path = "/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		try {
			adminFacade.updateCompany(company);
		} catch (NameException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@DeleteMapping(path = "deleteCompany/{companyID}")
	public ResponseEntity<Void> deleteCompany(@PathVariable("companyID") int companyID) {
		adminFacade.deleteCompany(companyID);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/companies")
	public ResponseEntity<List<Company>> getAllCompanies() {
		return new ResponseEntity<List<Company>>(adminFacade.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping(path = "/oneCompany/{companyID}")
	public ResponseEntity<?> getOneCompany(@PathVariable("companyID") int companyID) {
		try {
			return new ResponseEntity<Company>(adminFacade.getOneCompany(companyID), HttpStatus.OK);
		} catch (UserDontExistsException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			adminFacade.addCustomer(customer);
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

	@PutMapping(path = "/updateCustomer")
	public ResponseEntity<?> customerCompany(@RequestBody Customer customer) {
		adminFacade.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping(path = "deleteCustomer/{customerID}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("customerID") int customerID) {
		adminFacade.deleteCustomer(customerID);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(adminFacade.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping(path = "/oneCystomer/{customerID}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("customerID") int customerID) {
		return new ResponseEntity<Customer>(adminFacade.getOneCustomer(customerID), HttpStatus.OK);
	}
}
