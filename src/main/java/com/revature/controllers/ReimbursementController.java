package com.revature.controllers;

import io.javalin.http.Handler;

public class ReimbursementController {

	public Handler getAllReimbursementsHandler = (ctx)->{
		
		//check for session
		if(ctx.req.getSession(false) != null) {
			
		};
	};
	
}
