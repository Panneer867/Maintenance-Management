$(document).ready(function() {

	$('#admin-page').click(function() {
		alert($('#admin-page').is(':checked'));
		$('#set-admin-page').val($('#admin-page').is(':checked') ? 300 : "300N");
	});

	$('#company-management').click(function() {
		$('#set-company-management').val($('#company-management').is(':checked') ? 301 : "301N");
	});

	$('#create-company').click(function() {
		$('#set-create-company').val($('#create-company').is(':checked') ? 302 : "302N");
	});

	$('#edit-company').click(function() {
		$('#set-edit-company').val($('#edit-company').is(':checked') ? 303 : "303N");
	});

	$('#view-company').click(function() {
		$('#set-view-company').val($('#view-company').is(':checked') ? 304 : "304N");
	});

	$('#delete-company').click(function() {
		$('#set-delete-company').val($('#delete-company').is(':checked') ? 305 : "305N");
	});

});


