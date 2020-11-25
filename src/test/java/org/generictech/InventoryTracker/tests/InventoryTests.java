package org.generictech.InventoryTracker.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.InventoryDAO;
import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.service.InventoryService;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the Inventory servlet stack (InventoryServlet and InventoryService). 
 * @author Jaden Wilson
 * @since 1.0
 */
public class InventoryTests {
	
	private InventoryDAO mockedDAO;
	private Product p;
	private ArrayList<Inventory> inventories = new ArrayList<>();
	
	@Before
	public void prep() {
		mockedDAO = mock(InventoryDAO.class);
		p = new Product(2, "Helmet", "New helmet", 22.99);
	}
	
	/**
	 * Test to see if the DAO returns empty, that the service will also return empty.
	 * @throws SQLException
	 */
	@Test
	public void getAllInventoryEmptyInventoryTest() throws SQLException {
			when(mockedDAO.getAllInventory()).thenReturn(new ArrayList<Inventory>());
			
		InventoryService inventoryService = new InventoryService(mockedDAO);
		assertEquals(new ArrayList<Inventory>(), inventoryService.getAllInventory());
	}
	
	/**
	 * Test to check the inventoryService class's ability to determine the proper
	 * DAO method to be called when an integer string is passed in.
	 * @throws SQLException
	 */
	@Test
	public void searchInventoryByIdTest() throws SQLException {
		inventories.add(new Inventory(1, 5, new Product(), "2020-11-24", new User()));
			when(mockedDAO.searchInventoryById(1)).thenReturn(inventories);
			when(mockedDAO.searchInventoryByProductName("Helmet")).thenReturn(inventories);
			
		InventoryService inventoryService = new InventoryService(mockedDAO);
		assertEquals(inventories, inventoryService.searchInventory("1"));
		assertNotEquals(new ArrayList<Inventory>(), inventoryService.searchInventory("1"));
	}
	
	/**
	 * Test to check the inventoryService class's ability to determine the proper
	 * DAO method to be called when a non-integer string is passed in. 
	 * @throws SQLException
	 */
	@Test
	public void searchInventoryByProductNameTest() throws SQLException {
		inventories.add(new Inventory(1, 5, p, "2020-11-24", new User()));
			when(mockedDAO.searchInventoryById(1)).thenReturn(inventories);
			when(mockedDAO.searchInventoryByProductName("Helmet")).thenReturn(inventories);
		InventoryService inventoryService = new InventoryService(mockedDAO);
		assertEquals(inventories, inventoryService.searchInventory("Helmet"));
		assertNotEquals(new ArrayList<Inventory>(), inventoryService.searchInventory("Helmet"));
	}
}
