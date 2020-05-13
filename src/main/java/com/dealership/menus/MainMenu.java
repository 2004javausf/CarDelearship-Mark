package com.dealership.menus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dealership.dao.DealershipDAOImpl;
import com.dealership.dealership.Dealership;
import com.dealership.dealership.Lot;
import com.dealership.dealership.Offers;
import com.dealership.dealership.OwnedCars;
import com.dealership.dealership.User;
import com.dealership.util.CustomerServices;
import com.dealership.util.EmployeeServices;
import com.dealership.util.Logging;

public class MainMenu {
	static Map<String,String> users = new HashMap<String,String>();
	static Map<String,String> employee = new HashMap<String,String>();
	static List<User> d = new ArrayList<User>();
	static List<Lot> lot = new ArrayList<Lot>();
	static List<Offers> offers = new ArrayList<Offers>();
	static List<OwnedCars> oc = new ArrayList<OwnedCars>();
	boolean exit;
	Scanner sc = new Scanner(System.in);
	
	
	public void runMenu(){
	users = CustomerServices.getHashUsers();
	employee = EmployeeServices.getHashEmployees();
	d = Dealership.uList();
	lot = Dealership.lList();
	offers = Dealership.olist();
	oc = Dealership.dlist();
	while(!exit) {
		printMenu();
		int option = userOption();
		optionSelect(option);
		}
	}





	private void printMenu() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println("Welcome to Mark's Dealership!");
		System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Do you have an account with us? \nPress 1 for yes\nPress 2 for no");
		
	}

	private  int userOption() {
		int option = 2000;
		int i = 0;
		do {
		try {
			option = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			i++;
		}
		if(1>option||option>2) {
			i++;
			if(i>4) {
				System.out.println("You have entered too many invalid attempts, the application will now close");
				System.exit(0);
			}
			if(i>2) {
				System.out.println("Press 1 or 2");
			}
			System.out.println("Please enter one of the two options. 1 for yes or 2 for no");
			
		}
		
	}while ((1>option||option>2));
		return option;
	}
	
	private void optionSelect(int option) {
		switch (option) {
		case 1: 
			logIn();
			break;
		case 2:
			while(!exit) {
				printAccountCreate();
				int option1 = userOption1();
				optionSelect1(option1);
			}
			break;
		default:
			System.out.println("You should not have gotten here");
		}
	}





	private void logIn() {
		int i1 = 0;
		while (!exit) {
			System.out.print("Please enter your username: ");
			String username = sc.nextLine();
			i1++;
			if (users.containsKey(username)) {
				System.out.print("Please enter your password: ");
				String password = sc.nextLine();
				if ((users.get(username)).equals(password)) {
					System.out.println("You have entered the correct password.");
						for(int i3 = 0;i3<d.size();i3++) {
							if(username.equals(d.get(i3).getUsername())) {
								customerMenu(i3);
							}
						}
					}else {
						for(int i = 0;i<=3;i++) {
							if ((users.get(username)).equals(password)) {
								System.out.println("You have entered the correct password.");
								for(int i3 = 0;i3<d.size();i3++) {
									if(username.equals(d.get(i3).getUsername())) {
										customerMenu(i3);
									}

								}
							}
							System.out.println("The password you entered was incorrect. Try again.");
							password = sc.nextLine();
							if (i == 3) {
								System.out.println("The account is locked.");
								System.exit(0);
								break;
							}
						}
					}
				}else if (employee.containsKey(username)) {
					System.out.print("Please enter your password: ");
					String password = sc.nextLine();
					if((employee.get(username)).equals(password)) {
						System.out.println("You have entered the correct password.");
						employeeMenu();
					}else {
						System.out.println("You have entered the incorrect password, goodbye.");
						System.exit(0);
					}
				}else if(username.equals("exit")) {
					System.exit(0);
				}else {
					if (i1>3) {
						System.out.println("Too many invalid attempts.");
						System.exit(0);
					}
				}
			System.out.println("You have not entered a valid username, please try again, if you would like to exit type 'exit'");
		}
	}
	
	
	



	private void printAccountCreate() {
		System.out.println("Would you like to have an account with us?");
		System.out.println("For yes press 1");
		System.out.println("For no press 2");
		
	}
	
	private int userOption1() {
		int option = 2000;
		int i = 0;
		do {
		try {
			option = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			i++;
			
		}
		if(1>option||option>2) {
			i++;
			if(i>4) {
				System.out.println("You have entered too many invalid attempts, the application will now close");
				System.exit(0);
			}
			if(i>2) {
				System.out.println("Press 1 or 2");
			}
			System.out.println("Please enter one of the two options. 1 for yes or 2 for no");
		}
		
		}while ((1>option||option>2));
			return option;
	}
	
	private void optionSelect1(int option1) {
		switch(option1) {
		case 1: 
			signUp();
			break;
		case 2:
			System.out.println("Thank you, have a nice day");
			System.exit(0);
		default:
			System.out.println("Thank you, have a nice day");
			System.exit(0);
		}
		
	}

	
	
	
	private void signUp() {
		System.out.println("Please enter the username that you would like");
		int i = 0;
		boolean taken = false;
		String username = null;
		while(!taken) {
			System.out.print("Please enter your preferred username: ");
			username = sc.nextLine();
			//if the username is in our list make a statement saying it is a taken username
			if(users.containsKey(username)) {
				System.out.println("Sorry this username is taken");
			}else {
			System.out.println("Your chosen username is: "+username);
			taken = true;
			}
		}
			while(!exit) {
				i++;
				
			System.out.println("Please enter your password: ");
			String password = sc.nextLine();
			
			System.out.println("Please re-enter your password: ");
			String test = sc.nextLine();
			if(test.equals(password)) {
				DealershipDAOImpl ddi = new DealershipDAOImpl();
				System.out.println("Passwords match.");
				System.out.println("Please enter your first name");
				String fname = sc.nextLine();
				System.out.println("Please enter your last name");
				String lname = sc.nextLine();
				try {
					ddi.createUser(username, password, fname, lname);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Logging.LogIt("info", username+" " + password+ " "+ fname+" "+ lname);
				System.out.println("You have created your account, please wait a moment as we redirect you to the login page");
				runMenu();
			}
			else {
				if (i>3) {
					System.out.println("Your passwords still do not match, program ending.");
					System.exit(0);
				}
				System.out.println("Passwords do not match. Please try again.");
				
			}
			}
		
	}
	
/* Explanation to whoever is reading through my code, if someone ever does.
 * 	Current me cannot think of a way to connect to sequel one time and have it on a static table that I can view in every class
 *  I would want this because it take a good amount of time to load in the data from SQL.
 *  I figured since I couldn't think of an immediate solution to that problem, I would instead include all menu methods here
 *  Doing this, I can have the stuff I want from SQL always available to the menu, and only have to load it in once, saving a lot
 *  time. 
 *  I'll have an employee menu below
 */
	
// CUSTOMER MENU	
//-----------------------------------------------------------------------------------------------------------------------------	
	
	
	
	private void customerMenu(int i3) {
		while(!exit) {
			System.out.println("------------------------------------------------------------------");
			System.out.println("\nWelcome to your account "+ d.get(i3).getFname());
			Logging.LogIt("info", "Welcome to your account "+ d.get(i3).getFname());
			System.out.println("\n------------------------------------------------------------------");
			System.out.println("Would you like to \n1. View the cars on our lot\n2. View the cars that you own\n3. View your offers\n4. Exit");
			int option = customerOption();
			customerSelect(option,i3);
			}
	}





	private int customerOption() {
		int option = 2000;
		int i = 0;
		do {
		try {
			option = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			i++;
		}
		if(1>option||option>4) {
			i++;
			if(i>4) {
				System.out.println("You have entered too many invalid attempts, the application will now close");
				System.exit(0);
			}
			if(i>2) {
				System.out.println("Press 1, 2, or 3");
			}
			System.out.println("Please enter one of the three options, 1. For view lot 2. For view your cars 3. View your offers 4. Exit");
		}
		}while ((1>option||option>4));
		return option;
	
}


	private void customerSelect(int option, int i3) {
		switch(option) {
		case 1:
			System.out.println(lot);
			System.out.println("Would you like to make an offer on a car?\n1. Yes\n2. No");
			int option2 = 2000;
			int i = 0;
			do {
			try {
				option2 = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				i++;
			}
			if(1>option2||option2>2) {
				i++;
				if(i>4) {
					System.out.println("You have entered too many invalid attempts, the application will now close");
					System.exit(0);
				}
				if(i>2) {
					System.out.println("Press 1 or 2");
				}
				System.out.println("Please enter one of the two options. 1 for yes or 2 for no");
				
				}
			}while ((1>option2||option2>2));
			if(option2 == 1) {
				System.out.println("Please enter the ID of the car you would like to make an offer on");
				int carID = Integer.parseInt(sc.nextLine());
				for(int i2 = 0; i2<lot.size();i2++) {
					if (carID == lot.get(i2).getCarID()) {
					System.out.println("The asking price is: " + lot.get(i2).getPrice());
					System.out.println("Please enter the down payment you want to put on the car");
					float offer = Float.parseFloat(sc.nextLine());
					if (offer<0) {
						System.out.println("You have entered an invalid option your down payment must be greater than $0");
						System.out.println("Your down payment will be set to $100, if you would like to change this, go to update offers on the menu");
						offer = 100;
					}
					System.out.println("How much time in months would you like to pay this off?");
					int payTime = Integer.parseInt(sc.nextLine());
					if (payTime<1) {
						System.out.println("You have entered an invalid option, the payment time will be defaulted to 10 months");
						System.out.println("To change this, go to update offers on the menu");
						payTime = 10;
					}
					DealershipDAOImpl ddi = new DealershipDAOImpl();
					try {
						ddi.addOffer(carID, lot.get(i2).getPrice(), d.get(i3).getId(), offer, payTime);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Logging.LogIt("info", "offers to  a car " +carID+" "+lot.get(i2).getPrice()+ " "+ d.get(i3).getId() + " "+offer+" "+payTime);
					System.out.println("Your offer is pending");
					System.out.println("You will be returned to the customer menu");
					customerMenu(i3);
					}
				}
				
				System.out.println("You have enterend an invalid Car ID and will be returned to the customer menu");
				customerMenu(i3);
			}else {
				System.out.println("You will be returned to the customer menu");
				customerMenu(i3);
			}
			break;
		case 2:
			for (int k = 0; k < oc.size(); k++) {
				if(d.get(i3).getId() == oc.get(k).getUserID()) {
					System.out.println(oc.get(k));
					Logging.LogIt("info", "owned car "+oc.get(k));
					System.out.println("Press any key to return to the customer menu");
					String a = null;
					a = sc.nextLine();
					if(a!=null) {
						customerMenu(i3);
					}
				}
			}
			break;
		case 3:
			for (int j = 0; j < offers.size(); j++) {
				if (d.get(i3).getId() == offers.get(j).getUserID()){
					System.out.println(offers.get(j));
					}
			}
			System.out.println("Would you like to make an update to your offer?\n1. Yes\n2. No");
			int choice = Integer.parseInt(sc.nextLine());
			if (choice == 1) {
				System.out.println("Enter the car ID of the offer you would like to update");
				int carID = Integer.parseInt(sc.nextLine());
				System.out.println("Enter the down payment you would like to put down");
				float down = Float.parseFloat(sc.nextLine());
				System.out.println("Enter the time in months you would like to pay the car off in");
				int time = Integer.parseInt(sc.nextLine());
				DealershipDAOImpl ddi = new DealershipDAOImpl();
				try {
					ddi.updateOffer(carID, d.get(i3).getId(), down, time);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Logging.LogIt("info", "updating offer "+carID+" "+d.get(i3).getId()+" "+down+ " "+time);
				System.out.println("Your offer has been accepted, refresh to view the changes");
			}
			else {
				System.out.println("You will be returned to the customer menu");
				customerMenu(i3);
			}
			break;
		default:
			System.out.println("Thank you have a nice day");
			System.exit(0);
		}
		
	}
	
	
	
	
	
	
	
	
// EMPLOYEE MENU	
//________________________________________________________________________________________________________________________
	
	private void employeeMenu() {
		while(!exit) {
			System.out.println("------------------------------------------------------------------\n");
			System.out.println("Welcome to the Employee Menu");
			System.out.println("\n------------------------------------------------------------------");
			System.out.println("Would you like to \n1. View the cars on our lot\n2. View the offers on our cars\n3. View all customer payments\n4. Exit");
			int option = employeeOption();
			employeeSelect(option);
			}
		
		
	}


	private int employeeOption() {
		int option = 2000;
		int i = 0;
		do {
		try {
			option = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e) {
			i++;
		}
		if(1>option||option>4) {
			i++;
			if(i>4) {
				System.out.println("You have entered too many invalid attempts, the application will now close");
				System.exit(0);
			}
			if(i>2) {
				System.out.println("Press 1, 2, 3, or 4");
			}
			System.out.println("Please enter one of the three options, 1. For view lot, 2. For view offers. 3. View all payments. 4. Exit");
		}
		}while ((1>option||option>4));
		return option;
	}
	
	private void employeeSelect(int option) {
		DealershipDAOImpl ddi = new DealershipDAOImpl();
		switch(option) {
		case 1: 
			System.out.println(lot);
			System.out.println("Would you like to \n1. Add a car to the lot\n2. Remove a car from the lot\n3. Return to the employee menu");
			int option2 = 2000;
			int i = 0;
			do {
			try {
				option2 = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				i++;
			}
			if(1>option2||option2>3) {
				i++;
				if(i>4) {
					System.out.println("You have entered too many invalid attempts, the application will now close");
					System.exit(0);
				}
				if(i>2) {
					System.out.println("Press 1 or 2");
				}
				System.out.println("Please enter one of the three options. 1 for add car, 2 for remove car, 3 return to menu");
				}
			}while ((1>option2||option2>3));
			if (option2 == 1) {
				System.out.println("Please enter the make of the car you would like to add");
				String make = sc.nextLine();
				System.out.println("Please enter the model of the car you would like to add");
				String model = sc.nextLine();
				System.out.println("Please enter the color of the car you would like to add");
				String color = sc.nextLine();
				System.out.println("Please enter the mileage on the car you would like to add");
				float mileage = Float.parseFloat(sc.nextLine());
				if (mileage<0) {
					System.out.println("You have to enter a mileage greater than 0, you will be returned to the employee select menu");
					employeeSelect(option);
				}
				System.out.println("Please enter the price of the car you would like to add");
				float price = Float.parseFloat(sc.nextLine());
				if (price<0) {
					System.out.println("You have to enter a price greater than 0, you will be returned to the employee select menu");
					employeeSelect(option);
				}
				System.out.println("Please enter the year of the car you would like to add");
				int year = Integer.parseInt(sc.nextLine());
				if (year<1990) {
					System.out.println("Cars didn't exist before 1990, please try again at the employee select menu");
					employeeSelect(option);
				}
				try {
					ddi.addCar(make, model, color, mileage, price, year);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Logging.LogIt("info", "adding these things to the lot "+ make+" "+ model+" "+ color+" "+ mileage+" "+ price+" "+ year);
				System.out.println("The car has been added to the lot");
			}else if (option2 == 2) {
				System.out.println("Please enter the ID of the car you would like to remove");
				int carID = Integer.parseInt(sc.nextLine());
				try {
					ddi.removeCar(carID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Logging.LogIt("info", "removed car: "+carID);
				System.out.println("The car has been removed, please reload to see the changes");
			}else {
				employeeMenu();
			}
			break;
		case 2: 
			System.out.println(offers);
			System.out.println("Would you like to accept an offer on a car?\n1. Yes\n2. No");
			int option3 = Integer.parseInt(sc.nextLine());
			if(option3 == 1) {
				System.out.println("Please enter the car ID you would like to accept");
				int carID = Integer.parseInt(sc.nextLine());
				System.out.println("Please enter the user ID you would like to accept");
				int userID =  Integer.parseInt(sc.nextLine());
				try {
					ddi.acceptOffer(carID, userID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Logging.LogIt("info", "this car and user combo was accepted: "+carID+ " " + userID);
				System.out.println("The offer has been accepted, please refresh the page to see the changes");
			}else {
				System.out.println("You will be returned to the menu");
				employeeMenu();
			}
			break;
		case 3:
			System.out.println(oc);
			System.out.println("Press any key to return to the employee menu");
			String a = sc.nextLine();
			if (a != null){
				employeeMenu();
			}
			break;
		default:
				System.out.println("Thank you have a nice day");
				System.exit(0);
		}
		
	}
}

