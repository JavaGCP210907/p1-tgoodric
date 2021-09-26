package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface IReimbursementStatusDao {
	//TODO: Finalize
	public ArrayList<Reimbursement> getReimbursementsByStatus(int statusId);
	public ArrayList<Reimbursement> getReimbursementsByStatus(String status);
	public boolean updateStatus(int reimbursementId, int statusId);
}
