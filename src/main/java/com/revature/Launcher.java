package com.revature;

import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

//import com.google.gson.Gson;

import java.sql.SQLException;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
//import com.revature.daos.UserDao;
//import com.revature.models.LoginDTO;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		SessionHandler sessionHandler = new SessionHandler();
		DefaultSessionCache sessionCache = new DefaultSessionCache(sessionHandler);
		sessionHandler.getSessionCookieConfig().setSecure(true);
		sessionHandler.getSessionCookieConfig().setComment("__SAME_SITE_NONE__");
		//sessionHandler.setSessionCache(sessionCache);
		//create Javalin instance to expose API end points
		Javalin app = Javalin.create(
				config ->{
					config.enableCorsForAllOrigins();
					config.sessionHandler(() -> sessionHandler);				
					}				
				).start(8192);
		LoginController lc = new LoginController();
		ReimbursementController rc = new ReimbursementController();

		app.post("/login", lc.loginHandler); //working for sure
		app.get("/reimbursements", rc.getAllReimbursementsHandler); 
		app.post("/addReimbursement", rc.addReimbursementHandler);
		app.get("/reimbursements/{username}", rc.getReimbursementsByUserHandler);
		app.post("/reimbursements/{username}", rc.addReimbursementForUserHandler);
		app.put("/reimbursements/{reimbursementId}/approve", rc.approveHandler);
		app.put("reimbursements/{reimbursementId}/reject", rc.rejectHandler);
		//app.patch("/approveRequest", handler)
		//app.get("reimbursements/" + , rc.get)
		//app.
		
		//app.get("/reimbursements/<username>", rc.getReimbursementsByUserHandler);
		/*
		UserDao uDao = new UserDao();
		try {
			System.out.println(uDao.getUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}