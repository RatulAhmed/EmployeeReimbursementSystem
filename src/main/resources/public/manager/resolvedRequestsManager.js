let tableData = document.getElementById("data");
let table = document.getElementById("reimbursement-table");
let row ="";
let reimbursements = [];
fetch(`api/reimbursements/resolved`)
    .then(res => res.json())
    .then(data =>{
        reimbursements.push(...data)
        console.log(reimbursements);
        for(let val of reimbursements) {
            row += "<tr></tr><td>"+val.employee_id+"</td><td>"+val.amount+"</td><td>"+val.description+"</td><td>"+val.status+"</td>" +
                "</td><td>"+val.resolvedBy+"</td></tr>";
            tableData.innerHTML = row;
        }
    })