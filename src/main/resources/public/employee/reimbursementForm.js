const thisForm = document.getElementById('reimbursementForm');
const id = localStorage.getItem("employeeId");
thisForm.addEventListener('submit', async function (e) {
    e.preventDefault();
    const formData = new FormData(thisForm).entries();
    const response = await fetch(`/api/employees/${id}/reimbursements`, {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(formData))
    });

    const result = await response.json();
    console.log(result)
});
