package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.enums.UserRole;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.UserServiceInterface;

public class AuthController {
	
	private UserServiceInterface userService = new UserServiceImplementation();
	
	private ObjectMapper om = new ObjectMapper();
	
	//actually do the request
	public void userLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		System.out.println(cred);
		User u = null;
		try {
			u = userService.login(cred.getUsername(), cred.getPassword());
			System.out.println(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//use your session to keep track of your user permission level
		HttpSession sess = req.getSession();
		
		if(u.getRole() == UserRole.EMPLOYEE) {
			sess.setAttribute("User-Role", "EMPLOYEE");
		}else if(u.getRole() == UserRole.FINANCE_MANAGER) {
			sess.setAttribute("User-Role", "FINANCE_MANAGER");		
		}
		sess.setAttribute("user", u);
		
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(u));
	}
	
	
	

}
