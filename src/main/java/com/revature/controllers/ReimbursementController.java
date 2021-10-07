package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;


public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();


	public Handler getAllReimbursementsHandler = (ctx)->{

		//check for session, return 403 forbidden if not a manager

		List<Reimbursement> allReimbursements = rs.getReimbursements();
		if(allReimbursements == null) {
			ctx.status(400);
			return;
		}
		else if(allReimbursements.isEmpty()) {
			ctx.status(204);
			return;
		}
		else {
			//parse into JSON
			Gson gson = new Gson();
			String reimbursementJSON = gson.toJson(allReimbursements);
			ctx.result(reimbursementJSON);
			ctx.status(200);
		}
	};

	public Handler getReimbursementsByUserHandler = (ctx)->{

		//check for session

		//authenticate
		boolean authenticated = true; //handled clientside via cookies

		if(authenticated) {
			String username = ctx.pathParam("username");

			Gson gson = new Gson();

			List<Reimbursement> reimbursements = rs.getReimbursements(username);
			//System.out.println(reimbursements);
			String reimbursementJSON = gson.toJson(reimbursements);
			
			if(reimbursements == null) {
				ctx.status(400);
				return;
			}
			else if (reimbursements.isEmpty()) {
				ctx.status(204);
				return;
			}
			else {
				ctx.result(reimbursementJSON);
				ctx.status(200);
			}
		}
		else {
			ctx.status(403);
		}
	};

	public Handler addReimbursementHandler = (ctx) -> {
		//pull username or id from auth token

		//pull the reimbursement data from the webpage as JSON
		System.out.println("in handler");
		String reimbursementJson = ctx.body();
		System.out.println(reimbursementJson);
		Gson gson = new Gson();
		//Reimbursement reimbursement = null;
		ReimbursementDTO rdto = gson.fromJson(reimbursementJson, ReimbursementDTO.class);

		System.out.println(rdto);
		try {
			//System.out.println("in reimbursement handler");
			System.out.println("entering reimbursement service");
			//System.out.println("rdto parsed");
			if(rs.addReimbursement(rdto)) {
				ctx.status(201);
				return;
			};
		}
		catch(NumberFormatException ex) {
			ctx.status(400); //bad request, thrown if non-numeric data passed to amount field
			return;
		}
		catch(Exception exc) {
			ctx.status(500); //internal server error, good catch-all code
			System.out.println("error 500");
			exc.printStackTrace();
			return;
		}
	};
	//not used
	public Handler addReimbursementForUserHandler = (ctx) -> {
		String json = ctx.body();
		System.out.println(json);
	};

	public Handler approveHandler = (ctx) -> {

		System.out.println("in approveHandler");
		int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));
		String resolverId = ctx.body();
		resolverId = "gbansen";		
		if(rs.updateStatus(true, reimbursementId, resolverId)){
			ctx.status(200);
		}
		else {
			ctx.status(400);
		}
	};


	public Handler rejectHandler = (ctx) -> {
		int reimbursementId = 0;
		try {
			reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));
		}
		catch(NumberFormatException e) {
			ctx.status(400);
			return;
		}
		String resolverId = ctx.body();
		resolverId = "gbansen";
		if(rs.updateStatus(false, reimbursementId, resolverId)){
			ctx.status(200);
		}
		else {
			ctx.status(400);
		}
	};


	public Handler getReimbursementsByStatusHandler = (ctx)->{
		int statusId;
		try {
			statusId = Integer.parseInt(ctx.pathParam("statusId"));
		}
		catch (NumberFormatException e) {
			ctx.status(400);
			return;
		}
		List<Reimbursement> reimbursements = rs.getReimbursementsByStatus(statusId);
		//System.out.println(reimbursements);
		
		if (reimbursements == null) {
			ctx.status(400);
			return;
		}
		else if (reimbursements.isEmpty()) {
			ctx.status(204);
			return;
		}
		else {
			Gson gson = new Gson();
			String reimbursementJSON = gson.toJson(reimbursements);
			ctx.result(reimbursementJSON);
		}
	};


	public Handler getReimbursementsByUsernameAndStatusHandler = (ctx)->{
		int statusId;
		String username = ctx.pathParam("username");
		try {
			statusId = Integer.parseInt(ctx.pathParam("statusId"));
		}
		catch (NumberFormatException e) {
			ctx.status(400);
			return;
		}
		List<Reimbursement> reimbursements = rs.getReimbursementsByStatus(username,statusId);
		if (reimbursements == null) {
			ctx.status(500);
			return;
		}
		else if (reimbursements.isEmpty()) {
			ctx.status(204);
		}
		else {
			Gson gson = new Gson();
			String reimbursementJSON = gson.toJson(reimbursements);
			ctx.result(reimbursementJSON);
			ctx.status(200);
		}
	};

}
