var email = localStorage.getItem("email");

function getEmail() {
    console.log("testing")
    let element = document.getElementById("email").setAttribute("placeholder", email);
}

