function validateForm() {
    let x = document.forms["reg_form"]["email"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
}