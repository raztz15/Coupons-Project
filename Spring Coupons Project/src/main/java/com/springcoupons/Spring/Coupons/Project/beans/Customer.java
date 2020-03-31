package com.springcoupons.Spring.Coupons.Project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//											CUSTOMER CLASS
@Entity
@Table(name = "customers")

public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
//											CTORS
// an empty constructor for spring to initialize the current object
	
	public Customer() {
		super();
	}
	
//	constructor
	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
//											GETTERS/ SETTERS
	
/**
 * 
 * @return customer's ID
 */
	public int getId() {
		return id;
	}
	
/**
 * 
 * @return customer's first name
 */
	public String getFirstName() {
		return firstName;
	}
	
/**
 * set the customer's first name
 * @param firstName
 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
/**
 * 
 * @return customer's last name
 */
	public String getLastName() {
		return lastName;
	}
	
/**
 * set the customer's last name
 * @param lastName
 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
/**
 * 
 * @return customer's email
 */
	public String getEmail() {
		return email;
	}
	
/**
 * set the customer's email
 * @param email
 */
	public void setEmail(String email) {
		this.email = email;
	}
	
/**
 * 
 * @return customer's password
 */
	public String getPassword() {
		return password;
	}
	
/**
 * set customer's password
 * @param password
 */
	public void setPassword(String password) {
		this.password = password;
	}
	
/**
 * 
 * @return list of coupons that the customer purchased
 */
	public List<Coupon> getCoupons() {
		return  coupons;
	}
	
/**
 * set the list of coupons that the customer purchased
 * @param coupons
 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	
/**
 * adding coupon object to the coupon list if customer purchased this coupon
 * @param coupon
 */
	public void addCouponPurchase(Coupon coupon) {
		coupons.add(coupon);
	}

/**
 * removing coupon purchase from the DB
 * @param couponID
 */
	public void deleteCouponPurchase(int couponID) {
		for (int i = 0; i < coupons.size(); i++) {
			if(coupons.get(i).getId() == couponID) {
				coupons.remove(i);
				break;
			}
		}
	}
	
/**
 * removing all customer purchases in case of deleting customer from the system
 */
	public void removeAllPurchases() {
		this.coupons.removeAll(coupons);
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons;
	}
	
	
}
