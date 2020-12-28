package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.EmployeeServiceImplementation;
import com.revature.services.EmployeeServiceInterface;
import com.revature.services.FinanceManagerServiceImplementation;
import com.revature.services.FinanceManagerServiceInterface;

public class EmployeeController {

	private EmployeeServiceInterface empService = new EmployeeServiceImplementation();
	
	private ObjectMapper om = new ObjectMapper();
	

	public void findAllUserReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("getting session");
		HttpSession sess = req.getSession(false);		
		System.out.println(sess);
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals("Admin")) {
//			throw new UnauthorizedException();
//		}
		List<Reimbursement> allReimbursement = null;
		System.out.println("service controller");
		try {
			allReimbursement = empService.viewPastTickets((User)sess.getAttribute("user"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStatus(200);
//		res.addHeader("Content-Type", "application/json");
		if( allReimbursement == null) {
			allReimbursement = new ArrayList<>();
		}
		res.getWriter().write(om.writeValueAsString(allReimbursement));
		
	}
	
}
