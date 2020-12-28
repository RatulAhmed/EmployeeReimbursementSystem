let id;
let tableData = document.getElementById("data");
let table = document.getElementById("reimbursement-table");
let row ="";
let reimbursements = [];
fetch(`api/employees`)
    .then(res => res.json())
    .then(data =>{
        reimbursements.push(...data)
        console.log(reimbursements);
        for(let val of reimbursements) {
            id = val.id;
            localStorage.setItem("customerId", id);
            row += "<tr></tr><td>"+val.id+"</td><td>"+val.email+"</td><td>" +
                "<button id='lastRow' onclick='viewReimbursement()'>View</button></tr>";
            tableData.innerHTML = row;
        }
    })

function viewReimbursement() {
    window.location = '/viewReimbursements/';
}