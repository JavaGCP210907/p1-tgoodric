package com.revature.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface IReimbursementDao {

	public ArrayList<Reimbursement> getReimbursements() throws SQLException;
	public ArrayList<Reimbursement> getReimbursements(int id) throws SQLException;
	public ArrayList<Reimbursement> getReimbursements(String username) throws SQLException;
	public ArrayList<Reimbursement> getReimbursements(String fName, String lName) throws SQLException;
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) throws SQLException;
	public ArrayList<Reimbursement> getReimbursementsBySubmitterId(int userId) throws SQLException;
	public ArrayList<Reimbursement> getReimbursementsbyResolverId(int userId) throws SQLException;
	
	public boolean addReimbursement(Reimbursement r) throws SQLException;
}
