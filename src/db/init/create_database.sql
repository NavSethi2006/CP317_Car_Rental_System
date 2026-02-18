
use Car_Rental_System;

CREATE TABLE vehicles (
	id INT AUTO_INCREMENT PRIMARY KEY,
	license_plate VARCHAR(20) UNIQUE NOT NULL,
	make VARCHAR(225),
	model VARCHAR(255),
	year INT,
	daily_rate DECIMAL(10,2),
	vehicle_type enum('SEDAN', 'SUV', 'TRUCK', 'CAR'),
	status enum('AVAILABLE', 'RENTED', 'MAINTENANCE') DEFAULT 'AVAILABLE'
);

CREATE TABLE customers(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	phone VARCHAR(20),
	license_number VARCHAR(50)
);

CREATE TABLE rentals(
	id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    vehicle_id INT,
    customer_id INT,
    start_date DATETIME,
    end_date DATETIME,
    total_cost DECIMAL(10,2),
    FOREIGN KEY(vehicle_id) REFERENCES vehicles(id),
    FOREIGN KEY(customer_id) REFERENCES customers(id)
);
