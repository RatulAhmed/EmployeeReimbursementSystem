var email = localStorage.getItem("email");
var id = localStorage.getItem("employeeId");

fetch(`api/employees/employee/${email}`)
    .then(res => res.json())
    .then(data =>{
        localStorage.setItem("employeeId", data.id);
    })