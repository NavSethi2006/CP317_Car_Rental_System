package main.java.com.carrental.service;

import main.java.com.carrental.dao.CustomerDAO;
import main.java.com.carrental.model.Customer;
import main.java.com.carrental.util.SecurePasswordHasher;

public class CustomerService {
	
	public CustomerService(CustomerDAO customerDAO) {

	}
	
	public Customer Login(String email,String rawPassword) {
		
		Customer customer = CustomerDAO.findByEmail(email);
		if(customer == null) {
			return null;
		}
		boolean login = SecurePasswordHasher.verifyPassword(rawPassword, customer.getPassword());
		if(!login) { 
			// this case the gui would throw an error saying incorrect password
			System.err.print("Password is incorrect");
		}
		return customer;
	}
	
	public void Register(String email, String rawPassword, String username) {
		String hashedpassword = SecurePasswordHasher.hashPassword(rawPassword);
		CustomerDAO.insertRecord(username, email, hashedpassword);
	}
	
}
