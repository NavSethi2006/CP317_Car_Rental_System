package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import main.java.com.carrental.model.Rental;

public class RentalDAO {

	public List<Rental> findByVehicleID(String vehicleID) {
		List<Rental> rentals = new ArrayList<Rental>();
		String query = "SELECT * FROM rentals WHERE vehicle_id = '"+vehicleID+"'";
		ResultSet result = MySQL.fetch(query);
		try {
			while(result.next()) {
				String id = result.getString("id");
			    String vec_id = vehicleID;
			    String customer_id = result.getString("customer_id");
			    LocalDateTime start_date = result.getTimestamp("start_date").toLocalDateTime();
			    LocalDateTime end_date = result.getTimestamp("end_date").toLocalDateTime();
			    double total_cost = result.getDouble("total_cost");
			    
			    Rental rental = new Rental();
			    rental.setRentalID(id);
			    rental.setVehicle(VehicleDAO.findByID(vec_id));
			    rental.setCustomer(CustomerDAO.findByID(customer_id));
			    rental.setPickupDate(start_date);
			    rental.setPlannedReturnDate(end_date);
			    rental.setTotalCost(total_cost);
			    rentals.add(rental);
			}
		} catch(SQLException e) {
			System.err.print("Rental doesent exist");
		}	
		return rentals;
	}
	
	public List<Rental> findByCustomerID(String customerID) {
		List<Rental> rentals = new ArrayList<Rental>();
		String query = "SELECT * FROM rentals WHERE customer_id = '"+customerID+"'";
		ResultSet result = MySQL.fetch(query);
		
		try {
			while(result.next()) {
				String id = result.getString("id");
			    String vec_id = result.getString("vehicle_id");
			    String customer_id = customerID;
			    LocalDateTime start_date = result.getTimestamp("start_date").toLocalDateTime();
			    LocalDateTime end_date = result.getTimestamp("end_date").toLocalDateTime();
			    double total_cost = result.getDouble("total_cost");
			    
			    Rental rental = new Rental();
			    rental.setRentalID(id);
			    rental.setVehicle(VehicleDAO.findByID(vec_id));
			    rental.setCustomer(CustomerDAO.findByID(customer_id));
			    rental.setPickupDate(start_date);
			    rental.setPlannedReturnDate(end_date);
			    rental.setTotalCost(total_cost);
			    rentals.add(rental);
			}	    
		  
		} catch(SQLException e) {
			System.err.print("Rental doesent exist");
		}
			
		return rentals;
	
	}
	
	
}
