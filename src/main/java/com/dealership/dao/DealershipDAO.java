package com.dealership.dao;

import java.sql.SQLException;
import java.util.List;

import com.dealership.dealership.Employee;
import com.dealership.dealership.Lot;
import com.dealership.dealership.Offers;
import com.dealership.dealership.OwnedCars;
import com.dealership.dealership.User;

public interface DealershipDAO {
	
	//Create user
		public void createUser(String username, String password, String fname, String lname) throws SQLException;
	//Read all user
		public List<User> getUsersList() throws SQLException;
		
		
		
	//Create car on lot
		public void addCar(String make, String model, String color, float mileage, float price, int year) throws SQLException;
	//Read all cars on lot
		public List<Lot> getlotList() throws SQLException;
	//Remove car from lot
		public void removeCar(int carID) throws SQLException;
		
		
		
	//Create offer 
		public void addOffer(int carID, float price, int userID, float offer, int payTime) throws SQLException;
	//read offers
		public List<Offers> getOfferList() throws SQLException;
	//Update offers
		public void updateOffer(int carID, int userID, float offer, int payTime) throws SQLException;
		
		
	//read all employees
		public List<Employee> getEmployeeList() throws SQLException;
		
		
		
	//Create Owned car/accept offers
		public void acceptOffer(int carID, int userID) throws SQLException;
	//read owned cars
		public List<OwnedCars> getOwnersList() throws SQLException;

}
