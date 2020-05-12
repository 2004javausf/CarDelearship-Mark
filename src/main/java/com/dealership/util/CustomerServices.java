package com.dealership.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dealership.dao.DealershipDAOImpl;
import com.dealership.dealership.User;



public class CustomerServices {
	static DealershipDAOImpl ddi = new DealershipDAOImpl();
	
	
	public static Map<String,String> getHashUsers() {
		Iterator<User> itr = null;
		try {
			itr = ddi.getUsersList().iterator();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> users = new HashMap<String,String>();
		while(itr.hasNext()) {
			User currUser = itr.next();
			users.put(currUser.getUsername(), currUser.getPassword());
		}
		return users;
	}
	
}
