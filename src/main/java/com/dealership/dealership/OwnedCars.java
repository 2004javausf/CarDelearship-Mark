package com.dealership.dealership;

public class OwnedCars {

	private int userID;
	private String make;
	private String model;
	private String color;
	private float mileage;
	private int paymentsLeft;
	private float payMin;
	
	public OwnedCars() {
		super();
	}
	public OwnedCars(int userID, String make, String model, String color, float mileage, int paymentsLeft, float payMin) {
		super();
		this.userID = userID;
		this.make = make;
		this.model = model;
		this.color = color;
		this.mileage = mileage;
		this.paymentsLeft = paymentsLeft;
		this.payMin = payMin;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public int getPaymentsLeft() {
		return paymentsLeft;
	}

	public void setPaymentsLeft(int paymentsLeft) {
		this.paymentsLeft = paymentsLeft;
	}

	public float getPayMin() {
		return payMin;
	}

	public void setPayMin(float payMin) {
		this.payMin = payMin;
	}
	
	@Override
	public String toString() {
		return "OwnedCars [User ID: " + userID + ", Minimum Monthly Payment: $" + payMin + ", Payments Left: " + 
				paymentsLeft +  ", Make: " + make + ", Model: " + model + ", Color: " + color + ", Mileage: " + mileage + "]\n";
	}

	
	
}
