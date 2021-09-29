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
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ArrayList<Reimbursement>();
		}
	}
	
	/**
	 * Service layer wrapper for getting all reimbursements for a given ID
	 * 
	 * @param userId int the userId being searched for
	 * @return ArrayList containing the reimbursements for the user
	 */
	
	public ArrayList<Reimbursement> getReimbursementsByUserId(int userId){
		//TODO:implement validation logic?
		
		try {
			return rDao.getReimbursements(userId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ArrayList<Reimbursement>();
		}
	
	}
	
	public ArrayList<Reimbursement> getReimbursements(String username){
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Reimbursement> getReimbursements(String fName, String lName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status){
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<Reimbursement> getReimbursementsbyResolverId(int userId){
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
