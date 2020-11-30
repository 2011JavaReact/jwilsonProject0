package org.generictech.InventoryTracker.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.generictech.InventoryTracker.DTO.SystemUserDTO;
import org.generictech.InventoryTracker.service.SystemUserService;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Class to handle HTTP requests dealing with system users
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/systemuser", "/systemuser/*"})
public class SystemUserServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(InventoryServlet.class);
	private SystemUserService systemUserService = new SystemUserService();

	/**
	 * Method to handle POST requests to the /systemuser endpoints
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !(boolean)session.getAttribute("isManager")) {
			res.setStatus(401);
			return;
		}
		logger.info("POST request to /systemuser");
		if (req.getPathInfo() != null) {
			res.setStatus(400);
		} else {
			try {
				SystemUserDTO systemUserData = om.readValue(req.getReader(), SystemUserDTO.class);
				res.getWriter().append(om.writeValueAsString(systemUserService.insertSystemUser(systemUserData)));
				res.setContentType("application/json");
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				res.setStatus(500);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to handle DELETE requests to the /systemUser endpoint. Requests
	 * should come in with the id value of the system user to be deleted. 
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse) 
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || !(boolean)session.getAttribute("isManager")) {
			res.setStatus(401);
			return;
		}
		
		logger.info("DELETE request to /systemuser");
		if (req.getPathInfo() == null || req.getPathInfo().split("/").length != 2) {
			res.setStatus(400);
		} else {
			try {
				boolean success = systemUserService.deleteSystemUser(Integer.parseInt(req.getPathInfo().split("/")[1]));
				if (success) {
					res.setStatus(204);
				} else {
					res.setStatus(400);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				res.setStatus(400);
			} catch (SQLException e) {
				res.setStatus(400);
				e.printStackTrace();
			}
		}
	}
}
