package org.generictech.InventoryTracker.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.generictech.InventoryTracker.DAO.ProductDAO;
import org.generictech.InventoryTracker.model.Product;
import org.generictech.InventoryTracker.service.ProductService;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for testing the ProductService class
 * @author Jaden Wilson
 * @since 1.0
 */
public class ProductTests {
	
	private ProductDAO mockedDAO;
	private ArrayList<Product> products;
	private Product p;
	private ProductService productService;
	
	@Before
	public void prep() {
		mockedDAO = mock(ProductDAO.class);
		products = new ArrayList<>();
		p = new Product(1, "Helmet", "New helmet", 14.99);
		products.add(p);
		productService = new ProductService(mockedDAO);
	}

	@Test
	public void searchByIdTest() throws SQLException {
		ArrayList<Product> products2 = new ArrayList<Product>();
		products2.add(new Product(1, "Helmet", "another helmet", 15.99));
			when(mockedDAO.searchProductById(1)).thenReturn(products);
			when(mockedDAO.searchProductById(2)).thenReturn(new ArrayList<Product>());
			when(mockedDAO.searchProductByName("Helmet")).thenReturn(products2);
			
		assertEquals(products, productService.searchProducts("1"));
		assertNotEquals(products, productService.searchProducts("2"));
		assertEquals(new ArrayList<Product>(), productService.searchProducts("2"));
	
	}
	
	@Test
	public void searchByNameTest() throws SQLException {
		ArrayList<Product> products2 = new ArrayList<Product>();
		products2.add(new Product(1, "Helmet", "another helmet", 15.99));
			when(mockedDAO.searchProductByName("Helmet")).thenReturn(products);
			when(mockedDAO.searchProductByName("up")).thenReturn(new ArrayList<Product>());
			when(mockedDAO.searchProductById(1)).thenReturn(products2);
			
		assertEquals(products, productService.searchProducts("Helmet"));
		assertNotEquals(products, productService.searchProducts("1"));
		assertEquals(new ArrayList<Product>(), productService.searchProducts("up"));
	
	}
	
}
