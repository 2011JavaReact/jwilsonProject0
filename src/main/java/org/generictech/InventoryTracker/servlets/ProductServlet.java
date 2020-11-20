package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.generictech.InventoryTracker.service.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation for ProductServlet. Servlet handles incoming connections
 * specifically dealing with products. 
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns={"/product", "/product/*"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper om = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * Method for handling GET requests to /products endpoint
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService productService = new ProductService();
		
		try {
			res.getWriter().append(om.writeValueAsString(productService.getAllProducts()));
		} catch (JsonProcessingException e) {
			res.setStatus(500);
			e.printStackTrace();
		} catch (IOException e) {
			res.setStatus(500);
			e.printStackTrace();
		} catch (SQLException e) {
			res.setStatus(500);
			e.printStackTrace();
		}
		
		res.setContentType("application/json");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
