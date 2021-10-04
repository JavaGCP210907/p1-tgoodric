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
		//document.cookie = "params = " + uname + "; path = file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html";
		if(manager){
			paramString += "|manager"
			//document.cookie = "params = " + paramString +"; path = file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html";
		}
		else{
			paramString += "|employee"
			//document.cookie = "params = " + uname +"|employee; path = file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html";
		}
		document.cookie = "username = " + paramString +"; path = file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html";
        //document.getElementById("loginButton").removeEventListener("click", loginFunc);
        document.getElementById("login-row").innerText = "Welcome, " + user.username;
        //alert("successful");
        //window.location.replace("file:///C:/Users/tdgoo/Documents/Revature/Projects/p1javalin4Version/p1core/p1-tgoodric/p1frontend/p1landing.html");
		window.location.replace("http://127.0.0.1:5500/Projects/p1Javalin4Version/p1Core/p1-tgoodric/p1frontend/p1landing.html");

    }
    else{
        document.getElementById("login-row").innerText = "Username or password not found";
    }
}

async function submitFunc() {
	//console.log(username);
	//pull values from the fields
	let amt = document.getElementById("amount").value;
	//console.log(amt);
	let descr = document.getElementById("description").value;
	//let user = currentUser
	let expenseType = document.getElementById("expenseType").value;
	//console.log(expenseType);
	
	let reimbursementRequest = {
		amount:amt,	
		description:descr,
		user:username
	};
	let response = await fetch(url + "addReimbursement", {
		method:"POST",
		body:JSON.stringify(reimbursementRequest),
		credentials:"include"
	});
	if ((response.status === 400)){
		if(amt !== ""){
			alert("Please enter amount in the format XX.YY");
		}
		return;
	}
	else if (response.status == 201){
		alert("Request submitted, awaiting approval");
		//clear request history
		let reimbursementTable = document.getElementById("reimbursementTable");
		if (reimbursementTable != null){
			reimbursementTable.remove();
		}
		//recreate table
		reimbursementTable = createReimbursementTable();
	}
	else{
		alert("An error occurred while processing your request")
	}
}

function createReimbursementTable(username, manager){
	
	//generate table structure
	let tableDiv = document.createElement("div");
	tableDiv.setAttribute("id", "tableDiv");
	
	//generate table
	let table = document.createElement("table");
	table.setAttribute("id", "reimbursementTable");
	table.setAttribute("class", "table table-bordered table-dark");
	
	//generate the head	
	let head = document.createElement("thead");
	head.setAttribute("id", "reimbursementHead");
	let tableHeadLabels = createTableHead(manager);
	head.appendChild(tableHeadLabels)
	
	//generate the body
	let body = document.createElement("tbody")
	body.setAttribute("id", "reimbursementBody");
	
	//bolt everything together
	table.appendChild(head);
	table.appendChild(body);
	tableDiv.appendChild(table);
	
	
	
	//finally done
	return tableDiv;
}

async function getReimbursements(username){
	let response = null;
	if (username === "all"){
		response = await fetch(url + "reimbursements");
	}
	else {
		response = await fetch(url + "reimbursements/" + username);
	}
	
	console.log(response);
	if (response.status === 200){
		let data = await response.json();
		
		for(let reimbursement of data){
			console.log(reimbursement);		
		}
		
	}
	
	return data;
}

function createTextField(id, placeholder){              //freaking Javascript and its nonexistent type system
    let element = document.createElement("input");      //Sloppy design
    element.setAttribute("id", id);                     //not even TypeScript can truly fix the bad decisions
    element.setAttribute("placeholder", placeholder)    //that make up JS    
    return element;
}

function createDropDown(){
	let dropDown = document.createElement("select");
	dropDown.setAttribute("id", "expenseType");
	dropDown.appendChild(addOption("Lodging"));
	dropDown.appendChild(addOption("Travel"));
	dropDown.appendChild(addOption("Food"));
	dropDown.appendChild(addOption("Other"));
	return dropDown;
}

function addOption(text){
	let current = document.createElement("option");
	current.setAttribute("value", text);
	current.innerText = text;
	return current;
}

function createDiv(type){
	let capitalize = type[0].toUpperCase() + type.substring(1);
	let div = document.createElement("div");
	let label = document.createElement("h4");
	label.innerText = capitalize + ":";
    let field = createTextField(type, capitalize);
    div.appendChild(label);
    div.appendChild(field);
    div.appendChild(document.createElement("br"));
    div.appendChild(document.createElement("br"));
    return div;
}

function createTableHead(manager){
	let tRow = document.createElement("tr");
	tRow.appendChild(createHeaderLabel("ID"));
	tRow.appendChild(createHeaderLabel("Amount"));
	tRow.appendChild(createHeaderLabel("Description"));
	tRow.appendChild(createHeaderLabel("Date Submitted"));
	tRow.appendChild(createHeaderLabel("Status"));
	tRow.appendChild(createHeaderLabel("Date Approved/Rejected"));
	if(manager){
		tRow.appendChild(createHeaderLabel("Update Status"));
	}
	return tRow;
}


function createHeaderLabel(text){
	let label = document.createElement("th")
	label.innerText = text;
	return label;
}
//might not need this
async function changeStatus(){
    //verify user is a manager
    return 0;
}

