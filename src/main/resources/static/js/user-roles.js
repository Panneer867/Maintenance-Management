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

					if (jsonobject.roleMaster != null) {
						$('#set-role-master').val("115");
						$('#role-master').prop('checked', true);
					} else {
						$('#set-role-master').val("115N");
						$('#role-master').prop('checked', false);
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
						$('#set-update-userRoles').val("120");
						$('#update-userRoles').prop('checked', true);
					} else {
						$('#set-update-userRoles').val("120N");
						$('#update-userRoles').prop('checked', false);
					}

					if (jsonobject.importExport != null) {
						$('#set-import-export').val("121");
						$('#import-export').prop('checked', true);
					} else {
						$('#set-import-export').val("121N");
						$('#import-export').prop('checked', false);
					}

					if (jsonobject.deviceControl != null) {
						$('#set-device-control').val("122");
						$('#device-control').prop('checked', true);
					} else {
						$('#set-device-control').val("122N");
						$('#device-control').prop('checked', false);
					}

					if (jsonobject.serverBackup != null) {
						$('#set-server-backup').val("123");
						$('#server-backup').prop('checked', true);
					} else {
						$('#set-server-backup').val("123N");
						$('#server-backup').prop('checked', false);
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

					/* Asset Management User Roles */

					if (jsonobject.assetManagement != null) {
						$('#set-asset-management').val("300");
						$('#asset-management').prop('checked', true);
					} else {
						$('#set-asset-management').val("300N");
						$('#asset-management').prop('checked', false);
					}

					/* Stock Management User Roles */

					if (jsonobject.stockAvailable != null) {
						$('#set-stock-available').val("400");
						$('#stock-available').prop('checked', true);
					} else {
						$('#set-stock-available').val("400N");
						$('#stock-available').prop('checked', false);
					}
					if (jsonobject.inwardMatierals != null) {
						$('#set-inward-matierals').val("401");
						$('#inward-matierals').prop('checked', true);
					} else {
						$('#set-inward-matierals').val("401N");
						$('#inward-matierals').prop('checked', false);
					}
					if (jsonobject.inwardSpares != null) {
						$('#set-inward-spares').val("402");
						$('#inward-spares').prop('checked', true);
					} else {
						$('#set-inward-spares').val("402N");
						$('#inward-spares').prop('checked', false);
					}
					if (jsonobject.inwardTools != null) {
						$('#set-inward-tools').val("403");
						$('#inward-tools').prop('checked', true);
					} else {
						$('#set-inward-tools').val("403N");
						$('#inward-tools').prop('checked', false);
					}
					if (jsonobject.outwardMaterials != null) {
						$('#set-outward-materials').val("404");
						$('#outward-materials').prop('checked', true);
					} else {
						$('#set-outward-materials').val("404N");
						$('#outward-materials').prop('checked', false);
					}
					if (jsonobject.outwardSpares != null) {
						$('#set-outward-spares').val("405");
						$('#outward-spares').prop('checked', true);
					} else {
						$('#set-outward-spares').val("405N");
						$('#outward-spares').prop('checked', false);
					}
					if (jsonobject.outwardTools != null) {
						$('#set-outward-tools').val("406");
						$('#outward-tools').prop('checked', true);
					} else {
						$('#set-outward-tools').val("406N");
						$('#outward-tools').prop('checked', false);
					}
					if (jsonobject.matieralsReturn != null) {
						$('#set-matierals-return').val("407");
						$('#matierals-return').prop('checked', true);
					} else {
						$('#set-matierals-return').val("407N");
						$('#matierals-return').prop('checked', false);
					}
					if (jsonobject.sparesReturn != null) {
						$('#set-spares-return').val("408");
						$('#spares-return').prop('checked', true);
					} else {
						$('#set-spares-return').val("408N");
						$('#spares-return').prop('checked', false);
					}
					if (jsonobject.toolsReturn != null) {
						$('#set-tools-return').val("409");
						$('#tools-return').prop('checked', true);
					} else {
						$('#set-tools-return').val("409N");
						$('#tools-return').prop('checked', false);
					}
					if (jsonobject.rejectDamageReturn != null) {
						$('#set-reject-damage-return').val("410");
						$('#reject-damage-return').prop('checked', true);
					} else {
						$('#set-reject-damage-return').val("410N");
						$('#reject-damage-return').prop('checked', false);
					}
					if (jsonobject.stockApprovals != null) {
						$('#set-stock-approvals').val("411");
						$('#stock-approvals').prop('checked', true);
					} else {
						$('#set-stock-approvals').val("411N");
						$('#stock-approvals').prop('checked', false);
					}

					/* Work Order User Roles */

					if (jsonobject.generateWorkOrder != null) {
						$('#set-generate-work-order').val("500");
						$('#generate-work-order').prop('checked', true);
					} else {
						$('#set-generate-work-order').val("500N");
						$('#generate-work-order').prop('checked', false);
					}
					if (jsonobject.holdWorkOrder != null) {
						$('#set-hold-work-order').val("501");
						$('#hold-work-order').prop('checked', true);
					} else {
						$('#set-hold-work-order').val("501N");
						$('#hold-work-order').prop('checked', false);
					}
					if (jsonobject.cancelWorkOrder != null) {
						$('#set-cancel-work-order').val("502");
						$('#cancel-work-order').prop('checked', true);
					} else {
						$('#set-cancel-work-order').val("502N");
						$('#cancel-work-order').prop('checked', false);
					}
					if (jsonobject.workEstmation != null) {
						$('#set-work-estmation').val("503");
						$('#work-estmation').prop('checked', true);
					} else {
						$('#set-work-estmation').val("503N");
						$('#work-estmation').prop('checked', false);
					}

					/* Employee Management User Roles */

					if (jsonobject.employeeDashBoard != null) {
						$('#set-employee-dashBoard').val("600");
						$('#employee-dashBoard').prop('checked', true);
					} else {
						$('#set-employee-dashBoard').val("600N");
						$('#employee-dashBoard').prop('checked', false);
					}
					if (jsonobject.employeeMaster != null) {
						$('#set-employee-master').val("601");
						$('#employee-master').prop('checked', true);
					} else {
						$('#set-employee-master').val("601N");
						$('#employee-master').prop('checked', false);
					}
					if (jsonobject.attendance != null) {
						$('#set-employee-attendance').val("602");
						$('#employee-attendance').prop('checked', true);
					} else {
						$('#set-employee-attendance').val("602N");
						$('#employee-attendance').prop('checked', false);
					}
					if (jsonobject.salary != null) {
						$('#set-employee-salary').val("603");
						$('#employee-salary').prop('checked', true);
					} else {
						$('#set-employee-salary').val("603N");
						$('#employee-salary').prop('checked', false);
					}
					if (jsonobject.inspection != null) {
						$('#set-employee-inspection').val("604");
						$('#employee-inspection').prop('checked', true);
					} else {
						$('#set-employee-inspection').val("604N");
						$('#employee-inspection').prop('checked', false);
					}
					if (jsonobject.leave != null) {
						$('#set-employee-leave').val("605");
						$('#employee-leave').prop('checked', true);
					} else {
						$('#set-employee-leave').val("605N");
						$('#employee-leave').prop('checked', false);
					}

					/* Billing Management User Roles */

					if (jsonobject.consMasterDetails != null) {
						$('#set-cons-master-details').val("700");
						$('#cons-master-details').prop('checked', true);
					} else {
						$('#set-cons-master-details').val("700N");
						$('#cons-master-details').prop('checked', false);
					}
					if (jsonobject.consTransDetails != null) {
						$('#set-cons-trans-details').val("701");
						$('#cons-trans-details').prop('checked', true);
					} else {
						$('#set-cons-trans-details').val("701N");
						$('#cons-trans-details').prop('checked', false);
					}
					if (jsonobject.meterDetails != null) {
						$('#set-meter-details').val("702");
						$('#meter-details').prop('checked', true);
					} else {
						$('#set-meter-details').val("702N");
						$('#meter-details').prop('checked', false);
					}
					/*if (jsonobject.wardsConsumption != null) {
						$('#set-wards-consumption').val("703");
						$('#wards-consumption').prop('checked', true);
					} else {
						$('#set-wards-consumption').val("703N");
						$('#wards-consumption').prop('checked', false);
					}
					if (jsonobject.dmaPayment != null) {
						$('#set-dma-payment').val("704");
						$('#dma-payment').prop('checked', true);
					} else {
						$('#set-dma-payment').val("704N");
						$('#dma-payment').prop('checked', false);
					}
					if (jsonobject.dcb != null) {
						$('#set-billing-dcb').val("705");
						$('#billing-dcb').prop('checked', true);
					} else {
						$('#set-billing-dcb').val("705N");
						$('#billing-dcb').prop('checked', false);
					}*/


					/* Pump Management User Roles */

					if (jsonobject.pumpDashboard != null) {
						$('#set-pump-dashboard').val("800");
						$('#pump-dashboard').prop('checked', true);
					} else {
						$('#set-pump-dashboard').val("800N");
						$('#pump-dashboard').prop('checked', false);
					}
					if (jsonobject.pumpMaintenance != null) {
						$('#set-pump-maintenance').val("801");
						$('#pump-maintenance').prop('checked', true);
					} else {
						$('#set-pump-maintenance').val("801N");
						$('#pump-maintenance').prop('checked', false);
					}
					if (jsonobject.pumpMaintenanceIndent != null) {
						$('#set-pump-maintenance-indent').val("802");
						$('#pump-maintenance-indent').prop('checked', true);
					} else {
						$('#set-pump-maintenance-indent').val("802N");
						$('#pump-maintenance-indent').prop('checked', false);
					}
					/*if (jsonobject.pumpMaintenanceUpdate != null) {
						$('#set-pump-maintenance-update').val("803");
						$('#pump-maintenance-update').prop('checked', true);
					} else {
						$('#set-pump-maintenance-update').val("803N");
						$('#pump-maintenance-update').prop('checked', false);
					}
					if (jsonobject.pumpMaintenanceInspection != null) {
						$('#set-pump-maintenance-inspection').val("804");
						$('#pump-maintenance-inspection').prop('checked', true);
					} else {
						$('#set-pump-maintenance-inspection').val("804N");
						$('#pump-maintenance-inspection').prop('checked', false);
					}
					if (jsonobject.pumpMaintenanceHistory != null) {
						$('#set-pump-maintenance-history').val("805");
						$('#pump-maintenance-history').prop('checked', true);
					} else {
						$('#set-pump-maintenance-history').val("805N");
						$('#pump-maintenance-history').prop('checked', false);
					}*/

					/* Pipe Management User Roles */

					if (jsonobject.pipeDashboard != null) {
						$('#set-pipe-dashboard').val("900");
						$('#pipe-dashboard').prop('checked', true);
					} else {
						$('#set-pipe-dashboard').val("900N");
						$('#pipe-dashboard').prop('checked', false);
					}
					if (jsonobject.pipeMaintenanceIndent != null) {
						$('#set-pipe-maintenance-indent').val("901");
						$('#pipe-maintenance-indent').prop('checked', true);
					} else {
						$('#set-pipe-maintenance-indent').val("901N");
						$('#pipe-maintenance-indent').prop('checked', false);
					}
					if (jsonobject.pipeMaintenanceUpdate != null) {
						$('#set-pipe-maintenance-update').val("902");
						$('#pipe-maintenance-update').prop('checked', true);
					} else {
						$('#set-pipe-maintenance-update').val("902N");
						$('#pipe-maintenance-update').prop('checked', false);
					}
					if (jsonobject.pipeMaintenanceInspection != null) {
						$('#set-pipe-maintenance-inspection').val("903");
						$('#pipe-maintenance-inspection').prop('checked', true);
					} else {
						$('#set-pipe-maintenance-inspection').val("903N");
						$('#pipe-maintenance-inspection').prop('checked', false);
					}
					if (jsonobject.pipeMaintenanceHistory != null) {
						$('#set-pipe-maintenance-history').val("904");
						$('#pipe-maintenance-history').prop('checked', true);
					} else {
						$('#set-pipe-maintenance-history').val("904N");
						$('#pipe-maintenance-history').prop('checked', false);
					}

					/* Vehicle Management User Roles */


					if (jsonobject.vehicleTrackingMangement != null) {
						$('#set-vehicle-tracking-mangement').val("1100");
						$('#vehicle-tracking-mangement').prop('checked', true);
					} else {
						$('#set-vehicle-tracking-mangement').val("1100N");
						$('#vehicle-tracking-mangement').prop('checked', false);
					}
					if (jsonobject.vehicleHistory != null) {
						$('#set-vehicle-history').val("1101");
						$('#vehicle-history').prop('checked', true);
					} else {
						$('#set-vehicle-history').val("1101N");
						$('#vehicle-history').prop('checked', false);
					}
					if (jsonobject.vehicleFuelManagement != null) {
						$('#set-vehicle-fuel-management').val("1102");
						$('#vehicle-fuel-management').prop('checked', true);
					} else {
						$('#set-vehicle-fuel-management').val("1102N");
						$('#vehicle-fuel-management').prop('checked', false);
					}
					if (jsonobject.vehicleIndex != null) {
						$('#set-vehicle-index').val("1103");
						$('#vehicle-index').prop('checked', true);
					} else {
						$('#set-vehicle-index').val("1103N");
						$('#vehicle-index').prop('checked', false);
					}
					if (jsonobject.vehicleManagementIndex != null) {
						$('#set-vehicle-management-index').val("1104");
						$('#vehicle-management-index').prop('checked', true);
					} else {
						$('#set-vehicle-management-index').val("1104N");
						$('#vehicle-management-index').prop('checked', false);
					}
					if (jsonobject.vehicleInspection != null) {
						$('#set-vehicle-inspection').val("1105");
						$('#vehicle-inspection').prop('checked', true);
					} else {
						$('#set-vehicle-inspection').val("1105N");
						$('#vehicle-inspection').prop('checked', false);
					}
					if (jsonobject.vehicleWorkOrderView != null) {
						$('#set-vehicle-work-order-view').val("1106");
						$('#vehicle-work-order-view').prop('checked', true);
					} else {
						$('#set-vehicle-work-order-view').val("1106N");
						$('#vehicle-work-order-view').prop('checked', false);
					}

					/* Leakage Management User Roles */

					if (jsonobject.leakageDashboard != null) {
						$('#set-leakage-dashboard').val("1200");
						$('#leakage-dashboard').prop('checked', true);
					} else {
						$('#set-leakage-dashboard').val("1200N");
						$('#leakage-dashboard').prop('checked', false);
					}
					if (jsonobject.leakageIndent != null) {
						$('#set-leakage-indent').val("1201");
						$('#leakage-indent').prop('checked', true);
					} else {
						$('#set-leakage-indent').val("1201N");
						$('#leakage-indent').prop('checked', false);
					}
					if (jsonobject.leakageMaintenanceUpdate != null) {
						$('#set-leakage-maintenance-update').val("1202");
						$('#leakage-maintenance-update').prop('checked', true);
					} else {
						$('#set-leakage-maintenance-update').val("1202N");
						$('#leakage-maintenance-update').prop('checked', false);
					}
					if (jsonobject.leakageWorkInspection != null) {
						$('#set-leakage-work-inspection').val("1203");
						$('#leakage-work-inspection').prop('checked', true);
					} else {
						$('#set-leakage-work-inspection').val("1203N");
						$('#leakage-work-inspection').prop('checked', false);
					}
					if (jsonobject.leakageLeakageHistory != null) {
						$('#set-leakage-leakage-history').val("1204");
						$('#leakage-leakage-history').prop('checked', true);
					} else {
						$('#set-leakage-leakage-history').val("1204N");
						$('#leakage-leakage-history').prop('checked', false);
					}

					/* Meter Management User Roles */

					/*if (jsonobject.testingMaintenance != null) {
						$('#set-testing-maintenance').val("1300");
						$('#testing-maintenance').prop('checked', true);
					} else {
						$('#set-testing-maintenance').val("1300N");
						$('#testing-maintenance').prop('checked', false);
					}*/
					if (jsonobject.meterReplace != null) {
						$('#set-meter-replace').val("1301");
						$('#meter-replace').prop('checked', true);
					} else {
						$('#set-meter-replace').val("1301N");
						$('#meter-replace').prop('checked', false);
					}

					/* Hand Pump Management User Roles */

					if (jsonobject.handPumpIndex != null) {
						$('#set-hand-pump-index').val("1400");
						$('#hand-pump-index').prop('checked', true);
					} else {
						$('#set-hand-pump-index').val("1400N");
						$('#hand-pump-index').prop('checked', false);
					}
					if (jsonobject.handPumpIndent != null) {
						$('#set-hand-pump-indent').val("1401");
						$('#hand-pump-indent').prop('checked', true);
					} else {
						$('#set-hand-pump-indent').val("1401N");
						$('#hand-pump-indent').prop('checked', false);
					}
					if (jsonobject.handPumpWorkOrder != null) {
						$('#set-hand-pump-work-order').val("1402");
						$('#hand-pump-work-order').prop('checked', true);
					} else {
						$('#set-hand-pump-work-order').val("1402N");
						$('#hand-pump-work-order').prop('checked', false);
					}
					if (jsonobject.handPumpWorkUpdate != null) {
						$('#set-hand-pump-work-update').val("1403");
						$('#hand-pump-work-update').prop('checked', true);
					} else {
						$('#set-hand-pump-work-update').val("1403N");
						$('#hand-pump-work-update').prop('checked', false);
					}
					if (jsonobject.handPumpHandPumpInspection != null) {
						$('#set-hand-pump-inspection').val("1404");
						$('#hand-pump-inspection').prop('checked', true);
					} else {
						$('#set-hand-pump-inspection').val("1404N");
						$('#hand-pump-inspection').prop('checked', false);
					}
					if (jsonobject.handPumpMaintenanceHistory != null) {
						$('#set-hand-pump-maintenance-history').val("1405");
						$('#hand-pump-maintenance-history').prop('checked', true);
					} else {
						$('#set-hand-pump-maintenance-history').val("1405N");
						$('#hand-pump-maintenance-history').prop('checked', false);
					}

					/* Borewell Management User Roles */

					if (jsonobject.borewellIndex != null) {
						$('#set-borewell-index').val("1500");
						$('#borewell-index').prop('checked', true);
					} else {
						$('#set-borewell-index').val("1500N");
						$('#borewell-index').prop('checked', false);
					}
					if (jsonobject.borewellIndent != null) {
						$('#set-borewell-indent').val("1501");
						$('#borewell-indent').prop('checked', true);
					} else {
						$('#set-borewell-indent').val("1501N");
						$('#borewell-indent').prop('checked', false);
					}
					if (jsonobject.borewellWorkOrder != null) {
						$('#set-borewell-work-order').val("1502");
						$('#borewell-work-order').prop('checked', true);
					} else {
						$('#set-borewell-work-order').val("1502N");
						$('#borewell-work-order').prop('checked', false);
					}
					if (jsonobject.borewellWorkUpdate != null) {
						$('#set-borewell-work-update').val("1503");
						$('#borewell-work-update').prop('checked', true);
					} else {
						$('#set-borewell-work-update').val("1503N");
						$('#borewell-work-update').prop('checked', false);
					}
					if (jsonobject.borewellHandPumpInspection != null) {
						$('#set-borewell-inspection').val("1504");
						$('#borewell-inspection').prop('checked', true);
					} else {
						$('#set-borewell-inspection').val("1504N");
						$('#borewell-inspection').prop('checked', false);
					}
					if (jsonobject.borewellMaintenanceHistory != null) {
						$('#set-borewell-maintenance-history').val("1505");
						$('#borewell-maintenance-history').prop('checked', true);
					} else {
						$('#set-borewell-maintenance-history').val("1505N");
						$('#borewell-maintenance-history').prop('checked', false);
					}

					/* glsroht Management User Roles */

					if (jsonobject.glsrohtIndex != null) {
						$('#set-glsroht-index').val("1600");
						$('#glsroht-index').prop('checked', true);
					} else {
						$('#set-glsroht-index').val("1600N");
						$('#glsroht-index').prop('checked', false);
					}
					if (jsonobject.glsrohtIndent != null) {
						$('#set-glsroht-indent').val("1601");
						$('#glsroht-indent').prop('checked', true);
					} else {
						$('#set-glsroht-indent').val("1601N");
						$('#glsroht-indent').prop('checked', false);
					}
					if (jsonobject.glsrohtWorkOrder != null) {
						$('#set-glsroht-work-order').val("1602");
						$('#glsroht-work-order').prop('checked', true);
					} else {
						$('#set-glsroht-work-order').val("1602N");
						$('#glsroht-work-order').prop('checked', false);
					}
					if (jsonobject.glsrohtWorkUpdate != null) {
						$('#set-glsroht-work-update').val("1603");
						$('#glsroht-work-update').prop('checked', true);
					} else {
						$('#set-glsroht-work-update').val("1603N");
						$('#glsroht-work-update').prop('checked', false);
					}
					if (jsonobject.glsrohtHandPumpInspection != null) {
						$('#set-glsroht-inspection').val("1604");
						$('#glsroht-inspection').prop('checked', true);
					} else {
						$('#set-glsroht-inspection').val("1604N");
						$('#glsroht-inspection').prop('checked', false);
					}
					if (jsonobject.glsrohtMaintenanceHistory != null) {
						$('#set-glsroht-maintenance-history').val("1605");
						$('#glsroht-maintenance-history').prop('checked', true);
					} else {
						$('#set-glsroht-maintenance-history').val("1605N");
						$('#glsroht-maintenance-history').prop('checked', false);
					}

					/* Levels Control User Roles */

					if (jsonobject.levelsControlIndex != null) {
						$('#set-levels-control-index').val("1700");
						$('#levels-control-index').prop('checked', true);
					} else {
						$('#set-levels-control-index').val("1700N");
						$('#levels-control-index').prop('checked', false);
					}
					if (jsonobject.levelsControlIndent != null) {
						$('#set-levels-control-indent').val("1701");
						$('#levels-control-indent').prop('checked', true);
					} else {
						$('#set-levels-control-indent').val("1701N");
						$('#levels-control-indent').prop('checked', false);
					}
					if (jsonobject.levelsControlWorkOrder != null) {
						$('#set-levels-control-work-order').val("1702");
						$('#levels-control-work-order').prop('checked', true);
					} else {
						$('#set-levels-control-work-order').val("1702N");
						$('#levels-control-work-order').prop('checked', false);
					}
					if (jsonobject.levelsControlWorkUpdate != null) {
						$('#set-levels-control-work-update').val("1703");
						$('#levels-control-work-update').prop('checked', true);
					} else {
						$('#set-levels-control-work-update').val("1703N");
						$('#levels-control-work-update').prop('checked', false);
					}
					if (jsonobject.levelsControlInspection != null) {
						$('#set-levels-control-inspection').val("1704");
						$('#levels-control-inspection').prop('checked', true);
					} else {
						$('#set-levels-control-inspection').val("1704N");
						$('#levels-control-inspection').prop('checked', false);
					}
					if (jsonobject.levelsControlHistory != null) {
						$('#set-levels-control-history').val("1705");
						$('#levels-control-history').prop('checked', true);
					} else {
						$('#set-levels-control-history').val("1705N");
						$('#levels-control-history').prop('checked', false);
					}

					/* Water Storage User Roles */

					/*if (jsonobject.waterStorageGlsr != null) {
						$('#set-water-storage-glsr').val("1800");
						$('#water-storage-glsr').prop('checked', true);
					} else {
						$('#set-water-storage-glsr').val("1800N");
						$('#water-storage-glsr').prop('checked', false);
					}
					if (jsonobject.waterStorageoht != null) {
						$('#set-water-storage-oht').val("1801");
						$('#water-storage-oht').prop('checked', true);
					} else {
						$('#set-water-storage-oht').val("1801N");
						$('#water-storage-oht').prop('checked', false);
					}*/

					/* Contact Management User Roles */

					if (jsonobject.contactManagement != null) {
						$('#set-contact-management').val("1900");
						$('#contact-management').prop('checked', true);
					} else {
						$('#set-contact-management').val("1900N");
						$('#contact-management').prop('checked', false);
					}

					/* Approvals User Roles */

					if (jsonobject.approvals != null) {
						$('#set-approvals').val("2100");
						$('#approvals').prop('checked', true);
					} else {
						$('#set-approvals').val("2100N");
						$('#approvals').prop('checked', false);
					}

					/* MIS Reports User Roles */

					if (jsonobject.dailyReport != null) {
						$('#set-daily-report').val("2200");
						$('#daily-report').prop('checked', true);
					} else {
						$('#set-daily-report').val("2200N");
						$('#daily-report').prop('checked', false);
					}
					if (jsonobject.monthlyReport != null) {
						$('#set-monthly-report').val("2201");
						$('#monthly-report').prop('checked', true);
					} else {
						$('#set-monthly-report').val("2201N");
						$('#monthly-report').prop('checked', false);
					}

					/* Masters User Roles */

					if (jsonobject.masters != null) {
						$('#set-masters').val("2300");
						$('#masters').prop('checked', true);
					} else {
						$('#set-masters').val("2300N");
						$('#masters').prop('checked', false);
					}

				}
			});
		}

	});

});