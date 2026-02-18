package main.java.com.carrental.model;

import java.time.LocalDate;

/**
 * Customer class model, mostly used to store information for use in another function
 */
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
	
	public Customer() {
		
	}
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

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public LocalDate getLicenseExpiry() {
		return licenseExpiry;
	}

	public void setLicenseExpiry(LocalDate licenseExpiry) {
		this.licenseExpiry = licenseExpiry;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
