package org.generictech.InventoryTracker.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.generictech.InventoryTracker.DAO.LoginDAO;
import org.generictech.InventoryTracker.DTO.AuthDTO;
import org.generictech.InventoryTracker.DTO.CredentialsDTO;
import org.generictech.InventoryTracker.utils.PasswordHashingUtility;

/**
 * Class to handle operations dealing with logins
 * @author Jaden Wilson
 * @since 1.0
 */
public class LoginService {
	private LoginDAO loginDAO = new LoginDAO();
	
	public boolean login(CredentialsDTO credentials) throws SQLException, NoSuchAlgorithmException {
		AuthDTO authData =  loginDAO.getLoginInfo(credentials.getUsername());
		PasswordHashingUtility hash = new PasswordHashingUtility();
		return hash.validatePassword(credentials.getPassword(), authData.getPassword(), authData.getSalt());
	}
}
