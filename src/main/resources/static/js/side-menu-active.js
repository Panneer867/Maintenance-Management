$(document).ready(function() {

	$(function() {
		if (window.location.href.match('/stocks')) {
			$("#expand-stocks").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});


	$(function() {
		if (window.location.href.match('/monitor')) {
			$("#expand-monitor").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/pipe')) {
			$("#expand-pipe").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/hr')) {
			$("#expand-hr").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/vehicle')) {
			$("#expand-vehicle").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/task')) {
			$("#expand-task").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/leakage')) {
			$("#expand-leakage").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/pump')) {
			$("#expand-pump").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/mis')) {
			$("#expand-mis").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/masters')) {
			$("#expand-masters").addClass("li-active");
		}
	});

	$(function() {
		if (window.location.href.match('/billing')) {
			$("#expand-billing").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/employee')) {
			$("#expand-employee").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/workorder')) {
			$("#expand-workorder").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/moniter')) {
			$("#expand-moniter").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/meter')) {
			$("#expand-meter").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/handpump')) {
			$("#expand-handpump").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/borewell')) {
			$("#expand-borewell").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/glsr-oht')) {
			$("#expand-glsr-oht").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});

	$(function() {
		if (window.location.href.match('/levels-control')) {
			$("#expand-level-control").addClass("m-menu__item--open m-menu__item--expanded");
		}
	});


	/**************Stocks top tabs *************** */

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

	/**************end of Stocks top tabs *************** */

	/**************Strat of Task Updated tabs *************** */

	$(function() {
		if (window.location.href.match('/task/je')) {
			$("#task-update-je").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/aee')) {
			$("#task-update-aee").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/ee')) {
			$("#task-update-ee").addClass("m-menu__item--active sub-menu-active");
		}
	});
	$(function() {
		if (window.location.href.match('/task/commissioner')) {
			$("#task-update-com").addClass("m-menu__item--active sub-menu-active");
		}
	});

});



