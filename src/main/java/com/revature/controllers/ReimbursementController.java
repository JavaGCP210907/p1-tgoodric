package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;


public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	
	
	public Handler getAllReimbursementsHandler = (ctx)->{
		
		//check for session, return 403 forbidden if not a manager
		
		//if(true){
		if((ctx.req.getSession(false) != null)){
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
		
		//check for session
		
		//authenticate
		boolean authenticated = true; //TODO: add authentication logic
		
		if(authenticated) {
		String username = ctx.pathParam("username");
		
		Gson gson = new Gson();
		
		List<Reimbursement> reimbursements = rs.getReimbursements(username);
		//System.out.println(reimbursements);
		String reimbursementJSON = gson.toJson(reimbursements);
		
		ctx.result(reimbursementJSON);
		ctx.status(200);
		}
		else {
			ctx.status(403);
		}
	};
	
	public Handler addReimbursementHandler = (ctx) -> {
		//pull username or id from auth token
		
		//pull the reimbursement data from the webpage as JSON
		
		String reimbursementJson = ctx.body();
		
		Gson gson = new Gson();
		Reimbursement reimbursement = null;
		ReimbursementDTO rdto = gson.fromJson(reimbursementJson, ReimbursementDTO.class);
		
		System.out.println(rdto);
		try {
			System.out.println("in reimbursement handler");
			reimbursement = rdto.getReimbursement();
			System.out.println("rdto parsed");
		}
		catch(NumberFormatException e) {
			ctx.status(400); //bad request
			return;
		}
		catch(Exception e) {
			ctx.status(500); //internal server error, good catch-all code
			System.out.println("error 500");
			e.printStackTrace();
			return;
		}
		ReimbursementDao rDao = new ReimbursementDao();
		System.out.println("entering reimbursement dao");
		rDao.addReimbursement(reimbursement);
		ctx.status(201);
		//rDao.
	};
}
