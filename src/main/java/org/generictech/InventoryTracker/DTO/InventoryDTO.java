package org.generictech.InventoryTracker.DTO;


public class InventoryDTO {
	private int quantity;
	private int productId;
	private String lastUpdateDate;
	private String username;
	
	
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
	public int getProductId() {
		return productId;
	}
	
	/**
	 * Setter for product field
	 * @param product Product object for product 
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	 * Getter for username field
	 * @return String valdue for username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for username field
	 * @param username value for field
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Inventory Details: [quantity=" + quantity + ", productId=" + productId + ", lastUpdateDate=" + lastUpdateDate
				+ ", username=" + username + "]";
	}	
}
