package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	
	/**
	 * A service-layer wrapper for getting all reimbursements from the DAO layer
	 * 
	 * @return ArrayList Contains all reimbursements
	 */
	
	public ArrayList<Reimbursement> getReimbursements(){
		try {
			return rDao.getReimbursements();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reimbursement> getReimbursements(int userId){
		//TODO:implement validation logic?
		
		try {
			return rDao.getReimbursements(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
