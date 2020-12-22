package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.enums.ReimbursementStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementPostgresDAO;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserPostgresDAO;

public class FinanceManagerServiceImplementation implements FinanceManagerServiceInterface {

	static private UserDAO uDAO = new UserPostgresDAO();
	static private ReimbursementDAO reimDAO = new ReimbursementPostgresDAO();

	@Override
	public User login(String username, String password) throws SQLException {
		return uDAO.getUser(username, password);		
	}

	@Override
	public List<Reimbursement> viewAllReimbursements() throws SQLException {
		return reimDAO.getAllReimbursements();
	}

	@Override
	public List<Reimbursement> viewAllReimbursementsByStatus(ReimbursementStatus status) throws SQLException {
		return reimDAO.getAllReimbursementsByStatus(status);
	}

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimbursement) throws SQLException {
		reimbursement.approveReimbursement();
		return reimDAO.updateReimbursementStatus(reimbursement);
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimbursement) throws SQLException {
		reimbursement.declineReimbursement();
		return reimDAO.updateReimbursementStatus(reimbursement);
	}

}
