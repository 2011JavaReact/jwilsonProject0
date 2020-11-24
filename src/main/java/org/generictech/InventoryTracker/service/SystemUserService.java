package org.generictech.InventoryTracker.service;

import java.sql.SQLException;

import org.generictech.InventoryTracker.DAO.SystemUserDAO;
import org.generictech.InventoryTracker.model.User;

public class SystemUserService {
	
	SystemUserDAO systemUserDAO = new SystemUserDAO();

	public User searchUsersByUsername(String username) throws SQLException {
		return systemUserDAO.searchUsersByUsername(username); 
	}
}
