const url = "http://localhost:8192/"; //set url

let params = getCookie("username"); //it wouldn't work with params, don't ask
let paramArray = params.split("|");
let username = paramArray[0];
let role = paramArray[1];

//document.getElementById("")


let pageContents =  document.getElementById("pageContents");    
if(role === "employee"){
   
}
else{
	//stuff for managers
}
pageContents.appendChild(document.createElement("br"));
pageContents.appendChild(document.createElement("br"));
//button to generate reimbursements
document.getElementById("viewReimbursementsButton").addEventListener("click", viewReimbursementsFunc)
/* 
async function getReimbursements(){
	let response = null;
	if (role === "manager"){
		await fetch (url + "reimbursements");
	}
	else await fetch(url +)
}
 */

async function viewReimbursementsFunc(){
	let response = null;
	if(role === "manager"){
		response = await fetch(url + "reimbursements/");
		if (response.status === 200){
			let data = await response.json();
			for (let reimbursement of data) {
				let row = document.createElement("tr"); //create a table row

				let cell = document.createElement("td"); 
				cell.innerHTML = reimbursement.reimbursementId; 
				row.appendChild(cell);

				let cell2 = document.createElement("td"); 
				cell2.innerHTML = reimbursement.amount; 
				row.appendChild(cell2);

				let cell3 = document.createElement("td"); 
				cell3.innerHTML = reimbursement.description; 
				row.appendChild(cell3);

				let cell4 = document.createElement("td"); 
				cell4.innerHTML = reimbursement.reimbursementStatus; 
				row.appendChild(cell4);

				let cell5 = document.createElement("td"); 
				cell5.innerHTML = reimbursement.status; 
				row.appendChild(cell5);
				
				let cell6 = document.createElement("td"); 
				//cell6.innerHTML = reimbursement.resolved; 
				row.appendChild(cell6);
				
				let approveButton = document.createElement("button");
				approveButton.addEventListener("click", approveRequest);
				approveButton.innerText="Approve";
				let rejectButton = document.createElement("button");
				rejectButton.addEventListener("click",rejectRequest);
				rejectButton.innerText = "Deny";
				cell6.appendChild(approveButton);
				cell6.appendChild(rejectButton);
				
				let table = document.getElementById("reimbursementTable");
				table.appendChild(row);

			}
		}
	}
	else{
		response = await fetch(url + "reimbursements/" + username);
		if (response.status === 200){
			let data = await response.json();
			for (let reimbursement of data) {
				let row = document.createElement("tr"); //create a table row

				let cell = document.createElement("td"); 
				cell.innerHTML = reimbursement.reimbursementId; 
				row.appendChild(cell);

				let cell2 = document.createElement("td"); 
				cell2.innerHTML = reimbursement.amount; 
				row.appendChild(cell2);

				let cell3 = document.createElement("td"); 
				cell3.innerHTML = reimbursement.description; 
				row.appendChild(cell3);

				let cell4 = document.createElement("td"); 
				cell4.innerHTML = reimbursement.reimbursementStatusFk; 
				row.appendChild(cell4);

				let cell5 = document.createElement("td"); 
				cell5.innerHTML = reimbursement.status; 
				row.appendChild(cell5);
				
				let cell6 = document.createElement("td"); 
				cell6.innerHTML = reimbursement.resolved; 
				row.appendChild(cell6);
				//row.setAttribute("class", "table table-striped table-dark");
				let table = document.getElementById("reimbursementTable");
				table.appendChild(row)
				//table.setAttribute("class", "table table-dark table-striped")
			}
		}
	}


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
	
	//let data = await getReimbursements(username).body.JSON();

	//generate table structure
	let tableDiv = document.createElement("div");
	tableDiv.setAttribute("id", "tableDiv");
	
	//generate table
	let table = document.createElement("table");
	table.setAttribute("id", "reimbursementTable");
	table.setAttribute("class", "table table-striped table-dark");
	
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
	
	//let data = null;
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
	let reimbursementRequest = {
		amount:amt,	
		description:descr,
		user:usern,
        expenseType:type
	};
	//try{
	let response = await fetch(url + "addReimbursement/" /*  + username  */, {
		method: "post", /* 
 		headers: {
    		'Accept': 'application/json',
    		'Content-Type': 'application/json'
		}, */
  		body: JSON.stringify(reimbursementRequest),
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
	}
	else{
		alert("An error occurred while processing your request");
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
/*
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
		//credentials:"include"
	});
	return (await response).status;
}
*/
async function getReimbursements(username){
	let response = null;
	if (username === "all"){
		response = await fetch(url + "reimbursements");
	}
	else {
		response = await fetch(url + "reimbursements/" + username);
	}
	let data = null;
	console.log(response);
	if (response.status === 200){
		data = await response.json();
		
		for(let reimbursement of data){
			console.log(reimbursement);		
		}
		
	}
	
	return data;
}

async function approveRequest(){

}

async function rejectRequest(){

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