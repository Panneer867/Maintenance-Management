$(document).ready(function() {

	if ($('#get-admin').val() != 0) {
		$('.admin-page').prop('checked', true);
	}
});

function enableAdmin() {
	$('#set-admin').val($('.admin-page').is(':checked') ? 300 : "300N");
}