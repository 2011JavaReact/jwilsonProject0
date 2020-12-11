package org.generictech.InventoryTracker.DTO;

/**
 * Class to handle data from the user for inserting a new product.
 * As the ID field is not nessessary for the user to provide, this class is used
 * to read in that user input and insert into the database. The data from this object is then used
 * to make a product object with the ID returned from the database. 
 * @author Jaden Wilson
 * @since 1.0
 */
public class ProductDTO {
	private String productName;
	private String description;
	private double unitPrice;
	
	/**
	 * No args constructor
	 */
	public ProductDTO() {
		super();
	}
	
	/**
	 * Constructor taking all fields as parameters 
	 * @param productName
	 * @param description
	 * @param unitPrice
	 */
	public ProductDTO(String productName, String description, double unitPrice) {
		super();
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
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
		return "ProductDTO Details: [productName=" + productName + ", description=" + description
				+ ", unitPrice=" + unitPrice + "]";
	}
}
