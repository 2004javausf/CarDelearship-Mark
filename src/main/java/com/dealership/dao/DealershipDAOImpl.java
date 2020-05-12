package com.dealership.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dealership.dealership.Employee;
import com.dealership.dealership.Lot;
import com.dealership.dealership.Offers;
import com.dealership.dealership.OwnedCars;
import com.dealership.dealership.User;
import com.dealership.util.ConnFactory;

public class DealershipDAOImpl implements DealershipDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public void createUser(String username, String password, String fname, String lname) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTUSERS(?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);
		call.setString(2, password);
		call.setString(3, fname);
		call.setString(4, lname);
		call.execute();
		
	}
	

	public List<User> getUsersList() throws SQLException {
		List<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * FROM USERS");
		User u = null;
		while(rs.next()) {
			u = new User(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
			userList.add(u);
		}
		return userList;
	}


	public void addCar(String make, String model, String color, float mileage, float price, int year) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call ADDCAR(?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, make);
		call.setString(2, model);
		call.setString(3, color);
		call.setFloat(4, mileage);
		call.setFloat(5, price);
		call.setInt(6, year);
		call.execute();
		
	}


	public List<Lot> getlotList() throws SQLException {
		List<Lot> lotList = new ArrayList<Lot>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * FROM LOT");
		Lot l = null;
		while(rs.next()) {
			l = new Lot(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getFloat(5),rs.getFloat(6),rs.getInt(7));
			lotList.add(l);
		}
		return lotList;
	}
	
	public void removeCar(int carID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call REMOVECAR(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.execute();
	}


	public void addOffer(int carID, float price, int userID, float offer, int payTime) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call ADDOFFER(?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.setFloat(2, price);
		call.setInt(3, userID);
		call.setFloat(4, offer);
		call.setInt(5, payTime);
		call.execute();
	}


	public List<Offers> getOfferList() throws SQLException {
		List<Offers> offersList = new ArrayList<Offers>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * FROM OFFERS");
		Offers o = null;
		while(rs.next()) {
			o = new Offers(rs.getInt(1),rs.getFloat(2),rs.getInt(3), rs.getFloat(4), rs.getInt(5));
			offersList.add(o);
		}
		return offersList;
	}
	
	public void updateOffer(int carID, int userID, float offer, int payTime) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call UPDATEOFFER(?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.setInt(2, userID);
		call.setFloat(3, offer);
		call.setInt(4, payTime);
		call.execute();
		
	}


	public List<Employee> getEmployeeList() throws SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * FROM EMPLOYEE");
		Employee e = null;
		while(rs.next()) {
			e = new Employee(rs.getString(1),rs.getString(2));
			employeeList.add(e);
		}
		return employeeList;
	}


	public void acceptOffer(int carID, int userID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call UPDATECARINFO(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.setInt(2, userID);
		call.execute();
		
	}


	public List<OwnedCars> getOwnersList() throws SQLException {
		List<OwnedCars> employeeList = new ArrayList<OwnedCars>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * FROM OWNED_CARS");
		OwnedCars e = null;
		while(rs.next()) {
			e = new OwnedCars(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getFloat(6), rs.getInt(6), rs.getFloat(7));
			employeeList.add(e);
		}
		return employeeList;
	}


	


	


	


	
	
}
