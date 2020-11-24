package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database interactions dealing with system users. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class SystemUserDAO {

	
	public User searchUsersByUsername(String username) throws SQLException {
		String query = "SELECT system_user_id, username, ismanager"
				+ " FROM system_user"
				+ " WHERE username = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) {
			return new User(result.getInt("system_user_id"), username, result.getBoolean("ismanager"));
		} else {
			throw new SQLException("No results.");
		}
	}
}
