package com.dealership.dealership;

public class Lot {

	private int carID;
	private String make;
	private String model;
	private String color;
	private float mileage;
	private float price;
	private int year;
	
	public Lot() {
		super();
	}
	public Lot(int carID, String make, String model, String color, float mileage, float price, int year) {
		super();
		this.carID = carID;
		this.make = make;
		this.model = model;
		this.color = color;
		this.mileage = mileage;
		this.price = price;
		this.year = year;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Car ID: " + carID + ", Make: " + make + ", Model: " + model + " Year: " + year + ", Color: " + color + ", Mileage: "
				+ mileage + ", Price: $" + price + "]\n";
	}
}
