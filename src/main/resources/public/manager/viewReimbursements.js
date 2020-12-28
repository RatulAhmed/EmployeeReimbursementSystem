let customerId = localStorage.getItem("customerId");
let tableData = document.getElementById("data");
let table = document.getElementById("reimbursement-table");
let row ="";
let reimbursements = [];
fetch(`api/reimbursements/${customerId}`)
    .then(res => res.json())
    .then(data =>{
        reimbursements.push(...data)
        console.log(reimbursements);
        for(let val of reimbursements) {
            row += "<tr></tr><td>"+val.id+"</td><td>"+val.amount+"</td><td>"+val.description+"</td><tr>";
            tableData.innerHTML = row;
        }
    })

