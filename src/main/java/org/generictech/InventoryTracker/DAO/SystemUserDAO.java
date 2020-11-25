package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.generictech.InventoryTracker.DTO.SystemUserDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database interactions dealing with system users. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class SystemUserDAO {

	/**
	 * Method to interact with the database to search system users by username.
	 * Usernames should be unique, so this should only return a single result. 
	 * @param username
	 * @return User with non private info about the user. 
	 * @throws SQLException
	 */
	public User searchUsersByUsername(String username) throws SQLException {
		String query = "SELECT system_user_id, username, ismanager"
				+ " FROM system_user"
				+ " WHERE username = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) {
			stmt.close();
			connection.close();
			return new User(result.getInt("system_user_id"), username, result.getBoolean("ismanager"));
		} else {
			stmt.close();
			connection.close();
			throw new SQLException("No results.");
		}
	}
	
	/**
	 * Method to interact with the database for inserting a new system user. 
	 * @param systemUserData
	 * @param salt
	 * @return User object with basic user data
	 * @throws SQLException
	 */
	public User insertSystemUser(SystemUserDTO systemUserData, String salt) throws SQLException {
		String query = "INSERT INTO system_user"
				+ " (fname, lname, username, password, ismanager, salt)"
				+ " VALUES"
				+ " (?, ?, ?, ?, ?, ?)";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, systemUserData.getfName());
		stmt.setString(2, systemUserData.getlName());
		stmt.setString(3, systemUserData.getUsername());
		stmt.setString(4, systemUserData.getPassword());
		stmt.setBoolean(5, systemUserData.getIsManager());
		stmt.setString(6, salt);
		
		if (stmt.executeUpdate() != 1) {
			stmt.close();
			connection.close();
			throw new SQLException("No Rows Affected");
		}
		
		int id = 0;
		ResultSet keys = stmt.getGeneratedKeys();
		if (keys.next()) {
			id = keys.getInt(1);
		} else {
			throw new SQLException("No Keys Generated");
		}
		
		stmt.close();
		connection.close();
		return new User(id, systemUserData.getUsername(), systemUserData.getIsManager());
	}
	
	/**
	 * Method to interact with the database to delete a system user. 
	 * @param id
	 * @return boolean stating if the delete operation succeeded
	 * @throws SQLException
	 */
	public boolean deleteSystemUser(int id) throws SQLException {
		String query = "DELETE FROM system_user"
				+ " WHERE system_user_id = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		if (stmt.executeUpdate() == 1) {
			stmt.close();
			connection.close();
			return true;
		} else {
			stmt.close();
			connection.close();
			return false;
		}
	}
}
