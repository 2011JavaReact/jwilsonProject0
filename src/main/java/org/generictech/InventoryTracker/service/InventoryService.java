package org.generictech.InventoryTracker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.InventoryDAO;
import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.utils.NumericUtility;

/**
 * Class to contain the business logic and methods pertaining to the 
 * inventory records. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class InventoryService {

	InventoryDAO inventoryDAO = new InventoryDAO();
	/**
	 * Method to handle getting all inventory records
	 * @return ArrayList<Inventory> of all inventory records 
	 * @throws SQLException
	 */
	public ArrayList<Inventory> getAllInventory() throws SQLException {
		return inventoryDAO.getAllInventory();
	}
	
	public ArrayList<Inventory> searchInventory(String param) throws SQLException {
		ArrayList<Inventory> inventory = new ArrayList<>();
		if (NumericUtility.isInt(param)) {
			inventory = inventoryDAO.searchInventoryById(Integer.parseInt(param));
		} else {
			inventory = inventoryDAO.searchInventoryByProductName(param);
		}
		return inventory;
	}
}
