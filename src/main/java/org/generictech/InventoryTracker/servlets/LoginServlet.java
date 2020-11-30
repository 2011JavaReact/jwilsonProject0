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
import org.generictech.InventoryTracker.DTO.CredentialsDTO;
import org.generictech.InventoryTracker.model.User;
import org.generictech.InventoryTracker.service.LoginService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to handle HTTP requests to the /login endpoint
 * @author Jaden Wilson
 * @since 1.0
 */
@WebServlet(urlPatterns= {"/login", "/logout"})
public class LoginServlet extends HttpServlet {
	private ObjectMapper om = new ObjectMapper();
	private Logger logger = Logger.getLogger(LoginServlet.class);
	private LoginService loginService = new LoginService();
	
	/**
	 * Method to handle get requests to the /logout enpoint. Requests made to the /login endpoint
	 * will be returned with a 404 status. 
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			if (req.getSession(false) != null && req.getServletPath().equals("/logout")) {
				logger.info("GET request to /logout enpoint");
				HttpSession s = req.getSession(false);
				String username = (String) s.getAttribute("username");
				s.invalidate();
				if (req.getSession(false) == null) {
					logger.info("user " + username + " logged out");
					res.setStatus(204);
				} else {
					res.setStatus(500);
				}
			} else {
				if (req.getServletPath().equals("/login")) {
					res.setStatus(404);
				} else if (req.getSession(false) == null) {
					res.setStatus(401);
				} else {
					res.setStatus(400);
				}
			}
		}
	
	/**
	 * Method to handle POST requests to the /login endpoint
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getServletPath().equals("/logout")) {
			res.setStatus(404);
			return;
		}
		
		logger.info("POST request to /login");
		
		try {
			CredentialsDTO credentials = om.readValue(req.getReader(), CredentialsDTO.class);
			User u = loginService.login(credentials);
			if (u != null) {
				HttpSession session =  req.getSession();
				session.setAttribute("systemUserId", u.getSystemUserId());
				session.setAttribute("username", u.getUsername());
				session.setAttribute("isManager", u.isManager());
				logger.info("Successful login for user: " + u.getUsername());
				res.getWriter().append(om.writeValueAsString(u));
				res.setContentType("application/json");				
			} else {
				res.setStatus(401);
			}
		} catch (JsonProcessingException e) {
			res.setStatus(400);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			res.setStatus(500);
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
