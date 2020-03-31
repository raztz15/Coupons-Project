package com.springcoupons.Spring.Coupons.Project.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;

//											COUPON CLASS
@Entity
@Table(name = "coupons")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	@ManyToOne
	@JoinColumn()
	private Company company;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;
	@ManyToMany(mappedBy = "coupons" , fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private java.util.List<Customer> customers = new ArrayList<Customer>();
	
//										METHODS
	
//										CTORS
	
//	an empty constructor for spring to initialize the object
	public Coupon() {
		super();
	}

// constructor
	public Coupon(Company company, Category category, String title,
			String description, Date startDate, Date endDate, int amount, double price, String image) {
		super();
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

//										GETTERS/ SETTERS
	
/**
 * 
 * @return the coupon ID
 */
	public int getId() {
		return id;
	}
	
/**
 * 	
 * @return the coupons category
 */
	public Category getCategory() {
		return category;
	}

/**
 * set the coupon category
 * @param category
 */
	public void setCategory(Category category) {
		this.category = category;
	}

/**
 * 
 * @return the coupon title
 */
	public String getTitle() {
		return title;
	}

/**
 * set the coupon title
 * @param title
 */
	public void setTitle(String title) {
		this.title = title;
	}

/**
 * 
 * @return the coupon description
 */
	public String getDescription() {
		return description;
	}

/**
 * set the coupon description
 * @param description
 */
	public void setDescription(String description) {
		this.description = description;
	}

/**
 * 
 * @return the coupon start date
 */
	public Date getStartDate() {
		return startDate;
	}

/**
 * set the coupon start date
 * @param startDate
 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

/**
 * 
 * @return the coupon end date
 */
	public Date getEndDate() {
		return endDate;
	}

/**
 * set the coupon end date
 * @param endDate
 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

/**
 * 
 * @return the coupon amount
 */
	public int getAmount() {
		return amount;
	}

/**
 * set the coupon amount
 * @param amount
 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

/**
 * 
 *@return the coupon price
 */
	public double getPrice() {
		return price;
	}

/**
 * set the coupon price
 * @param price
 */
	public void setPrice(double price) {
		this.price = price;
	}

/**
 * 
 * @return the coupon image
 */
	public String getImage() {
		return image;
	}

/**
 * set the coupon image
 * @param image
 */
	public void setImage(String image) {
		this.image = image;
	}

/**
 * 
 * @return the list of customers that purchased the coupon
 */
	public List<Customer> getCustomers() {
		return customers;
	}

/**
 * set the list of customers that purchased the coupon
 * @param customers
 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

/**
 * 
 * @return the company that owns the coupon
 */
	public Company getCompany() {
		return company;
	}
	
/**
 * set the company that owns the coupon
 * @param company
 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
/**
 * adding the customer object to the list when customer purchases a coupon
 * @param c
 */
	public void addPurchase(Customer c) {
		this.customers.add(c);		
	}

	@Override
	public String toString() {
		return "id=" + id + ", companyID=" + company.getId() + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coupon) {
			return this.id == ((Coupon)obj).getId();
		} else 
			return false;
	}
	
}


