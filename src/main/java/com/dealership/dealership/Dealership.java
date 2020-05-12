package com.dealership.dealership;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dealership.dao.DealershipDAOImpl;

public class Dealership {
	static DealershipDAOImpl ddi = new DealershipDAOImpl();
	public static List<User> uList() {
//		DealershipDAOImpl ddi = new DealershipDAOImpl();
		List<User> d = new ArrayList<User>();
		try {
			d.addAll(ddi.getUsersList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public static List<Lot> lList(){
		List<Lot> lot = new ArrayList<Lot>();
		try {
			lot.addAll(ddi.getlotList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lot;
	}
	
	public static List<Offers> olist(){
		List<Offers> offers = new ArrayList<Offers>();
		try {
			offers.addAll(ddi.getOfferList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offers;
	}
	
	public static List<OwnedCars> dlist(){
		List<OwnedCars> offers = new ArrayList<OwnedCars>();
		try {
			offers.addAll(ddi.getOwnersList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offers;
	}
}
