package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DTO.InventoryDTO;
import org.generictech.InventoryTracker.model.Inventory;
import org.generictech.InventoryTracker.service.InventoryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet class containing all methods to handle HTTP requests dealing with 
 * inventory items. 
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/inventory", "/inventory/*"})
public class InventoryServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(InventoryServlet.class);
	private InventoryService inventoryService = new InventoryService();

	/**
	 * no args constructor
	 */
	public InventoryServlet() {
		super();
	}
	
	/**
	 * Method to handle GET requests to the /inventory endpoints
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("GET request to /inventory endpoint");
		
		if (req.getPathInfo() != null && req.getPathInfo().split("/").length == 2) {
			try {
				res.getWriter().append(om.writeValueAsString(inventoryService.searchInventory(req.getPathInfo().split("/")[1])));
				res.setContentType("application/json");
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		} else {
			try {
				res.getWriter().append(om.writeValueAsString(inventoryService.getAllInventory()));
				res.setContentType("application/json");
			} catch (JsonProcessingException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IOException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to handle POST requests to the /inventory endpoints
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("POST request to /inventory endpoint");
		
		if (req.getPathInfo() != null) {
			res.setStatus(400);
		} else {
			try {
				InventoryDTO inventoryData = om.readValue(req.getReader(), InventoryDTO.class);
				res.getWriter().append(om.writeValueAsString(inventoryService.insertInventory(inventoryData)));
				res.setContentType("application/json");
			} catch (JsonProcessingException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IOException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to handle PUT requests to the /inventory endpoints
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("PUT request to /inventory endpoint");
		
		if (req.getPathInfo() == null || req.getPathInfo().split("/").length != 2) {
			res.setStatus(400);
		} else {
			try {
				String[] params = req.getPathInfo().split("/");
				InventoryDTO inventoryData = om.readValue(req.getReader(), InventoryDTO.class);
				Inventory i = inventoryService.updateInventory(inventoryData, Integer.parseInt(params[1]));
				res.getWriter().append(om.writeValueAsString(i));
				res.setContentType("application/json");
			} catch (NumberFormatException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to handle DELETE requests to the /inventory endpoint. 
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("DELETE request made to /inventory endpoint");
		
		if(req.getPathInfo() == null || req.getPathInfo().split("/").length != 2) {
			res.setStatus(400);
		} else {
			try {
				inventoryService.deleteInventory(req.getPathInfo().split("/")[1]);
				res.setStatus(204);
			} catch (NumberFormatException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
}
