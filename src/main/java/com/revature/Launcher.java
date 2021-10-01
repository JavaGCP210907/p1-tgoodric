package com.revature;

//import com.google.gson.Gson;

//import java.sql.SQLException;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
//import com.revature.daos.UserDao;
//import com.revature.models.LoginDTO;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println("Hello");
		//User user = new User("jDoe", "swordfish", "John", "Doe", "jdoe@email.com", null);
		//System.out.println(user);
		
		//create Javalin instance to expose API endpoints
		Javalin app = Javalin.create(
				config ->{
					config.enableCorsForAllOrigins();
				}				
				).start(8192);
		LoginController lc = new LoginController();
		ReimbursementController rc = new ReimbursementController();
		/*
		LoginDTO ldto = new LoginDTO("tgoodrich", "password");
		Gson gson = new Gson();
		System.out.println(gson.toJson(ldto));
		*/
		app.post("/login", lc.loginHandler);
		//
		app.get("/reimbursements", rc.getAllReimbursementsHandler);
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