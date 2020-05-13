package com.dealership.dealership;

public class Offers {

	private int carID;
	private double price;
	private int userID;
	private double offer;
	private int payTime;
	
	
	public Offers() {
		super();
	}
	
	public Offers(int carID, double price, int userID, double offer, int payTime) {
		super();
		this.carID = carID;
		this.price = price;
		this.userID = userID;
		this.offer = offer;
		this.payTime = payTime;
	}

	
	public int getPayTime() {
		return payTime;
	}

	public void setPayTime(int payTime) {
		this.payTime = payTime;
	}


	public int getCarID() {
		return carID;
	}


	public void setCarID(int carID) {
		this.carID = carID;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public double getOffer() {
		return offer;
	}


	public void setOffer(double offer) {
		this.offer = offer;
	}

	@Override
	public String toString() {
		return "Offers [Car ID: " + carID + ", User ID: " + userID + ", Price: $" + price + ", Down Payment: $" + offer + ", Time to Pay: "
				+ payTime + "Offer Status: Pending]\n";
	}


	
	
}
