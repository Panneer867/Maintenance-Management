var input = document.getElementById("companyName"),
	match = document.getElementById("match");

function display(source, destination) {
	destination.textContent = source;
}

input.onkeyup = function() { display(this.value, match); };