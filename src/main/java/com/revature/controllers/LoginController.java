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
		
		if(ls.login(ldto.getUsername(), ldto.getPassword())) {
			
			
			//Generate JWT
			String jwt = JwtUtil.generate(ldto.getUsername(), ldto.getPassword());
			
			//create user session
			ctx.req.getSession();
			StringBuilder sb = new StringBuilder("Login successful. Welcome, ");
			sb.append(ldto.getUsername());
			sb.append(" \nJWT: ");
			sb.append(jwt);
			ctx.result(sb.toString());
			System.out.println(ctx.body()); //debug line
			//return successful status code
			ctx.status(200);
		}
		else {
			ctx.status(401); //401 unauthorized
			ctx.result("Username or password invalid");
		}
		
		//have the authentication cookie contain the user's id, employee type?
		
		
	};
	
	
}
