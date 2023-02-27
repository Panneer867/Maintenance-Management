$(document).ready(function() {

	$.noConflict(true);
	$.noConflict();
	$.noConflict();
	$('.mytable').DataTable({
		"responsive": true,
		"autoWidth": false,
	});

	$('.outward-stocks').DataTable({
		"responsive": true,
		"autoWidth": false,
		"bFilter": false,
		"lengthChange": false
	});

	$('.dropify').dropify();

});

