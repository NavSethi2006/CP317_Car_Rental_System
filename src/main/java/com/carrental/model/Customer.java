package main.java.com.carrental.model;

import java.time.LocalDate;

public class Customer {
	private String customerID;
	private String customerName;
	private String email;
	private String password; // TEMPORARY STORAGE FOR PASSWORD WILL BE REMOVED RIGHT AFTER INSERTED INTO DATABASE
	private String phone;
	private String licenseNumber;
	private LocalDate licenseExpiry;
	private CustomerType type;
	
	public enum CustomerType {REGULAR, CORPERATE, VIP};
	
	public Customer(String customerID, String customerName, String email, String password) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
	}
	
	public Customer(String customerID, String customerName, String email, String phone, String licenseNumber, LocalDate licenseExpiry) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.email = email;
		this.phone = phone;
		this.licenseNumber = licenseNumber;
		this.licenseExpiry = licenseExpiry;
	}
	
	public String getPassword() {
		return password;
	}
}
