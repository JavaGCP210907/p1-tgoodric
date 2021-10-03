"use strict"
const url = "http://localhost:8192/"; //set url
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
//const username = urlParams.get("username");
console.log
//const manager = urlParams.get("manager");
const params = getCookie("username"); //it wouldn't work with params, don't ask
//const role = getCookie("role");
console.log("params: " + params);
const paramArray = params.split("|");
const username = paramArray[0];
const role = paramArray[1];


let pageContents =  document.getElementById("pageContents");    
let submissionForm = document.createElement("form");
let titleHeader = document.createElement("h2");
titleHeader.innerText = "Initech Reimbursement Management System";
//title
submissionForm.appendChild(titleHeader);
submissionForm.appendChild(document.createElement("br"));
if(role === "employee"){
    //Generate the page contents for non-manager
    //console.log(username);
    let instructions = document.createElement("h3");
    instructions.innerText = "Enter information required for reimbursement request:";
    submissionForm.appendChild(instructions);

    //Amount field
    let amountFieldDiv = createDiv("amount");
    submissionForm.appendChild(amountFieldDiv);

    //description
    let descriptionFieldDiv = createDiv("description");
    submissionForm.appendChild(descriptionFieldDiv);

    let expenseDiv = document.createElement("div")
    expenseDiv.setAttribute("id", "expenseDiv");
    let expenseType = createDropDown();
    expenseType.setAttribute("id", "expenseType");
    expenseDiv.appendChild(expenseType);
    expenseDiv.appendChild(document.createElement("br"));
    expenseDiv.appendChild(document.createElement("br"));
    submissionForm.appendChild(expenseDiv);

    //submit button 
    let submitButton = document.createElement("button");
    submitButton.setAttribute("id", "submitButton");
    //submitButton.setAttribute("text", "Submit");
    submitButton.addEventListener("click", submitFunc);
    submitButton.setAttribute("class", "btn btn-dark col-sm-1");
    submitButton.innerText = "Submit";
    submissionForm.appendChild(submitButton);
    submissionForm.appendChild(document.createElement("br"));
    submissionForm.appendChild(document.createElement("br"));
    pageContents.appendChild(submissionForm);
}

else{
    //let
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

function createTextField(id, placeholder){             
    let element = document.createElement("input");
    element.setAttribute("id", id);                     
    element.setAttribute("placeholder", placeholder)    
    return element;
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

async function submitFunc() {
	console.log(username);
	//pull values from the fields
	let amt = document.getElementById("amount").value;
	console.log(amt);
	let descr = document.getElementById("description").value;
	//let user = currentUser
	let type = document.getElementById("expenseType").value;
	//console.log(expenseType);
	//console.log("username is" + username)
	let responseStatus = await submit()
	//}
	//catch(error){
		//hopefully this will work
	//}
	if ((responseStatus === 400)){
		if(amt !== ""){
			alert("Please enter amount in the format XX.YY");
		}
		return;
	}
	else if (responseStatus == 201){
		alert("Request submitted, awaiting approval");
		//clear request history
		//let reimbursementTable = document.getElementById("reimbursementTable");
		//if (reimbursementTable != null){
			//reimbursementTable.remove();
		//}
		//recreate table
		//reimbursementTable = createReimbursementTable();
	}
	else{
		alert("An error occurred while processing your request")
	}
	//console.log(reimbursementRequest);
}

function getCookie(cookieName) {
	let name = cookieName + "=";
	let ca = document.cookie.split(';');
	for(let i = 0; i < ca.length; i++) {
	  let c = ca[i];
	  while (c.charAt(0) == ' ') {
		c = c.substring(1);
	  }
	  if (c.indexOf(name) == 0) {
		return c.substring(name.length, c.length);
	  }
	}
	return "";
  }
async function submit(amt, descr, usern, type){
	let reimbursementRequest = {
		amount:amt,	
		description:descr,
		user:usern,
        expenseType:type
	};
	//try{
	let response = fetch(url + "addReimbursement/", {
		method:"POST",
		body:JSON.stringify(reimbursementRequest),
		credentials:"include"
	});
	return (await response).status;
}