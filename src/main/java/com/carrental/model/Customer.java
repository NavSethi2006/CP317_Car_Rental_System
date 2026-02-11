package main.java.com.carrental.model;

import java.time.LocalDate;

public class Customer {
	private String customerID;
	private String customerName;
	private String email;
	private String phone;
	private String licenseNumber;
	private LocalDate licenseExpiry;
	private CustomerType type;
	
	public enum CustomerType {REGULAR, CORPERATE, VIP};
}
