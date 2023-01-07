$('.table #delete').on('click', function(event) {
	event.preventDefault();
	var href = $(this).attr('href');
	$('#delRef').attr('href', href);
	$('#delCompany').modal();
});