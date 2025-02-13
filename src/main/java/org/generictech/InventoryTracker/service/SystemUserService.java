package org.generictech.InventoryTracker.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import org.generictech.InventoryTracker.DAO.SystemUserDAO;
import org.generictech.InventoryTracker.DTO.SystemUserDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.utils.PasswordHashingUtility;

/**
 * Class to handle logic and operations dealing with system users.
 * @author Jaden Wilson
 * @since 1.0
 */
public class SystemUserService {
	
	SystemUserDAO systemUserDAO = new SystemUserDAO();

	/**
	 * Method to handle searching users by username.
	 * @param username
	 * @return User object with non private data about the user. 
	 * @throws SQLException
	 */
	public User searchUsersByUsername(String username) throws SQLException {
		return systemUserDAO.searchUsersByUsername(username); 
	}
	
	/**
	 * Method to handle inserting a new system user.
	 * @param systemUserData SystemUserDTO object with system user data
	 * @return User object containing non private user data. 
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public User insertSystemUser(SystemUserDTO systemUserData) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		PasswordHashingUtility hash = new PasswordHashingUtility();
		String salt = hash.getSalt();
		systemUserData.setPassword(hash.generateHash(systemUserData.getPassword(), salt.getBytes()));
		return systemUserDAO.insertSystemUser(systemUserData, salt);
	}
	
	/**
	 * Method to handle deleting of system users
	 * @param id
	 * @return boolean stating whether or not the delete was successful.
	 * @throws SQLException
	 */
	public boolean deleteSystemUser(int id) throws SQLException {
		return systemUserDAO.deleteSystemUser(id);
	}
}
