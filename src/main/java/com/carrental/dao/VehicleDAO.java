package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.carrental.model.Vehicle;
import main.java.com.carrental.model.Vehicle.VehicleStatus;
import main.java.com.carrental.model.Vehicle.VehicleType;

public class VehicleDAO {

	public VehicleDAO() {
		
	}
	
	public List<Vehicle> findAvailableVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String query = "SELECT * from vehicles";
		ResultSet set = MySQL.fetch(query);
		try {
			Vehicle vehicle = new Vehicle();
			while(set.next()) {
				if(set.getString("status") == "AVAILABLE") {
					vehicle.setId(set.getString("id"));
					vehicle.setLicensePlate(set.getString("license_plate"));
					vehicle.setMake(set.getString("make"));
					vehicle.setModel(set.getString("model"));
					vehicle.setYear(set.getInt("year"));
					vehicle.setDailyRate(set.getDouble("daily_rate"));
					String type = set.getString("type");
					vehicle.getTypeFromString(type);
					vehicles.add(vehicle);
				}
			}
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicles;
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
				vehicle.getTypeFromString(type);
				vehicles.add(vehicle);
			}
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicles;
	}
	
	public List<Vehicle> findByType(VehicleType type) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String query = "SELECT * from vehicles where type='"+type.toString()+"'";
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
				vehicle.setType(type);
				vehicles.add(vehicle);
			}
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicles;
	}
	
	public static Vehicle findByID(String ID) {
		Vehicle vehicle = new Vehicle();
		String query = "SELECT * FROM vehicles where id='"+ID+"'";
		ResultSet set = MySQL.fetch(query);
		
		try {
			set.next();
			vehicle.setId(ID);
			vehicle.setLicensePlate(set.getString("license_plate"));
			vehicle.setMake(set.getString("make"));
			vehicle.setModel(set.getString("model"));
			vehicle.setYear(set.getInt("year"));
			vehicle.setDailyRate(set.getDouble("daily_rate"));
			String type = set.getString("type");
			vehicle.getTypeFromString(type);
			
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicle;
	}

	public boolean updateStatus(String id, VehicleStatus maintenance) {
		String query = "UPDATE vehicles SET status='"+maintenance.toString()+"' where id='"+id+"'";
		return MySQL.update(query);
	}
	
	public static void insertRecord(Vehicle vec) {
		String query = "INSERT INTO vehicles (license_plate, make, model, year, daily_rate, type, status) "
	             + "VALUES ('" + vec.getLicensePlate() + "','" + vec.getMake() + "','" + vec.getModel() + "',"
	             + vec.getYear() + "," + vec.getDailyRate() + ",'"
	             + vec.getType().toString() + "','" + vec.getStatus().toString() + "')";
		
		MySQL.insert(query);
	}
	
}
