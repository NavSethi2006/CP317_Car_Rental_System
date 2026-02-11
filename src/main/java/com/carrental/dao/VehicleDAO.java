package main.java.com.carrental.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import main.java.com.carrental.model.Vehicle;
import main.java.com.carrental.model.Vehicle.VehicleStatus;
import main.java.com.carrental.model.Vehicle.VehicleType;

public interface VehicleDAO {
	List<Vehicle> findAvailableVehicles(VehicleType type, LocalDate start, LocalDate end);
	Optional<Vehicle> findbyID(String id);
	Vehicle save(Vehicle vehicle);
	boolean updateStatus(String vehicleID, VehicleStatus status);
	void delete(String id);
	
	List<Vehicle> findByType(Vehicle type);
	
}
