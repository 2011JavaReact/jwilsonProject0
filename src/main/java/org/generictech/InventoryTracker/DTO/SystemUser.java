package org.generictech.InventoryTracker.DTO;
/**
 * Class that represents a user of this inventory tracking system.
 * @author Jaden Wilson
 * @since 1.0
 */
public class SystemUser {
	private int systemUserId;
	private String fName;
	private String lName;
	private String username;
	private String password;
	private boolean isManager;
	private String salt;
	
	public SystemUser() {
		super();
	}
	
	/**
	 * Constructor for a SystemUser object.
	 * @param systemUserId Int value representing the id value of the user.
	 * @param fName The first name of the user as a string.
	 * @param lName The last name of the user as a string.
	 * @param username The username of the user as a string.
	 * @param password The user's password (should already be hashed) as a string.
	 * @param isManager boolean value to determine if the user has management privileges.
	 * @param salt String value to help randomize hashed password. 
	 */
	public SystemUser(int systemUserId, String fName, String lName, String username, String password, boolean isManager,
			String salt) {
		super();
		this.systemUserId = systemUserId;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
		this.salt = salt;
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
	 * Getter for the fname field.
	 * @return String First name of the user. 
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 * Setter for the fname field.
	 * @param fName First name of the user. 
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Getter for the lname field.
	 * @return String last name of the user. 
	 */
	public String getlName() {
		return lName;
	}
	
	/**
	 * Setter for the lname field.
	 * @param lName String value for the last name of the user. 
	 */
	public void setlName(String lName) {
		this.lName = lName;
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
	
	/**
	 * Getter for the salt field
	 * @return String value of the random salt. 
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * Setter for the salt.
	 * @param salt String value for the salt. 
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	/**
	 * Overridden toString method providing string representation of the systemUser object. 
	 */
	public String toString() {
		return "SystemUser Details: [systemUserId=" + systemUserId + ", fName=" + fName + ", lName=" + lName + ", username="
				+ username + ", password=" + password + ", isManager=" + isManager + ", salt=" + salt + "]";
	}
	
	
}
