function enable() {
	document.getElementById('senableApp').value = $('#ienableApp').is(':checked') ? $('#ienableApp').val() : "off";
}

function deleteId(href) {
	$('#delRef').attr('href', href);
}

function deleteAll(href) {
	$('#deleteAll').attr('href', href);
}

function viewImg(src) {
	$('#viewRef').attr('src', src);
}

function approveWorkorder(src) {
	$('#approveRef').attr('href', src);
}