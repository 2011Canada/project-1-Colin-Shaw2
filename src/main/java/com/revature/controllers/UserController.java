package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Reimbursement;
import com.revature.services.FinanceManagerServiceImplementation;
import com.revature.services.FinanceManagerServiceInterface;

public class UserController {

	private FinanceManagerServiceInterface FMService = new FinanceManagerServiceImplementation();
	
	private ObjectMapper om = new ObjectMapper();
	

	public void findAllUsers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("getting sesstion");
		HttpSession sess = req.getSession();
		
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals("Admin")) {
//			throw new UnauthorizedException();
//		}
		List<Reimbursement> allReimbursement = null;
		System.out.println("service controller");
		try {
			allReimbursement = FMService.viewAllReimbursements();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allReimbursement));
		
	}
	
}
