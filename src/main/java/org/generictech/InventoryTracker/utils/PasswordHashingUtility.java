package org.generictech.InventoryTracker.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashingUtility {
	/**
	 * Method to generate a hashed password and salt to go with the hash. 
	 * @param passwd String version of password to be hashed
	 * @param salt String value for the salt to be used with the password.
	 * @return String 
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHash(String passwd, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(salt);		
		byte[] hash = md.digest(passwd.getBytes(StandardCharsets.UTF_8));
		String data = getString(hash);
		return data;
	}
	
	/**
	 * Method to validate entered password
	 * @param password String value of password to be checked
	 * @param hashedPassword Hashed password for user
	 * @param salt String value for the hash used for the users password
	 * @return boolean value stating wether the user is authenticated or not. 
	 * @throws NoSuchAlgorithmException
	 */
	public boolean validatePassword(String password, String hashedPassword, String salt) throws NoSuchAlgorithmException {
		String hashedSet = generateHash(password, salt.getBytes(StandardCharsets.UTF_8));
		return hashedPassword.equals(hashedSet);
	}
	
	/**
	 * Method to get a random salt value to be used when hashing passwords. 
	 * @return String salt value
	 */
	public String getSalt() {
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[16];
		rand.nextBytes(salt);
		return getString(salt);
	}
	
	/**
	 * Helper method to conver byte[] values used in hashing to strings for simpelr database storage. 
	 * @param in byte[] value to be converted
	 * @return String version of entered byte[].
	 */
	private String getString(byte[] in) {
		StringBuilder sb = new StringBuilder();
		for (byte b : in) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
}
