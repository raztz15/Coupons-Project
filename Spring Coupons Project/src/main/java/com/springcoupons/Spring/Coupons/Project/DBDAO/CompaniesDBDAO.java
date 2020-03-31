package com.springcoupons.Spring.Coupons.Project.DBDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DAO.CompaniesDAO;
import com.springcoupons.Spring.Coupons.Project.Repositories.CompanyRepository;
import com.springcoupons.Spring.Coupons.Project.beans.Company;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;

@Component
@Scope("prototype")
public class CompaniesDBDAO implements CompaniesDAO{

	@Autowired
	private CompanyRepository companyrepo;
	
	@Override
	public void addCompany(Company company) {
		companyrepo.save(company);
	}
	
	@Override
	public void updateCompany(Company company) {
		if (companyrepo.existsById(company.getId())) {
			companyrepo.save(company);
		}
	}
	
	@Override
	public void deleteCompany(int companyID) {
		companyrepo.deleteById(companyID);
	}
	
	@Override
	public Company getOneCompany(int companyID) {
		Optional<Company> c = companyrepo.findById(companyID);
		if (c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}
	
	@Override
	public Company getOneCompanyByName(String companyName) {
		Company c = companyrepo.findCompanyByName(companyName);
		return c;
	}
	
	@Override
	public ArrayList<Company> getAllCompanies() {
		ArrayList<Company> companies = (ArrayList<Company>) companyrepo.findAll();
		return companies;
	}
	
	@Override
	public boolean isCompanyExistsByEmailAndPassword(String email, String password) {
		if (companyrepo.findByEmailAndPassword(email, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isCompanyExistsByNameOrEmail(String companyName, String companyEmail) {
		if(companyrepo.findCompanyByNameOrEmail(companyName, companyEmail) != null) {
			return true;
		} else {			
			return false;
		}
			
	}

	@Override
	public boolean isCompanyExistsByName(String companyName) {
		if(companyrepo.findCompanyByName(companyName) != null)
			return true;
		return false;
	}
	
	@Override
	public Company getOneCompanyByEmailAndPassword(String email, String password) {
		return companyrepo.findByEmailAndPassword(email, password);
	}
	
	@Override
	public boolean isCompanyExistsByID(int companyID) {
		if(companyrepo.findCompanyById(companyID) != null) {
			return true;
		} return false;
	}
}
