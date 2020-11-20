package org.generictech.InventoryTracker.model;

/**
 * Class representing a single product for which inventory will be tracked. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class Product {
	private  int productId;
	private String productName;
	private String description;
	private double unitPrice;
	
	/**
	 * No args constructor
	 */
	public Product() {
		super();
	}
	
	/**
	 * Constructor 
	 * @param productId int value for productId field
	 * @param productName string value for productName field
	 */
	public Product(int productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}
	
	/**
	 * Constructor
	 * @param productId int value for productId field
	 * @param productName string value for productName field
	 * @param description string value for description field
	 */
	public Product(int productId, String productName, String description) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
	}
	
	/**
	 * Constructor
	 * @param productId int value for productId field
	 * @param productName string value for productName field
	 * @param unitPrice double value for unitPrice field
	 */
	public Product(int productId, String productName, double unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}
	
	/**
	 * Constructor
	 * @param productId int value for productId field
	 * @param productName string value for productName field
	 * @param description string value for description field
	 * @param unitPrice dpuble value for unitPrice field
	 */
	public Product(int productId, String productName, String description, double unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	
	/**
	 * Getter for productId field
	 * @return int value of the productId
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * Setter for the productId field
	 * @param productId int value for productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * Getter for ProductName field
	 * @return String value of the product name
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Setter for the productName field
	 * @param productName String value for the productName. 
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * Getter for the description field
	 * @return String value of the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for the description field
	 * @param description String value for the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for the unitPrice field
	 * @return double value of the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	
	/**
	 * Setter for the unitPrice field
	 * @param unitPrice double value for the unit price
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	/**
	 * Overridden implementation of the toString function.
	 */
	public String toString() {
		return "Product Details: [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", unitPrice=" + unitPrice + "]";
	}
	
}
