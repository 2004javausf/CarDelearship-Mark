package com.dealership.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnFactory {
	//singleton factory
		//need a private static instance
		
		private static ConnFactory cf = new ConnFactory();
		
		//private no args constructor
		private ConnFactory() {
			super();
		}
		
		//public static sync "getter"
		public static synchronized ConnFactory getInstance() {
			if(cf == null) {
				cf = new ConnFactory();
			}
			return cf;
		}
		
		//working methods
		//specifically get a connection to the db
		public Connection getConnection() {
			
			String url = "jdbc:oracle:thin:@cardealership.c1heybotdkrw.us-east-2.rds.amazonaws.com:1521:ORCL";
			String user = "mhardebeck";
			String password = "Fuckaws16";
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
}
