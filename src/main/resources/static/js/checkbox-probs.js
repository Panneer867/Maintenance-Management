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

	$('#role-master').click(function() {
		$('#set-role-master').val($('#role-master').is(':checked') ? 115 : "115N");
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

	$('#import-export').click(function() {
		$('#set-import-export').val($('#import-export').is(':checked') ? 121 : "121N");
	});

	$('#device-control').click(function() {
		$('#set-device-control').val($('#device-control').is(':checked') ? 122 : "122N");
	});

	$('#server-backup').click(function() {
		$('#set-server-backup').val($('#server-backup').is(':checked') ? 123 : "123N");
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

	/* Asset Management User Roles */

	$('#asset-management').click(function() {
		$('#set-asset-management').val($('#asset-management').is(':checked') ? 300 : "300N");
	});

	/* Stock Management User Roles */

	$('#stock-available').click(function() {
		$('#set-stock-available').val($('#stock-available').is(':checked') ? 400 : "400N");
	});
	$('#inward-matierals').click(function() {
		$('#set-inward-matierals').val($('#inward-matierals').is(':checked') ? 401 : "401N");
	});
	$('#inward-spares').click(function() {
		$('#set-inward-spares').val($('#inward-spares').is(':checked') ? 402 : "402N");
	});
	$('#inward-tools').click(function() {
		$('#set-inward-tools').val($('#inward-tools').is(':checked') ? 403 : "403N");
	});
	$('#outward-materials').click(function() {
		$('#set-outward-materials').val($('#outward-materials').is(':checked') ? 404 : "404N");
	});
	$('#outward-spares').click(function() {
		$('#set-outward-spares').val($('#outward-spares').is(':checked') ? 405 : "405N");
	});
	$('#outward-tools').click(function() {
		$('#set-outward-tools').val($('#outward-tools').is(':checked') ? 406 : "406N");
	});
	$('#matierals-return').click(function() {
		$('#set-matierals-return').val($('#matierals-return').is(':checked') ? 407 : "407N");
	});
	$('#spares-return').click(function() {
		$('#set-spares-return').val($('#spares-return').is(':checked') ? 408 : "408N");
	});
	$('#tools-return').click(function() {
		$('#set-tools-return').val($('#tools-return').is(':checked') ? 409 : "409N");
	});
	$('#reject-damage-return').click(function() {
		$('#set-reject-damage-return').val($('#reject-damage-return').is(':checked') ? 410 : "410N");
	});
	$('#stock-approvals').click(function() {
		$('#set-stock-approvals').val($('#stock-approvals').is(':checked') ? 411 : "411N");
	});

	/* Work Order User Roles */

	$('#generate-work-order').click(function() {
		$('#set-generate-work-order').val($('#generate-work-order').is(':checked') ? 500 : "500N");
	});
	$('#hold-work-order').click(function() {
		$('#set-hold-work-order').val($('#hold-work-order').is(':checked') ? 501 : "501N");
	});
	$('#cancel-work-order').click(function() {
		$('#set-cancel-work-order').val($('#cancel-work-order').is(':checked') ? 502 : "502N");
	});
	$('#work-estmation').click(function() {
		$('#set-work-estmation').val($('#work-estmation').is(':checked') ? 503 : "503N");
	});

	/* Employee Management User Roles */

	$('#employee-dashBoard').click(function() {
		$('#set-employee-dashBoard').val($('#employee-dashBoard').is(':checked') ? 600 : "600N");
	});
	$('#employee-master').click(function() {
		$('#set-employee-master').val($('#employee-master').is(':checked') ? 601 : "601N");
	});
	$('#employee-attendance').click(function() {
		$('#set-employee-attendance').val($('#employee-attendance').is(':checked') ? 602 : "602N");
	});
	$('#employee-salary').click(function() {
		$('#set-employee-salary').val($('#employee-salary').is(':checked') ? 603 : "603N");
	});
	$('#employee-inspection').click(function() {
		$('#set-employee-inspection').val($('#employee-inspection').is(':checked') ? 604 : "604N");
	});
	$('#employee-leave').click(function() {
		$('#set-employee-leave').val($('#employee-leave').is(':checked') ? 605 : "605N");
	});

	/* Billing Management User Roles */

	$('#cons-master-details').click(function() {
		$('#set-cons-master-details').val($('#cons-master-details').is(':checked') ? 700 : "700N");
	});
	$('#cons-trans-details').click(function() {
		$('#set-cons-trans-details').val($('#cons-trans-details').is(':checked') ? 701 : "701N");
	});
	$('#meter-details').click(function() {
		$('#set-meter-details').val($('#meter-details').is(':checked') ? 702 : "702N");
	});
	/*$('#wards-consumption').click(function() {
		$('#set-wards-consumption').val($('#wards-consumption').is(':checked') ? 703 : "703N");
	});
	$('#dma-payment').click(function() {
		$('#set-dma-payment').val($('#dma-payment').is(':checked') ? 704 : "704N");
	});
	$('#billing-dcb').click(function() {
		$('#set-billing-dcb').val($('#billing-dcb').is(':checked') ? 705 : "705N");
	});*/

	/* Pump Management User Roles */

	$('#pump-dashboard').click(function() {
		$('#set-pump-dashboard').val($('#pump-dashboard').is(':checked') ? 800 : "800N");
	});
	$('#pump-maintenance-index').click(function() {
		$('#set-pump-maintenance-index').val($('#pump-maintenance-index').is(':checked') ? 801 : "801N");
	});
	$('#pump-maintenance-indent').click(function() {
		$('#set-pump-maintenance-indent').val($('#pump-maintenance-indent').is(':checked') ? 802 : "802N");
	});
	$('#pump-maintenance-view').click(function() {
		$('#set-pump-maintenance-view').val($('#pump-maintenance-view').is(':checked') ? 803 : "803N");
	});
	$('#pump-maintenance-update').click(function() {
		$('#set-pump-maintenance-update').val($('#pump-maintenance-update').is(':checked') ? 804 : "804N");
	});
	$('#pump-maintenance-inspection').click(function() {
		$('#set-pump-maintenance-inspection').val($('#pump-maintenance-inspection').is(':checked') ? 805 : "805N");
	});
	$('#pump-maintenance-history').click(function() {
		$('#set-pump-maintenance-history').val($('#pump-maintenance-history').is(':checked') ? 806 : "806N");
	});


	/* Pipe Management User Roles */

	$('#pipe-dashboard').click(function() {
		$('#set-pipe-dashboard').val($('#pipe-dashboard').is(':checked') ? 900 : "900N");
	});
	$('#pipe-maintenance-index').click(function() {
		$('#set-pipe-maintenance-index').val($('#pipe-maintenance-index').is(':checked') ? 901 : "901N");
	});
	$('#pipe-maintenance-indent').click(function() {
		$('#set-pipe-maintenance-indent').val($('#pipe-maintenance-indent').is(':checked') ? 902 : "902N");
	});
	$('#pipe-maintenance-view').click(function() {
		$('#set-pipe-maintenance-view').val($('#pipe-maintenance-view').is(':checked') ? 903 : "903N");
	});
	$('#pipe-maintenance-update').click(function() {
		$('#set-pipe-maintenance-update').val($('#pipe-maintenance-update').is(':checked') ? 904 : "904N");
	});
	$('#pipe-maintenance-inspection').click(function() {
		$('#set-pipe-maintenance-inspection').val($('#pipe-maintenance-inspection').is(':checked') ? 905 : "905N");
	});
	$('#pipe-maintenance-history').click(function() {
		$('#set-pipe-maintenance-history').val($('#pipe-maintenance-history').is(':checked') ? 906 : "906N");
	});

	/* Vehicle Management User Roles */

	$('#vehicle-tracking-mangement').click(function() {
		$('#set-vehicle-tracking-mangement').val($('#vehicle-tracking-mangement').is(':checked') ? 1100 : "1100");
	});
	$('#vehicle-history').click(function() {
		$('#set-vehicle-history').val($('#vehicle-history').is(':checked') ? 1101 : "1101N");
	});
	/*$('#vehicle-fuel-management').click(function() {
		$('#set-vehicle-fuel-management').val($('#vehicle-fuel-management').is(':checked') ? 1102 : "1102N");
	});*/
	$('#vehicle-index').click(function() {
		$('#set-vehicle-index').val($('#vehicle-index').is(':checked') ? 1103 : "1103N");
	});
	$('#vehicle-management-index').click(function() {
		$('#set-vehicle-management-index').val($('#vehicle-management-index').is(':checked') ? 1104 : "1104N");
	});
	$('#vehicle-inspection').click(function() {
		$('#set-vehicle-inspection').val($('#vehicle-inspection').is(':checked') ? 1105 : "1105N");
	});
	$('#vehicle-work-order-view').click(function() {
		$('#set-vehicle-work-order-view').val($('#vehicle-work-order-view').is(':checked') ? 1106 : "1106N");
	});

	/* Leakage Management User Roles */

	$('#leakage-dashboard').click(function() {
		$('#set-leakage-dashboard').val($('#leakage-dashboard').is(':checked') ? 1200 : "1200N");
	});
	$('#leakage-indent').click(function() {
		$('#set-leakage-indent').val($('#leakage-indent').is(':checked') ? 1201 : "1201N");
	});
	$('#leakage-maintenance-update').click(function() {
		$('#set-leakage-maintenance-update').val($('#leakage-maintenance-update').is(':checked') ? 1202 : "1202N");
	});
	$('#leakage-work-inspection').click(function() {
		$('#set-leakage-work-inspection').val($('#leakage-work-inspection').is(':checked') ? 1203 : "1203N");
	});
	$('#leakage-leakage-history').click(function() {
		$('#set-leakage-leakage-history').val($('#leakage-leakage-history').is(':checked') ? 1204 : "1204N");
	});

	/* Meter Management User Roles */

	/*$('#testing-maintenance').click(function() {
		$('#set-testing-maintenance').val($('#testing-maintenance').is(':checked') ? 1300 : "1300N");
	});*/
	$('#meter-replace').click(function() {
		$('#set-meter-replace').val($('#meter-replace').is(':checked') ? 1301 : "1301N");
	});

	/* Hand Pump Management User Roles */

	$('#hand-pump-index').click(function() {
		$('#set-hand-pump-index').val($('#hand-pump-index').is(':checked') ? 1400 : "1400N");
	});
	$('#hand-pump-indent').click(function() {
		$('#set-hand-pump-indent').val($('#hand-pump-indent').is(':checked') ? 1401 : "1401N");
	});
	$('#hand-pump-work-order').click(function() {
		$('#set-hand-pump-work-order').val($('#hand-pump-work-order').is(':checked') ? 1402 : "1402N");
	});
	$('#hand-pump-work-update').click(function() {
		$('#set-hand-pump-work-update').val($('#hand-pump-work-update').is(':checked') ? 1403 : "1403N");
	});
	$('#hand-pump-inspection').click(function() {
		$('#set-hand-pump-inspection').val($('#hand-pump-inspection').is(':checked') ? 1404 : "1404N");
	});
	$('#hand-pump-maintenance-history').click(function() {
		$('#set-hand-pump-maintenance-history').val($('#hand-pump-maintenance-history').is(':checked') ? 1405 : "1405N");
	});

	/* Borewell Management User Roles */

	$('#borewell-index').click(function() {
		$('#set-borewell-index').val($('#borewell-index').is(':checked') ? 1500 : "1500N");
	});
	$('#borewell-indent').click(function() {
		$('#set-borewell-indent').val($('#borewell-indent').is(':checked') ? 1501 : "1501N");
	});
	$('#borewell-work-order').click(function() {
		$('#set-borewell-work-order').val($('#borewell-work-order').is(':checked') ? 1502 : "1502N");
	});
	$('#borewell-work-update').click(function() {
		$('#set-borewell-work-update').val($('#borewell-work-update').is(':checked') ? 1503 : "1503N");
	});
	$('#borewell-inspection').click(function() {
		$('#set-borewell-inspection').val($('#borewell-inspection').is(':checked') ? 1504 : "1504N");
	});
	$('#borewell-maintenance-history').click(function() {
		$('#set-borewell-maintenance-history').val($('#borewell-maintenance-history').is(':checked') ? 1505 : "1505N"); 5
	});

	/* glsroht Management User Roles */

	$('#glsroht-index').click(function() {
		$('#set-glsroht-index').val($('#glsroht-index').is(':checked') ? 1600 : "1600N");
	});
	$('#glsroht-indent').click(function() {
		$('#set-glsroht-indent').val($('#glsroht-indent').is(':checked') ? 1601 : "1601N");
	});
	$('#glsroht-work-order').click(function() {
		$('#set-glsroht-work-order').val($('#glsroht-work-order').is(':checked') ? 1602 : "1602N");
	});
	$('#glsroht-work-update').click(function() {
		$('#set-glsroht-work-update').val($('#glsroht-work-update').is(':checked') ? 1603 : "1603N");
	});
	$('#glsroht-inspection').click(function() {
		$('#set-glsroht-inspection').val($('#glsroht-inspection').is(':checked') ? 1604 : "1604N");
	});
	$('#glsroht-maintenance-history').click(function() {
		$('#set-glsroht-maintenance-history').val($('#glsroht-maintenance-history').is(':checked') ? 1605 : "1605N");
	});

	/* Levels Control User Roles */

	$('#levels-control-index').click(function() {
		$('#set-levels-control-index').val($('#levels-control-index').is(':checked') ? 1700 : "1700N");
	});
	$('#levels-control-indent').click(function() {
		$('#set-levels-control-indent').val($('#levels-control-indent').is(':checked') ? 1701 : "1701N");
	});
	$('#levels-control-work-order').click(function() {
		$('#set-levels-control-work-order').val($('#levels-control-work-order').is(':checked') ? 1702 : "1702N");
	});
	$('#levels-control-work-update').click(function() {
		$('#set-levels-control-work-update').val($('#levels-control-work-update').is(':checked') ? 1703 : "1703N");
	});
	$('#levels-control-inspection').click(function() {
		$('#set-levels-control-inspection').val($('#levels-control-inspection').is(':checked') ? 1704 : "1704N");
	});
	$('#levels-control-maintenance-history').click(function() {
		$('#set-levels-control-maintenance-history').val($('#levels-control-maintenance-history').is(':checked') ? 1705 : "1705N");
	});

	/* Water Storage User Roles */

	/*$('#water-storage-glsr').click(function() {
		$('#set-water-storage-glsr').val($('#water-storage-glsr').is(':checked') ? 1800 : "1800N");
	});
	$('#water-storage-oht').click(function() {
		$('#set-water-storage-oht').val($('#water-storage-oht').is(':checked') ? 1801 : "1801N");
	});*/

	/* Contact Management User Roles */

	$('#contact-management').click(function() {
		$('#set-contact-management').val($('#contact-management').is(':checked') ? 1900 : "1900N");
	});

	/* Approvals User Roles */

	$('#approvals').click(function() {
		$('#set-approvals').val($('#approvals').is(':checked') ? 2100 : "2100N");
	});

	/* MIS Reports User Roles */

	$('#daily-report').click(function() {
		$('#set-daily-report').val($('#daily-report').is(':checked') ? 2200 : "2200N");
	});
	$('#monthly-report').click(function() {
		$('#set-monthly-report').val($('#monthly-report').is(':checked') ? 2201 : "2201N");
	});

	/* Masters User Roles */

	$('#masters').click(function() {
		$('#set-masters').val($('#masters').is(':checked') ? 2300 : "2300N");
	});
});




