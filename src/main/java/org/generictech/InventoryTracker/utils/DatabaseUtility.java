package org.generictech.InventoryTracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

/**
 * Class to hold database utility methods to be used by other classes
 * @author Jaden Wilson
 * @since 1.0
 */
public class DatabaseUtility {

	/**
	 * Method to gain a connection to the database
	 * @return Connection object containing database connection details. 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		String url = "";
		String username = "";
		String password = "";
		
		Connection connection = null;
		
		DriverManager.registerDriver(new Driver());
		connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}
