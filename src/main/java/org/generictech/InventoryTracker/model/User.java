package org.generictech.InventoryTracker.model;

/**
 * Class containing the necessary information for each user to be 
 * sent back and forth to the user. While this class does not contain all
 * the fields contained in the database, it is the primary way to represent 
 * system users in order to preserve sensitive data. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class User {
	private int systemUserId;
	private String username;
	private boolean isManager;
	
	/**
	 * No args constructor for the user class. 
	 */
	public User() {
		super();
	}

	/**
	 * Constructor for the user class.
	 * @param systemUserId
	 * @param username
	 * @param isManager
	 */
	public User(int systemUserId, String username, boolean isManager) {
		super();
		this.systemUserId = systemUserId;
		this.username = username;
		this.isManager = isManager;
	}

	/**
	 * Getter for the systemUserId.
	 * @return int ID value for user. 
	 */
	public int getSystemUserId() {
		return systemUserId;
	}
	
	/**
	 * Setter for the systemUserId.
	 * @param systemUserId Int id value for the user.
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
	 * Getter for the isManager field
	 * @return boolean value determining if the user has mangement privileges. 
	 */
	public boolean isManager() {
		return isManager;
	}
	
	/**
	 * Setter for the isManager field
	 * @param isManager boolean value if the user is a manager. 
	 */
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	

	@Override
	/**
	 * Overridden toString method providing string representation of the systemUser object. 
	 */
	public String toString() {
		return "User Details: [systemUserId=" + systemUserId + ", username="
				+ username + ", isManager=" + isManager  + "]";
	}
}
