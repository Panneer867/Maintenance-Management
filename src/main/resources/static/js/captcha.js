let captchaText = document.querySelector('#captcha');
let userText = document.querySelector('#textBox');
let submitButton = document.querySelector('#submit');
let output = document.querySelector('#output');
let refreshButton = document.querySelector('#refresh');

let alphaNums = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
let emptyArr = [];
for (let i = 1; i <= 7; i++) {
	emptyArr.push(alphaNums[Math.floor(Math.random() * alphaNums.length)]);
}
captchaText.innerHTML = emptyArr.join('');

submitButton.addEventListener('click', function() {
	if (userText.value === captchaText.innerHTML) {
		var x = document.getElementsByTagName("form");
		x[0].submit();
	} else {
		output.classList.add("alert-danger");
		output.innerHTML = "Incorrect Captcha, Please try again";
	}
});

submitButton.addEventListener('keyup', function(e) {
	if (e.keyCode === 13) {
		if (userText.value === captchaText.innerHTML) {
			var x = document.getElementsByTagName("form");
			x[0].submit();
		} else {
			output.classList.add("alert-danger");
			output.innerHTML = "Incorrect, Please try again";
		}
	}
});

userText.addEventListener('keyup', function(e) {
	if (e.keyCode === 13) {
		if (userText.value === captchaText.innerHTML) {
			var x = document.getElementsByTagName("form");
			x[0].submit();
		} else {
			output.classList.add("alert-danger");
			output.innerHTML = "Incorrect, Please try again";
		}
	}
});

refreshButton.addEventListener('click', function() {
	userText.value = "";
	let refreshArr = [];
	for (let j = 1; j <= 7; j++) {
		refreshArr.push(alphaNums[Math.floor(Math.random() * alphaNums.length)]);
	}
	captchaText.innerHTML = refreshArr.join('');
	output.innerHTML = "";
});




