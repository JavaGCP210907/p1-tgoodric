package com.revature.models;

import com.revature.daos.UserDao;

public class ReimbursementDTO {

	private String amount;
	private String description;
	private String username;
	private String expenseType;

	public ReimbursementDTO(String amountString, String description, String username, String expenseType) {
		super();
		this.amount = amountString;
		this.description = description;
		this.username = username;
		this.expenseType = expenseType;
	}

	public Reimbursement getReimbursement() throws NumberFormatException {

		Reimbursement reimbursement = null;
		double amountD;
		try {
			System.out.println("parsing the amount");
			amountD = Double.parseDouble(amount);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(); //rethrow for handling in service layer
		}
		//Date submitted = new Date(System.currentTimeMillis());
		UserDao uDao = new UserDao();
		int userId = uDao.getUserId(username);
		int expenseId;
		switch(expenseType.toLowerCase()) {
			case "lodging":{
				expenseId = 1;
				break;
			}
			case "travel":{
				expenseId = 2;
				break;
			}
			case "food":{
				expenseId = 3;
				break;
			}
			default:{
				expenseId = 4;
				break;
			}
		}//end switch
		reimbursement = new Reimbursement(amountD, description, expenseId, userId);
		
		return reimbursement;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [amount=" + amount + ", description=" + description + ", username=" + username
				+ ", expenseType=" + expenseType + "]";
	}
	
}
