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

					if (jsonobject.adminpage != null) {
						$('#set-admin-page').val("300");
						$('#admin-page').prop('checked', true);
					} else {
						$('#set-admin-page').val("300N");
						$('#admin-page').prop('checked', false);
					}

					if (jsonobject.companyManagement != null) {
						$('#set-company-management').val("301");
						$('#company-management').prop('checked', true);
					} else {
						$('#set-company-management').val("301N");
						$('#company-management').prop('checked', false);
					}

					if (jsonobject.createCompany != null) {
						$('#set-create-company').val("302");
						$('#create-company').prop('checked', true);
					} else {
						$('#set-create-company').val("302N");
						$('#create-company').prop('checked', false);
					}

					if (jsonobject.editCompany != null) {
						$('#set-edit-company').val("303");
						$('#edit-company').prop('checked', true);
					} else {
						$('#set-edit-company').val("303N");
						$('#edit-company').prop('checked', false);
					}
					
					if (jsonobject.viewCompany != null) {
						$('#set-view-company').val("304");
						$('#view-company').prop('checked', true);
					} else {
						$('#set-view-company').val("304N");
						$('#view-company').prop('checked', false);
					}
					
					if (jsonobject.deleteCompany != null) {
						$('#set-delete-company').val("305");
						$('#delete-company').prop('checked', true);
					} else {
						$('#set-delete-company').val("305N");
						$('#delete-company').prop('checked', false);
					}

				}
			});
		}

	});

});