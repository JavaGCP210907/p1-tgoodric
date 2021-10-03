package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {

	LoginService ls = new LoginService();
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body(); //pull data from POST to Java String
		
		Gson gson = new Gson();


		LoginDTO ldto = gson.fromJson(body, LoginDTO.class);
		int result = ls.login(ldto.getUsername(), ldto.getPassword());
		if(result != 0) {
			
			
			//Generate JWT
			String jwt = JwtUtil.generate(ldto.getUsername(), ldto.getPassword());
			//return successful status code
			//200 for employees, 202 for managers
			if (result == 1) { //employee
				ctx.status(200);
				ctx.sessionAttribute("role", "employee");
			}
			else if (result == 2) { //manager
				ctx.status(202);
				ctx.sessionAttribute("role", "manager");
			}
			else { //invalid code
				ctx.status(401);
				ctx.result("User is deactivated, consult management");
				return; //exit early if an invalid
			}
			//create user session
			ctx.req.getSession();
			StringBuilder sb = new StringBuilder("Login successful. Welcome, ");
			sb.append(ldto.getUsername());
			sb.append(" \nJWT: ");
			sb.append(jwt);
			ctx.result(sb.toString());
			//System.out.println(ctx.body()); //debug line
			
			
		}
		else {
			ctx.status(401); //401 unauthorized
			ctx.result("Username or password invalid");
		}
	};
	
	
}
