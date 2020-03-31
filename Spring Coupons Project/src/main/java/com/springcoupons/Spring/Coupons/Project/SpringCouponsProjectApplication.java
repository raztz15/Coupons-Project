package com.springcoupons.Spring.Coupons.Project;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DBDAO.CompaniesDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CouponsDBDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CustomersDBDAO;
import com.springcoupons.Spring.Coupons.Project.Exceptions.NameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.PriceTooLowException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueNameException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UniqueTitleException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.UserDontExistsException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAlreadyPurchasedException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponAmountException;
import com.springcoupons.Spring.Coupons.Project.Exceptions.CouponEndDateExceptions;
import com.springcoupons.Spring.Coupons.Project.Exceptions.EmailAlreadyExistsException;
import com.springcoupons.Spring.Coupons.Project.Facade.AdminFacade;
import com.springcoupons.Spring.Coupons.Project.Facade.CompanyFacade;
import com.springcoupons.Spring.Coupons.Project.Facade.CustomerFacade;
import com.springcoupons.Spring.Coupons.Project.Job.CouponExpirationDailyJob;
import com.springcoupons.Spring.Coupons.Project.Login.ClientType;
import com.springcoupons.Spring.Coupons.Project.Login.LoginManager;
import com.springcoupons.Spring.Coupons.Project.beans.Category;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;
import com.springcoupons.Spring.Coupons.Project.beans.Customer;

@SpringBootApplication
public class SpringCouponsProjectApplication {

	public static void main(String[] args) throws UserDontExistsException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponsProjectApplication.class, args);
		CouponExpirationDailyJob job = context.getBean(CouponExpirationDailyJob.class);

		Thread t1 = new Thread(job);
		t1.start();

		try {
			CompaniesDBDAO companyDBDAO = context.getBean(CompaniesDBDAO.class);

			LoginManager loginManager = context.getBean(LoginManager.class);
//		CouponsDBDAO couponsDBDAO = context.getBean(CouponsDBDAO.class);
////		AdminFacade admin = context.getBean(AdminFacade.class);
			CompanyFacade companyFacade = context.getBean(CompanyFacade.class);

//		admin.login("admin@admin.com", "admin");

//		*******************************LOGIN MANAGER TEST*****************************************

//		*******************************ADMIN LOGIN*************************************************

//		AdminFacade admin = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
//		if (admin == null) {
//			try {
//				throw new UserDontExistsException();
//			} catch (UserDontExistsException e) {
//				System.out.println(e.getMessage());
//			}
//		} else {			
//			System.out.println("Admin logged in");
//		}

//		**********************************COMPANY LOGIN********************************************

			Company company1 = new Company();
			CompanyFacade company = (CompanyFacade) loginManager.login("coca@gmail.com", "coca123", ClientType.Company);
			if (company == null) {
				throw new UserDontExistsException();

			} else {
				company1 = companyDBDAO.getOneCompany(company.getCompanyID());
				System.out.println("company: " + company1.getName() + " with id: " + company1.getId() + " logged in");
			}

//		**********************************CUSTOMER LOGIN********************************************

//		CustomersDBDAO customersDBDAO = context.getBean(CustomersDBDAO.class);
//		Customer customer1 = new Customer();
//		CustomerFacade customerFacade = (CustomerFacade) loginManager.login("yoni@gmail.com", "121212", ClientType.Customer);
//		customer1 = customersDBDAO.getOneCustomer(customerFacade.getCustomerID());
//		System.out.println("Customer: " + customer1.getFirstName() + " " + customer1.getLastName() + 
//				" with ID: " + customer1.getId() + " logged in");

//		***************************ADMIN FACADE add company test****************************
//		Company company = new Company("Samsun", "samsung@gmail.com" , "samsung123");
//		try {
//			admin.addCompany(company);
//			System.out.println("company added with ID: " + company.getId());
//		} catch (UniqueNameException e) {
//			System.out.println(e.getMessage());
//		}

//		***************************ADMIN FACADE update company test******************************

//		Company company = companyDBDAO.getOneCompanyByName("Landver");
//		company.setPassword("landvers123");
//		try {
//			admin.updateCompany(company);
//		} catch (NameException e) {
//			System.out.println(e.getMessage());
//		}

//		***************************ADMIN FACADE - delete company test**************************

//		admin.deleteCompany(9);

//		**************************ADMIN FACADE - get all companies******************************

//		System.out.println(admin.getAllCompanies());

//		**************************ADMIN FACADE - get one company********************************
//		try {	
//			System.out.println(admin.getOneCompany(1));
//		} catch(UserDontExistsException e) {
//			System.out.println(e.getMessage());
//		}

//		**************************ADMIN FACADE - add customer test*******************************

//		Customer customer = new Customer("He", "Shaani", "hezi@gmail.com", "hezi123");
//		
//		try {
//			admin.addCustomer(customer);
//		} catch (EmailAlreadyExistsException e) {
//			System.out.println(e.getMessage());
//		}

//		************************ADMIN FACADE - update customer test***********************

//		customer = customersDBDAO.getOneCustomer(7);
//		customer.setEmail("hezi1234@gmail.com");
//		admin.updateCustomer(customer);

//		************************** ADMIN FACADE - delete customer test*************************

//		admin.deleteCustomer(8);

//		*************************ADMIN FACADE - get all customers test********************************

//		System.out.println(admin.getAllCustomers());

//		*************************ADMIN FACADE - get one customer****************************************

//		System.out.println(admin.getOneCustomer(6));

//		*************************COMPANY FACADE - login test******************************************

//		companyFacade.login("microsoft@gmail.co", "mic123");
//		Company c = companyDBDAO.getOneCompany(6);
//		System.out.println("Company " + c.getName() + " logged in");

//		*************************COMPANY FACADE - add coupon test*************************************

//		Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
//		Date endDate = new Date(new GregorianCalendar(2019, Calendar.AUGUST, 25).getTimeInMillis());
//		Coupon coupon1 = new Coupon(company1, Category.ELECTRICITY, "Computers", "hp computer", startDate, endDate, 13, 600, "pic of computer");
//		try {
//			companyFacade.addCoupon(coupon1);
//		} catch (UniqueTitleException e) {
//			System.out.println(e.getMessage());
//		}

//		**************************COMPANY FACADE - update coupon test***********************************
//		Coupon coupon1 = new Coupon();
//		coupon1 = couponsDBDAO.getOneCoupon(15);
//		coupon1.setAmount(35);
//		coupon1.setCategory(Category.VACATION);
//		coupon1.setDescription("Paris");
//		coupon1.setEndDate(endDate);
//		coupon1.setStartDate(startDate);
//		coupon1.setImage("pic of aiffle");
//		coupon1.setTitle("Europe");
//		
//		companyFacade.updateCoupon(coupon1);

//		*************************COMPANY FACADE - delete coupon test***********************************

//		companyFacade.deleteCoupon(5);

//		*************************COMPANY FACADE - get all company coupons test*************************

//		System.out.println(companyFacade.getAllCompanyCoupons(company.getCompanyID()));

//		************************COMPANY FACADE - get coupons by category********************************

//		company1 = companyDBDAO.getOneCompany(11);
//		System.out.println(companyFacade.getAllCompanyCouponsByCategory(Category.ELECTRICITY, company1));

//		************************COMPANY FACADE - get coupons by max price********************************

//		company1 = companyDBDAO.getOneCompany(company1.getId());
//		try {
//			System.out.println(companyFacade.getAllCompanyCouponsByMaxPrice(company1, 400));
//		} catch (PriceTooLowException e) {
//			System.out.println(e.getMessage());
//		}

//		************************COMPANY FACEDE - get company details (logged in)*************************

//		System.out.println(companyFacade.getCompanyDetails(company1.getId()));

//		************************************CUSTOMER FACADE*****************************************

			CustomerFacade customerFacade1 = context.getBean(CustomerFacade.class);

//		***************************CUSTOMER FACADE - login test**************************************

//		customerFacade.login("avi@gmail.com", "11111");
//		Customer c =  customerDBDAO.getOneCustomer(5);
//		System.out.println("Customer " + c.getFirstName() + " " + c.getLastName() + " logged in");

//		***************************CUSTOMER FACADE - add coupon purchase test************************

//		Coupon coupon1 = new Coupon();
//		coupon1 = couponsDBDAO.getOneCoupon(17);
//			customerFacade.purchaseCoupon(coupon1);
//		} 

//		****************************CUSTOMER FACADE - get customer coupons***************************

//		System.out.println(customerFacade.getCustomerCoupons());

//		***************************CUSTOMER FACADE - get customer coupons by category****************

//		System.out.println(customerFacade.getCustomerCouponsByCategory(Category.ELECTRICITY, customer1));

//		**************************CUSTOMER FACADE - get customer coupons by max price****************

//		System.out.println(customerFacade.getCustomerCouponsByMaxPrice(320, customer1));

//		****************************CUSTOMER FACADE - get customer details test***********************

//		System.out.println(customerFacade1.getCustomerDetails(1));

			job.stop();

		}
//		catch (CouponAmountException | CouponEndDateExceptions | CouponAlreadyPurchasedException e) {
//			System.out.println(e.getMessage());
//		}
		catch (UserDontExistsException e) {
			System.out.println(e.getMessage());
		}

	}

}
