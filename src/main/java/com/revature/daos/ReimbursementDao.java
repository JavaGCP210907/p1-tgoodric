/**
 * 
 */
package com.revature.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Reimbursement;

/**
 * @author tdgoo
 *
 */
public class ReimbursementDao implements IReimbursementDao {

	/**
	 * 
	 */
	public ReimbursementDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements(String fName, String lName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsBySubmitterId(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsbyResolverId(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
