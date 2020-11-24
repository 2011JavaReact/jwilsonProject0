package org.generictech.InventoryTracker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.InventoryDAO;
import org.generictech.InventoryTracker.DTO.InventoryDTO;
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
	
	/**
	 * Method to direct searching of inventory records.
	 * @param param parameter from search request. 
	 * @return ArrayList<Inventory> of the inventory records meeting the given requirements. 
	 * @throws SQLException
	 */
	public ArrayList<Inventory> searchInventory(String param) throws SQLException {
		ArrayList<Inventory> inventory = new ArrayList<>();
		if (NumericUtility.isInt(param)) {
			inventory = inventoryDAO.searchInventoryById(Integer.parseInt(param));
		} else {
			inventory = inventoryDAO.searchInventoryByProductName(param);
		}
		return inventory;
	}
	
	/**
	 * Method to handle inserting of new inventory records
	 * @param inventoryData object containing necessary inventory data. 
	 * @return Inventory object with inserted inventory data. 
	 * @throws SQLException
	 */
	public Inventory insertInventory(InventoryDTO inventoryData) throws SQLException {
		return inventoryDAO.insertInventory(inventoryData);
	}
}
