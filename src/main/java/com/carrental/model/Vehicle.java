package main.java.com.carrental.model;

public abstract class Vehicle {
	private String id;
	private String licensePlate;
	private String make;
	private String model;
	private int year;
	private double dailyRate;
	private VehicleStatus status;
	private VehicleType type;
	
	public enum VehicleStatus {AVAILABLE, RENTED, MAINTAINANCE};
	public enum VehicleType {ECONOMY, COMPACT, SUV, LUXURY, TRUCK};
	
	public Vehicle(String id, String licensePlate, String make, String model, int year, double dailyRate) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyRate = dailyRate;
		status = VehicleStatus.AVAILABLE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}
	
}
