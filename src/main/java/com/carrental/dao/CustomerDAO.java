package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.carrental.model.Customer;

public class CustomerDAO {
	
	public CustomerDAO() {
		
	}

	public Customer findByUsername(String username) {
		String query = "SELECT * FROM customers WHERE name = '"+username+"'";
		ResultSet result = MySQL.fetch(query);
		Customer customer = null;
		try {
			String id = result.getString(0);
			// skipped 1 since index 1 is the name
			String email = result.getString(2);
			String password = result.getString(3);
			customer = new Customer(id, username, email, password);

		} catch(SQLException e) {
			System.err.print("This email doesent exist in our database, would you like to register?");
		}
		
		
		return customer;
	}
	
	public static Customer findByEmail(String email) {
		String query = "SELECT * FROM customers WHERE email = '"+email+"'";
		ResultSet result = MySQL.fetch(query);
		Customer customer = null;
		try {
			String id = result.getString(0);
			String name = result.getString(1);
			// skipped 2 since index 2 is email
			String password = result.getString(3);
			customer = new Customer(id, name, email, password);

		} catch(SQLException e) {
			System.err.print("This email doesent exist in our database, would you like to register?");
		}
		return customer;
	}
	

	
	public static void insertRecord(String username, String email, String password) {
		String query = "INSERT INTO customers(name, email) VALUES('"+username+"','"+email+"'"+password+"');";
		boolean insertion = MySQL.insert(query);
		
		if(insertion) {
			// return frontend success code
		} else {
			System.err.print("Error with registeration, please try again later");
		}
	}
	
	
}
