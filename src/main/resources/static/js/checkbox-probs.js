$(document).ready(function() {

	$('#admin-page').click(function() {
		$('#set-admin-page').val($('#admin-page').is(':checked') ? 100 : "100N");
	});

	$('#company-management').click(function() {
		$('#set-company-management').val($('#company-management').is(':checked') ? 101 : "101N");
	});

	$('#create-company').click(function() {
		$('#set-create-company').val($('#create-company').is(':checked') ? 102 : "102N");
	});

	$('#edit-company').click(function() {
		$('#set-edit-company').val($('#edit-company').is(':checked') ? 103 : "103N");
	});

	$('#view-company').click(function() {
		$('#set-view-company').val($('#view-company').is(':checked') ? 104 : "104N");
	});

	$('#delete-company').click(function() {
		$('#set-delete-company').val($('#delete-company').is(':checked') ? 105 : "105N");
	});

});




