package main.java.com.carrental.model;

import java.time.LocalDateTime;

public class Rental {
	private String rentalID;
	private Customer customer;
	private Vehicle vehicle;
	private LocalDateTime pickupDate;
	private LocalDateTime plannedReturnDate;
	private LocalDateTime actualReturnDate;
	private double totalCost;
	private RentalStatus status;
	private Payment payment;
	
	public enum RentalStatus {RESERVED, ACTIVE, COMPLETED, CANCELLED};
}
