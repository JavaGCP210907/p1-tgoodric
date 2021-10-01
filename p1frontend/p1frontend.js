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

    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials:"include"
    });
    
    console.log(response);

    //check for successful login, process accordingly

    if(response.status === 200 || response === 202){ //200 ok for employees, 202 accepted for mgrs
        document.getElementById("login-row").innerText = "Welcome, " + user.username;
        //alert("successful");
        
        let onLoginDiv = document.getElementById("onLogin");
        let titleDiv = createElement("")
        //TODO: continue creating and adding elements
        let amountField = createTextField("amount", "Amount");
        let descriptionField = createTextField("description", )
    }
    else{
        document.getElementById("login-row").innerText = "Username or password not found";
    }
}

async function displayReimbursements(){

}

function createTextField(id, placeholder){              //freaking Javascript and its nonexistent type system
    let element = Document.createElement("input");      //Sloppy design
    element.setAttribute("id", id);                     //not even TypeScript can truly fix the bad decisions
    element.setAttribute("placeholder", placeholder)    //that make up JS    
    return element;
}

async function changeStatus(){
    //verify user is a manager
    return 0;
}

