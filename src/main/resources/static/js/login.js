//TODO : Its a Completed Code
function formvalid() {
	var vaildpass = document.getElementById("pass").value;

	if (vaildpass.length <= 8 || vaildpass.length >= 20) {
		document.getElementById("vaild-pass").innerHTML = "Minimum 8 characters";
		return false;
	} else {
		document.getElementById("vaild-pass").innerHTML = "";
	}
}

function show() {
	var x = document.getElementById("pass");
	if (x.type === "password") {
		x.type = "text";
		document.getElementById("showimg").src =
			"/images/password-show.png";
	} else {
		x.type = "password";
		document.getElementById("showimg").src =
			"/images/password-hide.png";
	}
}