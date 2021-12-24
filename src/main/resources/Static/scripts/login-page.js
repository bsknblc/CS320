/*function validateForm() {
    const loginForm = document.getElementById("login-form");
    const loginButton = document.getElementById("login-form-submit");
    const loginErrorMsg = document.getElementById("login-error-msg");
    if (desc == null || desc == "") {
        alert("Please enter course description!")
        return false;
    }

    if (type== null || type== "0") {
        alert("Please select course type!")
        return false;
    }
    saveCourse();
    return true;
}*/
const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    if (validateUser(username, password)) {
        console.log("ok");
        alert("You have successfully logged in.");
        location.reload();
    } else {
        console.log("no");
        loginErrorMsg.style.opacity = 1;
    }
})