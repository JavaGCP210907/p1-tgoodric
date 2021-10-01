"use strict"
const url = "http://localhost:8192/"; //set url

document.getElementById("loginButton").addEventListener("click", loginFunc);
//document.

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
    console.log(JSON.stringify(user));
    //alert(user);

    //ask Ben about this, something is getting mangled here
    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials:"include"
    });
    
    //window.location.href = "https://localhost:8192/" +
    console.log(response);

    //check for successful login, process accordingly

    if(response.status == 200){
        document.getElementById("login-row").innerText = "Welcome, " + user.username;
        //alert("successful");
    }
    else{
        document.getElementById("login-row").innerText = "Username or password not found";
    }
}


