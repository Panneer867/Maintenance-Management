
$(document).ready(function() {
$('.table .edit-role').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(role) {
			$('#roleIdCopy').val(role.id);
			$('#roleNameCopy').val(role.name);
			$('#roleDescriptionCopy').val(role.description);
		});
		$('#editRole').modal();
	});
});