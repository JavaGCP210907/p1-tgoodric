package com.revature;

import com.revature.controllers.ReimbursementController;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println("Hello");
		//User user = new User("jDoe", "swordfish", "John", "Doe", "jdoe@email.com", null);
		//System.out.println(user);
		
		//create Javalin instance to expose API endpoints
		Javalin app = Javalin.create().start(8888);
		ReimbursementController rc = new ReimbursementController();
		//
		app.get("/reimbursements", rc.getAllReimbursementsHandler);
		
	}

}