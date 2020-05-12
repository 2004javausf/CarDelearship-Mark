package com.dealership.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.dealership.dao.DealershipDAOImpl;
import com.dealership.dealership.Employee;

public class EmployeeServices {
static DealershipDAOImpl ddi = new DealershipDAOImpl();
	
	
	public static Map<String,String> getHashEmployees() {
		Iterator<Employee> itr = null;
		try {
			itr = ddi.getEmployeeList().iterator();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> employee = new HashMap<String,String>();
		while(itr.hasNext()) {
			Employee currEmployeee = itr.next();
			employee.put(currEmployeee.getEmployeeName(), currEmployeee.getEmployeePassword());
		}
		return employee;
	}
}
