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

});

