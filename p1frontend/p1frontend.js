"use strict"
const url = "http://localhost:8192/"; //set url
console.log("Front-end design is my passion")
document.getElementById("loginButton").addEventListener("click", loginFunc);
document.getElementById("submitButton").addEventListener("click", submitFunc);



async function loginFunc(){
    let uname = document.getElementById("usernameField").value;
    //console.log(username);
    let pword = document.getElementById("passwordField").value;
    //console.log(password);
	
    //create user dto
    let user = {
        username:uname,
        password:pword
    };
    //console.log(JSON.stringify(user));
    //alert(user);

    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials:"include"
    });
    
    //console.log(response);

    //check for successful login, process accordingly

    if(response.status === 200 || response.status === 202){ //200 ok for employees, 202 accepted for mgrs
        const manager = (response.status === 202);
		let paramString = uname;
		if(manager){
			paramString += "|manager"
		}
		else{
			paramString += "|employee"
		}
		document.cookie = "username = " + paramString +"; path = file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html";
        document.getElementById("login-row").innerText = "Welcome, " + user.username;
		window.location.replace("http://127.0.0.1:5500/Projects/p1Javalin4Version/p1Core/p1-tgoodric/p1frontend/p1landing.html");

    }
    else{
        document.getElementById("login-row").innerText = "Username or password not found";
    }
}


