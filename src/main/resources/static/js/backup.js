$(document).ready(function() {

	if (!$('#checkboxId').is(':checked')) {
		$("#timeId").prop("disabled", true);
		$("#setId").prop("disabled", true);
	}


	$('#checkboxId').click(function() {

		if (!$('#checkboxId').is(':checked')) {
			$("#timeId").prop("disabled", true);
			$("#setId").prop("disabled", true);
			$("#nowId").prop("disabled", false);
		} else {
			$("#timeId").prop("disabled", false);
			$("#setId").prop("disabled", false);
			$("#nowId").prop("disabled", true);
		}

	});
});