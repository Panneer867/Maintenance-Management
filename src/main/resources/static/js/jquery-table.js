$(document).ready(function() {

	$.noConflict(true);
	$.noConflict();
	$.noConflict();
	$('.mytable').DataTable({
		"responsive": true,
		"autoWidth": false,
		"scrollX": true,

	}).columns.adjust();


	$('.dropify').dropify();


});

