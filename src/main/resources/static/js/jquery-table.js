$(document).ready(function() {

	$.noConflict(true);
	$.noConflict();
	$.noConflict();
	$('.mytable').DataTable({
		"responsive": true,
		"autoWidth": false,
		"scrollX": true,
	});

	$('.dropify').dropify();

});

