package main.java.com.carrental.dao;

import java.time.LocalDate;
import java.util.List;

import main.java.com.carrental.model.Vehicle;
import main.java.com.carrental.model.Vehicle.VehicleStatus;
import main.java.com.carrental.model.Vehicle.VehicleType;

public class VehicleDAO {

	public VehicleDAO() {
		
	}
	
	public List<Vehicle> findAvailableVehicles(VehicleType type, LocalDate startDate, LocalDate EndDate) {
		String query = "SELECT * FROM vehicles WHERE status='AVAILABLE'";
	}
	
	public Vehicle Save(Vehicle vehicle) {
		
	}
	
	public boolean updateStatus(String vehicleID, VehicleStatus status) {
		
	}
	
	public void delete(Vehicle vehicle) {
		
	}
	
	public List<Vehicle> findByType(VehicleType type) {
		
	}
	
}
