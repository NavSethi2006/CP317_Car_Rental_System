package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import main.java.com.carrental.model.Rental;

public class RentalDAO {

	public static List<Rental> findByVehicleID(String vehicleID) {
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
	
	public static List<Rental> findByCustomerID(String customerID) {
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
	
	private Rental mapRowToRental(ResultSet rs) throws SQLException {
        Rental rental = new Rental();
        rental.setRentalID(rs.getString("id"));
        rental.setVehicle(VehicleDAO.findByID(rs.getString("vehicle_id")));
        rental.setCustomer(CustomerDAO.findByID("customer_id"));
        rental.setPickupDate(rs.getObject("start_date", LocalDateTime.class));
        rental.setPlannedReturnDate(rs.getObject("end_date", LocalDateTime.class));
        rental.setStatus(Rental.RentalStatus.valueOf(rs.getString("status")));
        // set other fields if needed
        return rental;
    }

	public List<Rental> findOverlappingRentals(String id, LocalDateTime start, LocalDateTime end) {
		List<Rental> overlapping = new ArrayList<>();
		String query = "SELECT * FROM rentals " +
		                "WHERE vehicle_id = '"+id+"'" +
		                "AND start_date <= '"+start+"' " +
		                "AND end_date >= '"+end+"'";
		
		ResultSet set = MySQL.fetch(query);
		try {
			while(set.next()) {
				Rental rental = mapRowToRental(set);
				overlapping.add(rental);
			}	
		} catch(SQLException e) {
			
		}
		
		return overlapping;
	}
		
	public static void insertRecord(Rental rental) {
		String query = "INSERT INTO rentals(start_date, end_date, total_cost) "
				+ "VALUES('"+rental.getPickupDate()+"','"+rental.getPlannedReturnDate()+"','"
				+rental.getTotalCost()+"');";
		MySQL.insert(query);
	}
	
}
