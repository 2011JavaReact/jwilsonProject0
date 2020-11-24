package org.generictech.InventoryTracker.DTO;

public class SystemUserDTO {
	private String fName;
	private String lName;
	private String username;
	private String password;
	private boolean isManager;
	
	/**
	 * No args constructor
	 */
	public SystemUserDTO() {
		super();
	}
	
	/**
	 * Constructor for a NewSystemUser
	 * @param fName String value for the fname
	 * @param lName String value for the lname
	 * @param username String value for the username
	 * @param password String value representing the hashed password
	 * @param isManager boolean value expressing if a user is a manager
	 * @param salt String value of the salt to pair with the hashed password. 
	 */
	public SystemUserDTO(String fName, String lName, String username, String password, boolean isManager) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
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
	public boolean getIsManager() {
		return isManager;
	}
	
	/**
	 * Setter for the isManager field
	 * @param isManager boolean value if the user is a manager. 
	 */
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	

	@Override
	/**
	 * Overridden toString method providing string representation of the systemUser object. 
	 */
	public String toString() {
		return "SystemUser Details: fName=" + fName + ", lName=" + lName + ", username="
				+ username + ", password=" + password + ", isManager=" + isManager + "]";
	}
}
