const url = "http://localhost:8192/"; //set url

let params = getCookie("username"); //it wouldn't work with params, don't ask
let paramArray = params.split("|");
const username = paramArray[0];
const role = paramArray[1];

//document.getElementById("")


let pageContents =  document.getElementById("pageContents");    
pageContents.appendChild(document.createElement("br"));
pageContents.appendChild(document.createElement("br"));
//button to generate reimbursements
document.getElementById("viewReimbursementsButton").addEventListener("click", viewReimbursementsFunc);
document.getElementById("submitButton").addEventListener("click", submitFunc);

//function get
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

		extension = document.getElementById("typeSelector").value
		urlBuilder = url + "reimbursements" + extension
		response = await fetch(urlBuilder);
		let table = document.getElementById("reimbursementBody");
		table.innerText = "";
		if (response.status === 204){
			alert("No results of this type")
		}
		else if (response.status === 200){
			let data = await response.json();
			for (let reimbursement of data) {
				let row = document.createElement("tr"); //create a table row

				let cell = document.createElement("th");
				cell.setAttribute("scope", "row");
				cell.setAttribute("id", "userId" + reimbursement.reimbursementId)
				cell.innerHTML = reimbursement.reimbursementId; 
				row.appendChild(cell);

				let cell2 = document.createElement("td"); 
				cell2.innerHTML = reimbursement.amount; 
				row.appendChild(cell2);

				let cell3 = document.createElement("td"); 
				cell3.innerHTML = reimbursement.description; 
				row.appendChild(cell3);

				let cell4 = document.createElement("td"); 
				cell4.innerHTML = reimbursement.submitted; 
				row.appendChild(cell4);

				let cell5 = document.createElement("td"); 
				cell5.innerHTML = reimbursement.status; 
				row.appendChild(cell5);
				
				let cell6 = document.createElement("td"); 
				//cell6.innerHTML = reimbursement.resolved; 
				row.appendChild(cell6);
				
				if (reimbursement.status == 1){
					let approveButton = document.createElement("button");
					approveButton.addEventListener("click", function(){approveRequest(reimbursement.reimbursementId)});
					approveButton.innerText="Approve";
					let rejectButton = document.createElement("button");
					//rejectButton.addEventListener("click", rejection.bind(null));
					rejectButton.addEventListener("click", function(){rejectRequest(reimbursement.reimbursementId)});
					rejectButton.innerText = "Deny";
					cell6.appendChild(approveButton);
					cell6.appendChild(rejectButton);
				}
				else{
					cell6.innerHTML = statusType(reimbursement.status);
				}
				

				table.appendChild(row);

			}
		}
	}
	else{
		extension = document.getElementById("typeSelector").value
		urlBuilder = url + "reimbursements/" + username + extension;
		console.log(urlBuilder)
		response = await fetch(urlBuilder);
		let tableBody = document.getElementById("reimbursementBody");
		tableBody.innerText = "";
		if (response.status === 204){
			alert("No results of this type")
		}
		else if (response.status === 200){
			let data = await response.json();
			
			for (let reimbursement of data) {
				let row = document.createElement("tr"); //create a table row

				let cell = document.createElement("td");
				//cell.setAttribute("class", "idNumber" + reimbursement.reimbursementId) 
				cell.innerHTML = reimbursement.reimbursementId;

				row.appendChild(cell);

				let cell2 = document.createElement("td"); 
				cell2.innerHTML = reimbursement.amount; 
				row.appendChild(cell2);

				let cell3 = document.createElement("td"); 
				cell3.innerHTML = reimbursement.description; 
				row.appendChild(cell3);

				let cell4 = document.createElement("td"); 
				cell4.innerHTML = reimbursement.submitted; 
				row.appendChild(cell4);

				let cell5 = document.createElement("td");
				if(reimbursement.resolved == undefined){
					cell5.innerHTML = "Pending";
				}
				else{
					cell5.innerHTML = reimbursement.resolved;
				}
				row.appendChild(cell5);
				
				let cell6 = document.createElement("td"); 
				cell6.innerHTML = statusType(reimbursement.status); 
				row.appendChild(cell6);
				//row.setAttribute("class", "table table-striped table-dark");
				tableBody.appendChild(row);
				//table.setAttribute("class", "table table-dark table-striped")
			}
		}
	}


}

function statusType(statusId){
	if (statusId == 1){
		return "Pending";
	}
	if (statusId == 2){
		return "Approved";
	}
	return "Denied";
}

async function submitFunc() {
	console.log(username);
	//pull values from the fields
	let amt = document.getElementById("amountField").value;
	//console.log(amt);
	let descr = document.getElementById("descriptionField").value;
	//let user = currentUser
	let type = document.getElementById("expenseType").value;
	let usern = username;
	//console.log(expenseType);
	//console.log("username is" + username)
	let reimbursementRequest = {
		amount:amt,	
		description:descr,
		username:usern,
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

async function approveRequest(requestId){
	let response = await fetch(url +"reimbursements/" + requestId + "/approve ", {
        method: "PUT",
        body: username,
        credentials:"include"
    });
	return response.status;
}

async function rejectRequest(requestId){
	let response = await fetch(url +"reimbursements/" + requestId + "/reject ", {
        method: "PUT",
        body: username,
        credentials:"include"
    });
	return response.status;
}

function createHeaderLabel(text){
	let label = document.createElement("th")
	label.innerText = text;
	return label;
}