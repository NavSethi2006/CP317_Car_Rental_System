/*
 * Author : Navin Sethi
 * ID	  : 169086962
 * Email  : seth6962@mylaurier.ca
 * 
 */

package main.java.com.carrental.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Create a new instance of the mysql server for the car_rental_system.
 * Only one instance of this class needs to be created however most fuctions
 * can be called anywhere in the program
 * <p>
 */
public class MySQL {
	
	private String URL;
	private String username;
	private String password;
	private static Connection connection;
	
	//
	public void Connect_to_MySQL_Database() {
		URL = "jdbc:mysql://localhost:3306/car_rental?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		username = "user";
		password = "root";
		try {
			connection = DriverManager.getConnection(URL, username, password);
		} catch (SQLException e) {
			System.err.print("Database is offline. Please try again later");
		}
	}
	
	/**
	 * Fetch function to get information from the MySQL server
	 * @param selectStmt the string statement of which the MySQL server will execute
	 * @return result the ResultSet representation of the MySQL servers reply
	 * 
	 */
	public static ResultSet fetch(String selectStmt) {
		ResultSet result = null;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(selectStmt);
		} catch(SQLException e) {
			System.err.print("Failed to fetch data from database, Please try again later");
		}
		
		return result;
	}
	
	/**
	 * Insert function to create a record of data for the MySQL server
	 * @param insertStmt the string statement of which the MySQL server will execute
	 */
	public static void insert(String insertStmt) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(insertStmt);
		} catch (SQLException e) {
			System.err.print("Failed to create insert statement");
		}
	}
}
