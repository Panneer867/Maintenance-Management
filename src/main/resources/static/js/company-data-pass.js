$(document).ready(function() {

	document.getElementById('icomid').value = document.getElementById('ccomid').value;
	document.getElementById('companyName').value = document.getElementById('ccomname').value;
	var input = document.getElementById("ccomname").value,
		match = document.getElementById("match");

	display(input, match);
	function display(source, destination) {
		destination.textContent = source;
	}

	document.getElementById('iusername').value = document.getElementById('cusername').value;
	document.getElementById('iemail').value = document.getElementById('cemail').value;
	document.getElementById('iaddress').value = document.getElementById('caddress').value;
	document.getElementById('istate').value = document.getElementById('cstate').value;
	document.getElementById('icity').value = document.getElementById('ccity').value;
	document.getElementById('ipincode').value = document.getElementById('cpincode').value;
	document.getElementById('imobile').value = document.getElementById('cmobile').value;
	document.getElementById('ifax').value = document.getElementById('cfax').value;
	document.getElementById('iwebsite').value = document.getElementById('cwebsite').value;
	document.getElementById('iabout').value = document.getElementById('cabout').value;

	if (document.getElementById('cenableApp').value === 'on') {
		$('#ienableApp').prop('checked', true);
	}


	document.getElementById('inoofbranch').value = document.getElementById('cnoofbranch').value;


	$("#click-logo").click(function() {
		document.getElementById('setId').value = document.getElementById('ccomid').value;
	});

});