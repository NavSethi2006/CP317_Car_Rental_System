package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.carrental.model.Vehicle;
import main.java.com.carrental.model.Vehicle.VehicleStatus;
import main.java.com.carrental.model.Vehicle.VehicleType;

public class VehicleDAO {

	public VehicleDAO() {
		
	}
	
	public List<Vehicle> findAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String query = "SELECT * from vehicles";
		ResultSet set = MySQL.fetch(query);
		try {
			Vehicle vehicle = new Vehicle();
			while(set.next()) {
				vehicle.setId(set.getString("id"));
				vehicle.setLicensePlate(set.getString("license_plate"));
				vehicle.setMake(set.getString("make"));
				vehicle.setModel(set.getString("model"));
				vehicle.setYear(set.getInt("year"));
				vehicle.setDailyRate(set.getDouble("daily_rate"));
				String type = set.getString("type");
				
				
				switch(type) {
				case 
				}
				
				vehicle.setType(null); 
				vehicle.setStatus(null);				
			}
		} catch(SQLException e) {
			
		}
	}

	
	public boolean updateStatus(String vehicleID, VehicleStatus status) {
		
	}
	
	
	public List<Vehicle> findByType(VehicleType type) {
		
	}
	
}
