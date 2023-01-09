$(document).ready(function() {
	document.getElementById('icomname').value = document.getElementById('ccomname').value;
	var input = document.getElementById("ccomname").value,
		match = document.getElementById("match");

	display(input, match);
	function display(source, destination) {
		destination.textContent = source;
	}

alert(document.getElementById('cstate').value);
	document.getElementById('iusername').value = document.getElementById('cusername').value;
	document.getElementById('iemail').value = document.getElementById('cemail').value;
	document.getElementById('iaddress').value = document.getElementById('caddress').value;
	document.getElementById('rstates').value = document.getElementById('cstate').value;
	document.getElementById('cities').value = document.getElementById('ccity').value;
	document.getElementById('ipincode').value = document.getElementById('cpincode').value;
	document.getElementById('imobile').value = document.getElementById('cmobile').value;
	document.getElementById('ifax').value = document.getElementById('cfax').value;
	document.getElementById('iwebsite').value = document.getElementById('cwebsite').value;
	document.getElementById('ienableApp').value = document.getElementById('cenableApp').value;
	
});