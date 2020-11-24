package org.generictech.InventoryTracker.model;


/**
 * Class to represent an inventory record.
 * @author Jaden Wilson
 * @since 1.0
 *
 */
public class Inventory {
	private int inventoryID;
	private int quantity;
	private Product product;
	private String lastUpdateDate;
	private User lastUpdatedBy;
	
	/**
	 * No args constructor
	 */
	public Inventory() {
		super();
	}
	
	/**
	 * Inventory object constructor
	 * @param inventoryId Int value for inventory ID
	 * @param quantity Int value for the quantity of items.
	 * @param product Product object detailing the product the inventory record points to
	 * @param lastUpdateDate String containing the date the inventory record was updated. 
	 * @param lastUpdatedBy SystemUser object detailing which user last updated this inventory record (for management purposes).
	 */
	public Inventory(int inventoryId, int quantity, Product product, String lastUpdateDate, User lastUpdatedBy) {
		this.inventoryID = inventoryId;
		this.quantity = quantity;
		this.product = product;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdatedBy = lastUpdatedBy;
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
	
	/**
	 * Getter for lastUpdatedBy field
	 * @return User object of the user who updated this record last.
	 */
	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	/**
	 * Setter for lastUpdatedBy field
	 * @param lastUpdatedBy User object with the details of the user who updated this record last. 
	 */
	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "Inventory Details: [inventoryID " + inventoryID + " quantity=" + quantity + ", product=" + product + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}	
	
}
