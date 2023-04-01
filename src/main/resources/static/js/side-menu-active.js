$(document).ready(function() {

	$(function() {
		if (window.location.href.match('/stocks/')) {
			$("#expand-stocks").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/monitor/')) {
			$("#expand-monitor").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/pipe/')) {
			$("#expand-pipe").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/hr/')) {
			$("#expand-hr").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/vehicle/')) {
			$("#expand-vehicle").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/task/')) {
			$("#expand-task").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/leakage/')) {
			$("#expand-leakage").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/pump/')) {
			$("#expand-pump").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/mis/')) {
			$("#expand-mis").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/billing/')) {
			$("#expand-billing").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/employee/')) {
			$("#expand-employee").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/workorder/')) {
			$("#expand-workorder").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/moniter/')) {
			$("#expand-moniter").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/meter/')) {
			$("#expand-meter").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/handpump/')) {
			$("#expand-handpump").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/borewell/')) {
			$("#expand-borewell").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/glsr-oht/')) {
			$("#expand-glsr-oht").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/levels-control/')) {
			$("#expand-level-control").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});


	$(function() {
		if (window.location.href.match('/asset/')) {
			$("#expand-asset-management").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/water-storage/')) {
			$("#expand-water-storage").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/masters/')) {
			$("#expand-masters").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/contact/')) {
			$("#contact-management-active").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/approval')) {
			$("#approval-active").addClass("li-active");
		}
	});
	
	$(function() {
		if (window.location.href.match('/indent-status')) {
			$("#indent-status-active").addClass("li-active");
		}
	});

	/************** Dashboard *************** */

	$(function() {
		if (window.location.href.match('/dashboard/task-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/asset-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/stock-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});


	$(function() {
		if (window.location.href.match('/dashboard/workorder-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/employee-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/billing-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/pump-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/pipe-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/vehicle-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/leakage-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/meter-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/handpump-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/borewell-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/glsroht-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/dashboard/levelcontrol-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});
	$(function() {
		if (window.location.href.match('/dashboard/waterstorage-management')) {
			$("#dashboard-menu").addClass("li-active");
		}
	});
	

	/**************Task Mangement tabs *************** */

	$(function() {
		if (window.location.href.match('/task/dashboard')) {
			$("#task-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/task/je')) {
			$("#task-je-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/aee')) {
			$("#task-aee-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/ee')) {
			$("#task-ee-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/commissioner')) {
			$("#task-com-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/task/work-complete')) {
			$("#work-complete-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/task/job-card')) {
			$("#job-card-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/task/complaint-history')) {
			$("#complaint-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Asset Management *************** */

	$(function() {
		if (window.location.href.match('/asset/dashboard')) {
			$("#asset-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/asset/entry')) {
			$("#asset-entry-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/asset/view') || window.location.href.match('/asset/id/view')) {
			$("#asset-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/**************Stocks Management *************** */

	$(function() {
		if (window.location.href.match('/inward/materials')) {
			$("#inward-material-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/inward/spares')) {
			$("#inward-spare-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/inward/tools')) {
			$("#inward-tools-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	$(function() {
		if (window.location.href.match('/outward/stocks')) {
			$("#approvals-stocks-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	$(function() {
		if (window.location.href.match('/stocks/outward')) {
			$("#outward-stocks").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/stocks/return')) {
			$("#stocks-return").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Workorders *************** */

	$(function() {
		if (window.location.href.match('/workorder/dashboard')) {
			$("#workoder-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	
	$(function() {
		if (window.location.href.match('/workorder/generate')) {
			$("#generate-workorder-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/workorder/approved')) {
			$("#workorder-approved-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/workorder/hold')) {
			$("#hold-workorder-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/workorder/cancel')) {
			$("#cancel-workorder-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Employee Management *************** */

	$(function() {
		if (window.location.href.match('/employee/dashboard')) {
			$("#employee-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/employee/master')) {
			$("#employee-master-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/employee/attendenceTracker')) {
			$("#employee-attendance-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/employee/salary-generate')) {
			$("#employee-salary-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	$(function() {
		if (window.location.href.match('/employee/inspection')) {
			$("#employee-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/employee/leave')) {
			$("#employee-leave-active").addClass("m-menu__item--active sub-menu-active");
		}
	});



	/************** Billing Management *************** */

	$(function() {
		if (window.location.href.match('/billing/dashboard')) {
			$("#billing-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/billing/consumer-master') || window.location.href.match('/billing/get/consumers')) {
			$("#billing-cons-master-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/billing/transactions') || window.location.href.match('/billing/get/transactions')) {
			$("#billing-cons-trans-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/billing/meter-details') || window.location.href.match('/billing/get/meter')) {
			$("#billing-meter-details-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/billing/ward-consumption')) {
			$("#billing-ward-consumption-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/billing/dma-payment')) {
			$("#billing-dma-payment-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/billing/dcb')) {
			$("#billing-dcb-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Pump Management *************** */

	$(function() {
		if (window.location.href.match('/pump/maintenance/dashboard')) {
			$("#pump-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pump/maintenance/index')) {
			$("#pump-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pump/maintenance/indent')) {
			$("#pump-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pump/maintenance/view')) {
			$("#pump-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/pump/maintenance/update')) {
			$("#pump-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/pump/maintenance/inspection')) {
			$("#pump-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/pump/maintenance/history')) {
			$("#pump-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	/************** Pump Management *************** */

	$(function() {
		if (window.location.href.match('/pipe/dashboard')) {
			$("#pipe-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pipe/pipe-index')) {
			$("#pipe-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pipe/maintenance-indent')) {
			$("#pipe-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/pipe/viewwork')) {
			$("#pipe-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	$(function() {
		if (window.location.href.match('/pipe/maintenance-work-update')) {
			$("#pipe-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/pipe/maintenance-inspection')) {
			$("#pipe-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/pipe/maintenance-history')) {
			$("#pipe-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Vehicle Management *************** */

	$(function() {
		if (window.location.href.match('/vehicle/dashboard')) {
			$("#vehicle-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/vehicle/management')) {
			$("#vehicle-tracking-managemnt-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/vehicle/history')) {
			$("#vehicle-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/vehicle/fuel-management')) {
			$("#vehicle-fuel-management-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	$(function() {
		if (window.location.href.match('/vehicle/index')) {
			$("#vehicle-vehicle-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/vehicle/management/index')) {
			$("#vehicle-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/vehicle/inspection')) {
			$("#vehicle-workorder-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	$(function() {
		if (window.location.href.match('/vehicle/work-order')) {
			$("#vehicle-workorder-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	/************** Leakage Management *************** */

	$(function() {
		if (window.location.href.match('/leakage/dashboard')) {
			$("#leakage-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/index')) {
			$("#leakage-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/indent')) {
			$("#leakage-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/view')) {
			$("#leakage-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/update')) {
			$("#leakage-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/inspection')) {
			$("#leakage-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/leakage/history')) {
			$("#leakage-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	/************** Meter Management *************** */

	$(function() {
		if (window.location.href.match('/monitor/meter/dashboard')) {
			$("#meter-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/meter/testing-maintenance')) {
			$("#testing-maintenance-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/meter/replacement')) {
			$("#meter-replace-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	/************** Hand Pump Management *************** */

	$(function() {
		if (window.location.href.match('/monitor/handpump/dashboard')) {
			$("#handpump-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/index')) {
			$("#handpump-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/indent')) {
			$("#handpump-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/view')) {
			$("#handpump-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/work-update')) {
			$("#handpump-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/inspection')) {
			$("#handpump-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/handpump/history')) {
			$("#handpump-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	/************** Borewell Maintenance *************** */

	$(function() {
		if (window.location.href.match('/monitor/borewell/dashboard')) {
			$("#borewell-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/index')) {
			$("#borewell-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/indent')) {
			$("#borewell-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/work-order')) {
			$("#borewell-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/work-update')) {
			$("#borewell-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/inspection')) {
			$("#borewell-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/borewell/history')) {
			$("#borewell-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Borewell Maintenance *************** */

	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/dashboard')) {
			$("#glsroht-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/index')) {
			$("#glsroht-maintenance-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/indent')) {
			$("#glsroht-maintenance-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/work-order')) {
			$("#glsroht-maintenance-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/work-update')) {
			$("#glsroht-maintenance-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/inspection')) {
			$("#glsroht-maintenance-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/glsr-oht/history')) {
			$("#glsroht-maintenance-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	/************** Borewell Maintenance *************** */

	$(function() {
		if (window.location.href.match('/monitor/levels-control/dashboard')) {
			$("#levelcontrol-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/index')) {
			$("#levelcontrol-index-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/indent')) {
			$("#levelcontrol-indent-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/work-order')) {
			$("#levelcontrol-view-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/work-update')) {
			$("#levelcontrol-update-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/inspection')) {
			$("#levelcontrol-inspection-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/levels-control/history')) {
			$("#levelcontrol-history-active").addClass("m-menu__item--active sub-menu-active");
		}
	});


	/************** Water Storage Maintenance *************** */

	$(function() {
		if (window.location.href.match('/monitor/water-storage/dashboard')) {
			$("#water-storage-dashboard-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/water-storage/glsr')) {
			$("#water-storage-glsr-active").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/monitor/water-storage/oht')) {
			$("#water-storage-oht-active").addClass("m-menu__item--active sub-menu-active");
		}
	});

	/************** Water Storage Maintenance *************** */

	$(function() {
		if (window.location.href.match('/mis/daily')) {
			$("#mis-daily-report").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/mis/monthly')) {
			$("#mis-monthly-report").addClass("m-menu__item--active sub-menu-active");
		}
	});


});



