package org.generictech.InventoryTracker.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.InventoryDAO;
import org.generictech.InventoryTracker.DTO.InventoryBasicDTO;
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
	 * No args constructor
	 */
	public InventoryService() {
		super();
	}
	
	/**
	 * Constructor for the InventoryService class
	 * @param inventoryDAO
	 */
	public InventoryService(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	/**
	 * Method to handle getting all inventory records
	 * @return ArrayList<Inventory> of all inventory records 
	 * @throws SQLException
	 */
	public ArrayList<Inventory> getAllInventory() throws SQLException {
		return inventoryDAO.getAllInventory();
	}
	
	/**
	 * Method to handle getting all inventory records for a basic user.
	 * This method is similar to the getAllInventory method, except it returns 
	 * an ArrayList<InventoryBasicDTO> to accommodate basic users. 
	 * @return ArrayList<InventoryBasicDTO> of inventory records.
	 * @throws SQLException
	 * @see {@link InventoryService#getAllInventory()}
	 */
	public ArrayList<InventoryBasicDTO> basicGetAllInventory() throws SQLException {
		return inventoryDAO.basicGetAllInventory();
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
	 * Method to handle searching inventory. This method is similar to the above searchInventory method,
	 * and only differs in its return type. This is to allow different results for managers and basic users. 
	 * @param param search parameter
	 * @return ArrayList<InventoryBasicDTO>
	 * @throws SQLException
	 * @see {@link InventoryService#searchInventory(String)}
	 */
	public ArrayList<InventoryBasicDTO> basicSearchInventory(String param) throws SQLException {
		ArrayList<InventoryBasicDTO> inventory = new ArrayList<>();
		if (NumericUtility.isInt(param)) {
			inventory = inventoryDAO.basicSearchInventoryById(Integer.parseInt(param));
		} else {
			inventory = inventoryDAO.basicSearchInventoryByProductName(param);
		}
		return inventory;
	}
	
	/**
	 * Method to handle inserting of new inventory records
	 * @param inventoryData object containing necessary inventory data. 
	 * @return Inventory object with inserted inventory data. 
	 * @throws SQLException
	 */
	public Inventory insertInventory(InventoryDTO inventoryData) throws SQLException, IndexOutOfBoundsException {
		return inventoryDAO.insertInventory(inventoryData);
	}
	
	/**
	 * Method to handle inserting of new inventory records. This method is similar to the
	 * insertInventory() method, except this one returns an InventoryBasicDTO. This allows 
	 * for different views to be returned when managers and basic users insert data. 
	 * @param inventoryData object containing necessary inventory data. 
	 * @return Inventory object with inserted inventory data. 
	 * @throws SQLException
	 * @see {@link InventoryService#insertInventory(InventoryDTO)}
	 */
	public InventoryBasicDTO basicInsertInventory(InventoryDTO inventoryData) throws SQLException, IndexOutOfBoundsException {
		return inventoryDAO.basicInsertInventory(inventoryData);
	}
	
	/**
	 * Method to handle updating inventory records. 
	 * @param inventoryData Data to update with
	 * @param id value for the inventory record to be updated
	 * @return Inventory object with updated details
	 * @throws SQLException
	 */
	public Inventory updateInventory(InventoryDTO inventoryData, int id) throws SQLException {
		return inventoryDAO.updateInventory(inventoryData, id);
	}
	
	/**
	 * Method to handle updating inventory records.
	 * This method is similar to the above updateInventory method, but 
	 * differs in the return type to accommodate basic users. 
	 * @param inventoryData
	 * @param id
	 * @return InventoryBasicDTO object with basic inventory details
	 * @throws SQLException
	 * @see {@link InventoryService#updateInventory(InventoryDTO, int)}
	 */
	public InventoryBasicDTO basicUpdateInventory(InventoryDTO inventoryData, int id) throws SQLException {
		return inventoryDAO.basicUpdateInventory(inventoryData, id);
	}
	
	/**
	 * Method to handle deleting inventory records
	 * @param id value of the inventory record to be deleted
	 * @return boolean value expressing if the delete worked properly. 
	 * @throws SQLException
	 */
	public boolean deleteInventory(String id) throws SQLException, NumberFormatException {
		return inventoryDAO.deleteInventory(Integer.parseInt(id));
	}
}
