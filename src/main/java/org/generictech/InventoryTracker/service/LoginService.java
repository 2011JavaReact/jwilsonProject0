package org.generictech.InventoryTracker.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.security.auth.login.LoginContext;

import org.generictech.InventoryTracker.DAO.LoginDAO;
import org.generictech.InventoryTracker.DTO.AuthDTO;
import org.generictech.InventoryTracker.DTO.CredentialsDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.utils.PasswordHashingUtility;

/**
 * Class to handle operations dealing with logins
 * @author Jaden Wilson
 * @since 1.0
 */
public class LoginService {
	private LoginDAO loginDAO = new LoginDAO();
	
	/**
	 * No args constructor
	 */
	public LoginService() {
		super();
	}
	
	/**
	 * Contructor with parameters (primarily for testing purposes)
	 * @param loginDAO
	 */
	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	/**
	 * Method to handle login operations, including password hashing.
	 * @param credentials Object with user credentials
	 * @return User object with user details to allow session creation
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	public User login(CredentialsDTO credentials) throws SQLException, NoSuchAlgorithmException {
		AuthDTO authData =  loginDAO.getLoginInfo(credentials.getUsername());
		PasswordHashingUtility hash = new PasswordHashingUtility();
		boolean success =  hash.validatePassword(credentials.getPassword(), authData.getPassword()
				, authData.getSalt());
		if (success) {
			return new User(authData.getSystemUserId(), authData.getUsername(), authData.getIsManager());
		} else {
			return null;
		}
	}
}
