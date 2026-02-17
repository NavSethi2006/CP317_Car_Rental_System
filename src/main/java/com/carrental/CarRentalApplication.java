package main.java.com.carrental;

import main.java.com.carrental.dao.CustomerDAO;
import main.java.com.carrental.dao.MySQL;

public class CarRentalApplication {

	public static void main(String[] args) {
		MySQL mysql = new MySQL();
		mysql.Connect_to_MySQL_Database();
		
		// CUSTOMER DAO TEST
		CustomerDAO customerDAO = new CustomerDAO();
		
		
		
	}

}
