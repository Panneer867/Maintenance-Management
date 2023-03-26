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
		if (window.location.href.match('/masters/')) {
			$("#expand-masters").addClass("li-active");
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
	$(function() {
		if (window.location.href.match('/dashboard/contact-management')) {
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
		if (window.location.href.match('/asset/viewAsset')) {
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
		if (window.location.href.match('/approvals/stocks')) {
			$("#approvals-stocks-active").addClass("m-menu__item--active sub-menu-active");
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


});



