package com.springcoupons.Spring.Coupons.Project.Job;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springcoupons.Spring.Coupons.Project.DAO.CouponsDAO;
import com.springcoupons.Spring.Coupons.Project.DBDAO.CouponsDBDAO;
import com.springcoupons.Spring.Coupons.Project.beans.Coupon;

@Component
public class CouponExpirationDailyJob implements Runnable {
	
	@Autowired
	private CouponsDBDAO couponsDBDAO;
	private boolean quit = true;

//										CONSTRUCTOR
	public CouponExpirationDailyJob() {
		super();
	}

// method that runs all the time and checking everyday the expiration of all the coupons in the system
//		and deleting the ones who are expired 
	@Override
	public void run() {
		while(quit) {
			Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
			ArrayList<Coupon> coupons = couponsDBDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if(currentDate.after(coupon.getEndDate())) {
					couponsDBDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
//	method that stops the previous method
	public void stop() {
		quit = false;
	}

}
