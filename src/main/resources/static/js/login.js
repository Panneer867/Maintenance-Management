
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