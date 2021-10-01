package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;


public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	
	
	public Handler getAllReimbursementsHandler = (ctx)->{
		
		//check for session, return 403 for if not a manager
		/*
		if(ctx.req.getSession(false) != null) {
			//TODO: implement logins, check 09/27 morning, 
			//See also the nonsense about same-site cookies in Discord
		}
		else {
			return;
		}*/
		
		
		if((ctx.req.getSession(false) != null) && /*check role*/ true){
			List<Reimbursement> allReimbursements = rs.getReimbursements();

			//parse into JSON
			Gson gson = new Gson();

			String reimbursementJSON = gson.toJson(allReimbursements);


			ctx.result(reimbursementJSON);
			ctx.status(200);
		}
		else {
			ctx.status(403);
		}
	};
	
	public Handler getReimbursementsByUserHandler = (ctx)->{
		
		//check for session, pull the username
		
		//authenticate
		
		//if authenticated
		String username = ctx.pathParam("username");
		
		Gson gson = new Gson();
		
		List<Reimbursement> reimbursements = rs.getReimbursements(username);
		
		String reimbursementJSON = gson.toJson(reimbursements);
		
		ctx.result(reimbursementJSON);
		ctx.status(200);
		
	};
	
}
