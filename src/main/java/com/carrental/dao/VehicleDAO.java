package main.java.com.carrental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.carrental.model.Vehicle;
import main.java.com.carrental.model.Vehicle.VehicleStatus;
import main.java.com.carrental.model.Vehicle.VehicleType;

/**
 * Get all Vehicle info from this class, this
 * should be the only class that sends and
 * recieves data from the database
 */
public class VehicleDAO {

	public VehicleDAO() {
		
	}
	
	/**
	 * Find every vehicle available for rent
	 * @return a list of vehicles that are available
	 */
	public List<Vehicle> findAvailableVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String query = "SELECT * from vehicles where status='AVAILABLE'";
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
					String type = set.getString("vehicle_type");
					vehicle.getTypeFromString(type);
					vehicles.add(vehicle);
			}
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicles;
	}
	
	/**
	 * Find every vehicle that exists in the database
	 * @return List of every vehicle in the database
	 */
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
				String type = set.getString("vehicle_type");
				vehicle.getTypeFromString(type);
				vehicles.add(vehicle);
			}
		} catch(SQLException e) {
			System.err.print("Could not find SQL column");
		}
		return vehicles;
	}
	
	/**
	 * Find every vehicle with given type
	 * @param VehicleType type of vehicle to display
	 * @return a list of vehicles that align with specified type
	 */
	public List<Vehicle> findByType(VehicleType type) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		String query = "SELECT * from vehicles where vehicle_type='"+type.toString()+"'";
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
	
	/**
	 * Find a vehicle with given identification
	 * @param ID
	 * @return Vehicle vehicle found null if vehicle wasnt found
	 */
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
			String type = set.getString("vehicle_type");
			vehicle.getTypeFromString(type);
			
		} catch(SQLException e) {
			System.out.println("Could not find SQL column");
		}
		return vehicle;
	}

	/**
	 * Update the static of the vehicle, for example, "AVAILABLE" "RENTED" "MAINTENANCE"
	 * @param Vehicle identification
	 * @param Status the status you want the car to change to
	 * @return boolean true if MySQL query was successful
	 */
	public boolean updateStatus(String id, VehicleStatus status) {
		String query = "UPDATE vehicles SET status='"+status.toString()+"' where id='"+id+"'";
		return MySQL.update(query);
	}
	
	/**
	 * Insert a record of a vehicle into the MySQL database
	 * @param Vehicle to insert into the database
	 */
	public static void insertRecord(Vehicle vec) {
		String query = "INSERT INTO vehicles (license_plate, make, model, year, daily_rate, vehicle_type, status) "
	             + "VALUES ('" + vec.getLicensePlate() + "','" + vec.getMake() + "','" + vec.getModel() + "',"
	             + vec.getYear() + "," + vec.getDailyRate() + ",'"
	             + vec.getType().toString() + "','" + vec.getStatus().toString() + "')";
		
		MySQL.insert(query);
	}
	
}
