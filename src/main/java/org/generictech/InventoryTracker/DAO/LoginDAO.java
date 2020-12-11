package org.generictech.InventoryTracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.generictech.InventoryTracker.DTO.AuthDTO;
import org.generictech.InventoryTracker.utils.DatabaseUtility;

/**
 * Class to handle database communications for login and authentication
 * @author Jaden Wilson
 * @since 1.0
 */
public class LoginDAO {
	
	/**
	 * method to interact with the database to get data necessary for authentication,
	 * searching by username
	 * @param username String value for the specified user
	 * @return AuthDTO object with authentication details.
	 * @throws SQLException
	 */
	public AuthDTO getLoginInfo(String username) throws SQLException {
		String query = "SELECT system_user_id, username, password, salt, ismanager"
				+ " FROM system_user"
				+ " WHERE username = ?";
		Connection connection = DatabaseUtility.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) {
			stmt.close();
			connection.close();
			return new AuthDTO(result.getInt(1), result.getString(2), result.getString(3)
					, result.getString(4), result.getBoolean(5));
		} else {
			stmt.close();
			connection.close();
			throw new SQLException("No results.");
		}
	}
}
