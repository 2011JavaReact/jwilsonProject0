package org.generictech.InventoryTracker.DTO;

/**
 * Class to handle data returned from the database for authentication
 * @author Jaden Wilson
 * @since 1.0
 */
public class AuthDTO {
	private int systemUserId;
	private String username;
	private String password;
	private String salt;
	private boolean isManager;
	
	/**
	 * No arg constructor 
	 */
	public AuthDTO() {
		super();
	}
	
	/**
	 * Constructor for new AuthDTO objects
	 * @param username
	 * @param password
	 * @param salt
	 * @param isManager
	 */
	public AuthDTO(int systemUserId, String username, String password, String salt, boolean isManager) {
		this.systemUserId = systemUserId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.isManager = isManager;
	}
	
	/**
	 * Getter for systemUserId field
	 * @return int value of the systemUserId
	 */
	public int getSystemUserId() {
		return systemUserId;
	}

	/**
	 * Setter for the systemUserId field
	 * @param systemUserId int value for the systemUserId
	 */
	public void setSystemUserId(int systemUserId) {
		this.systemUserId = systemUserId;
	}

	/**
	 * Getter for the username
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter for the username of the user
	 * @param username String value for the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter for the hashed password of the user. 
	 * @return String value of the hashed password. 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter for the password
	 * @param password String of hashed plaintext password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the salt field
	 * @return String value of the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * Setter for the salt field
	 * @param salt String value to set the salt to.
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * Getter for the isManager field
	 * @return boolean value of isManager field
	 */
	public boolean getIsManager() {
		return isManager;
	}

	/**
	 * Setter for isManager field
	 * @param isManager 
	 */
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	/**
	 * Overridden toString method providing string representation of the systemUser object. 
	 */
	public String toString() {
		return "SystemUser Details: [username=" + username + ", password=" + password + "isManager= " + isManager + "]";
	}
}
