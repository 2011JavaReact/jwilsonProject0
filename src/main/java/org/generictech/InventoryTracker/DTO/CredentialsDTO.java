package org.generictech.InventoryTracker.DTO;

/**
 * Class to handle data tranfer of credentials during login. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class CredentialsDTO {
	private String username;
	private String password;
	
	/**
	 * No args constructor
	 */
	public CredentialsDTO() {
		super();
	}
	
	/**
	 * Contructor with parameters
	 * @param username
	 * @param password
	 */
	public CredentialsDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	

	@Override
	/**
	 * Overridden toString method providing string representation of the systemUser object. 
	 */
	public String toString() {
		return "SystemUser Details: [username=" + username + ", password=" + password + "]";
	}
}
