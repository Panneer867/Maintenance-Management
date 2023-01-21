$(document).ready(function() {

	$("#roleId-change").change(function() {
		
		var roleId = $(this).val()
		if (roleId != null) {
			$.ajax({
				type: 'GET',
				url: '/get/user/roles/' + roleId,
				success: function(data) {
					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);

					/* Admin User Roles */

					if (jsonobject.adminpage != null) {
						$('#set-admin-page').val("100");
						$('#admin-page').prop('checked', true);
					} else {
						$('#set-admin-page').val("100N");
						$('#admin-page').prop('checked', false);
					}

					if (jsonobject.companyManagement != null) {
						$('#set-company-management').val("101");
						$('#company-management').prop('checked', true);
					} else {
						$('#set-company-management').val("101N");
						$('#company-management').prop('checked', false);
					}

					if (jsonobject.createCompany != null) {
						$('#set-create-company').val("102");
						$('#create-company').prop('checked', true);
					} else {
						$('#set-create-company').val("102N");
						$('#create-company').prop('checked', false);
					}

					if (jsonobject.editCompany != null) {
						$('#set-edit-company').val("103");
						$('#edit-company').prop('checked', true);
					} else {
						$('#set-edit-company').val("103N");
						$('#edit-company').prop('checked', false);
					}
					
					if (jsonobject.viewCompany != null) {
						$('#set-view-company').val("104");
						$('#view-company').prop('checked', true);
					} else {
						$('#set-view-company').val("104N");
						$('#view-company').prop('checked', false);
					}
					
					if (jsonobject.deleteCompany != null) {
						$('#set-delete-company').val("105");
						$('#delete-company').prop('checked', true);
					} else {
						$('#set-delete-company').val("105N");
						$('#delete-company').prop('checked', false);
					}
					
					if (jsonobject.branchManagement != null) {
						$('#set-branch-management').val("106");
						$('#branch-management').prop('checked', true);
					} else {
						$('#set-branch-management').val("106N");
						$('#branch-management').prop('checked', false);
					}
					
					if (jsonobject.createBranch != null) {
						$('#set-create-branch').val("107");
						$('#create-branch').prop('checked', true);
					} else {
						$('#set-create-branch').val("107N");
						$('#create-branch').prop('checked', false);
					}
					
					if (jsonobject.editBranch != null) {
						$('#set-edit-branch').val("108");
						$('#edit-branch').prop('checked', true);
					} else {
						$('#set-edit-branch').val("108N");
						$('#edit-branch').prop('checked', false);
					}
					
					if (jsonobject.viewBranch != null) {
						$('#set-view-branch').val("109");
						$('#view-branch').prop('checked', true);
					} else {
						$('#set-view-branch').val("109N");
						$('#view-branch').prop('checked', false);
					}
					
					if (jsonobject.deleteBranch != null) {
						$('#set-delete-branch').val("110");
						$('#delete-branch').prop('checked', true);
					} else {
						$('#set-delete-branch').val("110N");
						$('#delete-branch').prop('checked', false);
					}
					
					if (jsonobject.userManagement != null) {
						$('#set-user-management').val("111");
						$('#user-management').prop('checked', true);
					} else {
						$('#set-user-management').val("111N");
						$('#user-management').prop('checked', false);
					}
					
					if (jsonobject.createUser != null) {
						$('#set-create-user').val("112");
						$('#create-user').prop('checked', true);
					} else {
						$('#set-create-user').val("112N");
						$('#create-user').prop('checked', false);
					}
					
					if (jsonobject.editUser != null) {
						$('#set-edit-user').val("113");
						$('#edit-user').prop('checked', true);
					} else {
						$('#set-edit-user').val("113N");
						$('#edit-user').prop('checked', false);
					}
					
					if (jsonobject.deleteUser != null) {
						$('#set-delete-user').val("114");
						$('#delete-user').prop('checked', true);
					} else {
						$('#set-delete-user').val("114N");
						$('#delete-user').prop('checked', false);
					}
					
					if (jsonobject.roleManagement != null) {
						$('#set-role-management').val("115");
						$('#role-management').prop('checked', true);
					} else {
						$('#set-role-management').val("115N");
						$('#role-management').prop('checked', false);
					}
					
					if (jsonobject.createRole != null) {
						$('#set-create-role').val("116");
						$('#create-role').prop('checked', true);
					} else {
						$('#set-create-role').val("116N");
						$('#create-role').prop('checked', false);
					}
					
					if (jsonobject.editRole != null) {
						$('#set-edit-role').val("117");
						$('#edit-role').prop('checked', true);
					} else {
						$('#set-edit-role').val("117N");
						$('#edit-role').prop('checked', false);
					}
					
					if (jsonobject.deleteRole != null) {
						$('#set-delete-role').val("118");
						$('#delete-role').prop('checked', true);
					} else {
						$('#set-delete-role').val("118N");
						$('#delete-role').prop('checked', false);
					}
					
					if (jsonobject.userRoles != null) {
						$('#set-view-userRoles').val("119");
						$('#view-userRoles').prop('checked', true);
					} else {
						$('#set-view-userRoles').val("119N");
						$('#view-userRoles').prop('checked', false);
					}
					
					if (jsonobject.updateUserRoles != null) {
						$('#set-branch-management').val("120");
						$('#update-userRoles').prop('checked', true);
					} else {
						$('#set-update-userRoles').val("120N");
						$('#update-userRoles').prop('checked', false);
					}
					
					
					/* Task User Roles */
					
					
					if (jsonobject.taskJe != null) {
						$('#set-task-je').val("200");
						$('#task-je').prop('checked', true);
					} else {
						$('#set-task-je').val("200N");
						$('#task-je').prop('checked', false);
					}
					
					if (jsonobject.taskAee != null) {
						$('#set-task-aee').val("201");
						$('#task-aee').prop('checked', true);
					} else {
						$('#set-task-aee').val("201N");
						$('#task-aee').prop('checked', false);
					}
					
					if (jsonobject.taskEe != null) {
						$('#set-task-ee').val("202");
						$('#task-ee').prop('checked', true);
					} else {
						$('#set-task-ee').val("202N");
						$('#task-ee').prop('checked', false);
					}
					
					if (jsonobject.taskCommissioner != null) {
						$('#set-task-commissioner').val("203");
						$('#task-commissioner').prop('checked', true);
					} else {
						$('#set-task-commissioner').val("203N");
						$('#task-commissioner').prop('checked', false);
					}
					
					if (jsonobject.taskWorkcomplete != null) {
						$('#set-task-workcomplete').val("204");
						$('#task-workcomplete').prop('checked', true);
					} else {
						$('#set-task-workcomplete').val("204N");
						$('#task-workcomplete').prop('checked', false);
					}
					
					if (jsonobject.taskJobcard != null) {
						$('#set-task-jobcard').val("205");
						$('#task-jobcard').prop('checked', true);
					} else {
						$('#set-task-jobcard').val("205N");
						$('#task-jobcard').prop('checked', false);
					}
					
					if (jsonobject.taskComplainthistory != null) {
						$('#set-task-complainthistory').val("206");
						$('#task-complainthistory').prop('checked', true);
					} else {
						$('#set-task-complainthistory').val("206N");
						$('#task-complainthistory').prop('checked', false);
					}
					
					
				}
			});
		}

	});

});