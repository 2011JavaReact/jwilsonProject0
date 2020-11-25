package org.generictech.InventoryTracker.DTO;

import org.generictech.InventoryTracker.model.Product;

/**
 * Class to handle data tranfer of inventory data for basic user without
 * manager permissions. Removes the lastUpdateBy field.
 * @author Jaden Wilson
 * @since 1.1
 */
public class InventoryBasicDTO {
	private int inventoryID;
	private int quantity;
	private Product product;
	private String lastUpdateDate;

	/**
	 * No args constructor
	 */
	public InventoryBasicDTO() {
		super();
	}

	/**
	 * Constructor with parameters for each field.
	 * @param inventoryID
	 * @param quantity
	 * @param product
	 * @param lastUpdateDate
	 */
	public InventoryBasicDTO(int inventoryID, int quantity, Product product, String lastUpdateDate) {
		this.inventoryID = inventoryID;
		this.quantity = quantity;
		this.product = product;
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * Getter for inventoryID field
	 * @return int value of inventory ID
	 */
	public int getInventoryID() {
		return inventoryID;
	}

	/**
	 * Setter for inventoryID field
	 * @param inventoryID int value for inventory id
	 */
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	/**
	 * Getter for quantity field
	 * @return int value of quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Setter for quantity field
	 * @param quantity int value for quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Getter for product field
	 * @return Product object of the specified product
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Setter for product field
	 * @param product Product object for product 
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Getter for lastUpdateDate field
	 * @return String value of date the record was last updated
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	/**
	 * Setter for lastUpdateDate field
	 * @param lastUpdateDate String value of the date the record was updated last
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	

	@Override
	public String toString() {
		return "InventoryBasicDTO Details: [inventoryID " + inventoryID + " quantity=" + quantity + ", product=" + product + "]";
	}	
}
