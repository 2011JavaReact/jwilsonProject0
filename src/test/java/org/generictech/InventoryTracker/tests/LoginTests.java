package org.generictech.InventoryTracker.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.generictech.InventoryTracker.DAO.LoginDAO;
import org.generictech.InventoryTracker.DTO.AuthDTO;
import org.generictech.InventoryTracker.DTO.CredentialsDTO;
import org.generictech.InventoryTracker.service.LoginService;
import org.generictech.InventoryTracker.utils.PasswordHashingUtility;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the LoginService class, and its methods
 * @author Jaden Wilson
 *
 */
public class LoginTests {
	
	private LoginDAO mockedDAO;
	private LoginService loginService;
	private String password;
	private String salt;
	
	@Before
	public void prep() throws NoSuchAlgorithmException {
		mockedDAO = mock(LoginDAO.class);
		loginService = new LoginService(mockedDAO);
		PasswordHashingUtility hash = new PasswordHashingUtility();
		salt = hash.getSalt();
		password = hash.generateHash("mySecretPassword", salt.getBytes());
	}
	
	/**
	 * Test to test a valid login to the database
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	@Test 
	public void validLoginTest() throws NoSuchAlgorithmException, SQLException {
		when(mockedDAO.getLoginInfo("Ttester")).thenReturn(new AuthDTO(1, "Ttester", password, salt, true));
		
		assertNotEquals(null, loginService.login(new CredentialsDTO("Ttester", "mySecretPassword")));
	}
	
	/**
	 * Test to test a login with an incorrect password.
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	@Test 
	public void invalidLoginTest() throws NoSuchAlgorithmException, SQLException {
		when(mockedDAO.getLoginInfo("Ttester")).thenReturn(new AuthDTO(1, "Ttester", password, salt, true));
		
		assertEquals(null, loginService.login(new CredentialsDTO("Ttester", "myWrongPassword")));
	}
}
