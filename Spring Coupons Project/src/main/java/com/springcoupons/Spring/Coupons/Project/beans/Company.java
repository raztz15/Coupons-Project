package com.springcoupons.Spring.Coupons.Project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//                                         COMPANY CLASS
@Entity
@Table(name = "companies")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
//											METHODS
	
//											CTORS
	
// an empty constructor for spring to initialize the current object
	public Company() {
		super();
	}

// constructor
	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

//											GETTERS/ SETTERS

/**
 * 
 * @return the company ID
 */
	public int getId() {
		return id;
	}

/**
 * 
 * @return the name of the company
 */
	public String getName() {
		return name;
	}

/**
 * set the name of the company
 * @param name
 */
	public void setName(String name) {
		this.name = name;
	}

/**
 * 
 * @return the email of the company
 */
	public String getEmail() {
		return email;
	}

/**
 * set the email of the company
 * @param email
 */
	public void setEmail(String email) {
		this.email = email;
	}

/**
 * 
 * @return the password of the company
 */
	public String getPassword() {
		return password;
	}

/**
 * set the password of the company
 * @param password
 */
	public void setPassword(String password) {
		this.password = password;
	}
	
/**
 * add coupon to the company coupons list	
 * @param coupon
 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

/**
 * 
 * @return a list of coupons that belongs to the company
 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

/**
 * set the list of the company coupons
 * @param coupons
 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons;
	}
	

}
