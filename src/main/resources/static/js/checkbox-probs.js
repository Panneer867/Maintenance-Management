$(document).ready(function() {

	/* Admin User Roles Checkboxes */

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

	$('#branch-management').click(function() {
		$('#set-branch-management').val($('#branch-management').is(':checked') ? 106 : "106N");
	});

	$('#create-branch').click(function() {
		$('#set-create-branch').val($('#create-branch').is(':checked') ? 107 : "107N");
	});

	$('#edit-branch').click(function() {
		$('#set-edit-branch').val($('#edit-branch').is(':checked') ? 108 : "108N");
	});

	$('#view-branch').click(function() {
		$('#set-view-branch').val($('#view-branch').is(':checked') ? 109 : "109N");
	});

	$('#delete-branch').click(function() {
		$('#set-delete-branch').val($('#delete-branch').is(':checked') ? 110 : "110N");
	});
	$('#user-management').click(function() {
		$('#set-user-management').val($('#user-management').is(':checked') ? 111 : "111N");
	});

	$('#create-user').click(function() {
		$('#set-create-user').val($('#create-user').is(':checked') ? 112 : "112N");
	});

	$('#edit-user').click(function() {
		$('#set-edit-user').val($('#edit-user').is(':checked') ? 113 : "113N");
	});

	$('#delete-user').click(function() {
		$('#set-delete-user').val($('#delete-user').is(':checked') ? 114 : "114N");
	});

	$('#role-management').click(function() {
		$('#set-role-management').val($('#role-management').is(':checked') ? 115 : "115N");
	});
	$('#create-role').click(function() {
		$('#set-create-role').val($('#create-role').is(':checked') ? 116 : "116N");
	});

	$('#edit-role').click(function() {
		$('#set-edit-role').val($('#edit-role').is(':checked') ? 117 : "117N");
	});

	$('#delete-role').click(function() {
		$('#set-delete-role').val($('#delete-role').is(':checked') ? 118 : "118N");
	});

	$('#view-userRoles').click(function() {
		$('#set-view-userRoles').val($('#view-userRoles').is(':checked') ? 119 : "119N");
	});

	$('#update-userRoles').click(function() {
		$('#set-update-userRoles').val($('#update-userRoles').is(':checked') ? 120 : "120N");
	});

	/* Task User Roles Checkboxes */

	$('#task-je').click(function() {
		$('#set-task-je').val($('#task-je').is(':checked') ? 200 : "200N");
	});

	$('#task-aee').click(function() {
		$('#set-task-aee').val($('#task-aee').is(':checked') ? 201 : "201N");
	});

	$('#task-ee').click(function() {
		$('#set-task-ee').val($('#task-ee').is(':checked') ? 202 : "202N");
	});

	$('#task-commissioner').click(function() {
		$('#set-task-commissioner').val($('#task-commissioner').is(':checked') ? 203 : "203N");
	});

	$('#task-workcomplete').click(function() {
		$('#set-task-workcomplete').val($('#task-workcomplete').is(':checked') ? 204 : "204N");
	});

	$('#task-jobcard').click(function() {
		$('#set-task-jobcard').val($('#task-jobcard').is(':checked') ? 205 : "205N");
	});
	
	$('#task-complainthistory').click(function() {
		$('#set-task-complainthistory').val($('#task-complainthistory').is(':checked') ? 206 : "206N");
	});

});




