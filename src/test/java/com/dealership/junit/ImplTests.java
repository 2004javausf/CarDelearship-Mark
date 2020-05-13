package com.dealership.junit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dealership.dao.DealershipDAOImpl;
import com.dealership.dealership.Dealership;
import com.dealership.dealership.Lot;
import com.dealership.dealership.Offers;
import com.dealership.dealership.OwnedCars;
import com.dealership.dealership.User;

import junit.framework.Assert;

class ImplTests {
	DealershipDAOImpl ddi = new DealershipDAOImpl();
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Checking for user read and write")
	void userTest() {
		String t = "t";
		String e = "e";
		String s = "s";
//		try {
//			ddi.createUser(t, e, s, t);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		List<User> d = new ArrayList<User>();
		d = Dealership.uList();
		Assert.assertEquals(t, d.get(7).getUsername());
		Assert.assertEquals(e, d.get(7).getPassword());
		Assert.assertEquals(s, d.get(7).getFname());
		Assert.assertEquals(t, d.get(7).getLname());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Checking for reading from lot table")
	void lotTest() {
		int carID = 1;
		String make = "Tesla";
		String model = "Model 3";
		String color = "Red";
		float mileage = 0;
		float price = 39900;
		int year = 2020;
		List<Lot> lot = new ArrayList<Lot>();
		lot = Dealership.lList();
		Assert.assertEquals(carID, lot.get(0).getCarID());
		Assert.assertEquals(make, lot.get(0).getMake());
		Assert.assertEquals(model, lot.get(0).getModel());
		Assert.assertEquals(color, lot.get(0).getColor());
		Assert.assertEquals(mileage, lot.get(0).getMileage());
		Assert.assertEquals(price, lot.get(0).getPrice());
		Assert.assertEquals(year, lot.get(0).getYear());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("checking reading from offers table")
	void offersTest() {
		int carID = 1;
		int userID = 4;
		List<Offers> offers = new ArrayList<Offers>();
		offers = Dealership.olist();
		Assert.assertEquals(carID, offers.get(1).getCarID());
		Assert.assertEquals(userID, offers.get(1).getUserID());
	}
	
}
