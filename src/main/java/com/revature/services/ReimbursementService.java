package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	
	/**
	 * A service-layer wrapper for getting all reimbursements from the DAO layer
	 * 
	 * @return ArrayList Contains all reimbursements
	 */
	
	public ArrayList<Reimbursement> getReimbursements(){
		try {
			ArrayList<Reimbursement> result = rDao.getReimbursements();
			//System.out.println(result);
			return result;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Service layer wrapper for getting all reimbursements for a given ID
	 * 
	 * @param userId int the userId being searched for
	 * @return ArrayList containing the reimbursements for the user
	 */
	
	public ArrayList<Reimbursement> getReimbursementsByUserId(int userId){
		try {
			return rDao.getReimbursements(userId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	
	}
	
	/**
	 * Service layer wrapper for getting all reimbursements for a given ID
	 * 
	 * @param username String the username being searched for
	 * @return ArrayList containing the reimbursements for the user
	 */
	public ArrayList<Reimbursement> getReimbursements(String username){
	
		try {
			return rDao.getReimbursements(username);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status){
		try {
			return rDao.getReimbursementsByStatus(status);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean addReimbursement(ReimbursementDTO rdto) {
		Reimbursement reimbursement = rdto.getReimbursement();//parse out the reimbursement
		try {
			return rDao.addReimbursement(reimbursement);
		}
		catch(SQLException e) {
			//Exception("Something went wrong", e);
			return false; //if something goes wrong, nothing is added
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException("Non-numeric data entered");
		}
	}

	public boolean updateStatus(boolean approved, int id, String username) {
		int newStatus;
		boolean resolved;
		
		try {
			ArrayList<Reimbursement> results = rDao.getReimbursements(id);
			resolved = (results.size() > 0 && results.get(0).getStatus() != 1);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		if (!resolved) { //no change made if status is already resolved
			if (approved) {
				newStatus = 2;
			} else {
				newStatus = 3;
			}
			UserDao uDao = new UserDao();
			int resolverId;
			try {
				resolverId = uDao.getUserId(username);
				return rDao.updateStatus(id, newStatus, resolverId);

			} 
			catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
			//return false;
		}
		return false;
	}

	public ArrayList<Reimbursement> getReimbursementsByStatus(String username, int statusId) {
		UserDao uDao = new UserDao();
		int userId = uDao.getUserId(username);
		
		try {
			return rDao.getReimbursementsByUserAndStatus(userId, statusId);
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
		
}
