package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.ReimbursementStatus;
import com.revature.enums.ReimbursementType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class ReimbursementPostgresDAO implements ReimbursementDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		Connection conn = cf.getConnection();
		List<Reimbursement> reimbursementList = new ArrayList<>();
		String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, "
				+ "reimb_author, reimb_resolver, reimb_status, reimb_type from ers_reimbursement "
				+ "join ers_reimbursement_status using (reimb_status_id) "
				+ "join ers_reimbursement_type using (reimb_type_id) "
				+ ";";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		
		while (res.next()) {
			reimbursementList.add(new Reimbursement(res.getInt("reimb_id"), res.getDouble("reimb_amount"), 
					res.getDate("reimb_submitted"), res.getDate("reimb_resolved"), res.getString("reimb_description"), 
					res.getInt("reimb_author"), ReimbursementStatus.valueOf(res.getString("reimb_status")), 
					ReimbursementType.valueOf(res.getString("reimb_type"))));
		}
		if(reimbursementList.size() == 0) {
			throw new SQLException();
		}
		return reimbursementList;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(ReimbursementStatus status) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getAllReimbursementsByUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Reimbursement updateReimbursementStatus(Reimbursement reimbursement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Reimbursement addReimbursement(Reimbursement reimbursement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
