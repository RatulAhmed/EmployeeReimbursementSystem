let id = localStorage.getItem("employeeId");
let tableData = document.getElementById("data");
let table = document.getElementById("reimbursement-table");
let row ="";
let reimbursements = [];
fetch(`api/employees/${id}/reimbursements/pending`)
    .then(res => res.json())
    .then(data =>{
        reimbursements.push(...data)

        for(let val of reimbursements) {
            row += "<tr></tr><td>"+val.amount+"</td><td>"+val.description+"</td><td>"+val.status+"</td></tr>";
            tableData.innerHTML = row;
        }
    })