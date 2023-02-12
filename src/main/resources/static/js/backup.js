$(document).ready(function() {


	if (!$('#checkboxId').is(':checked')) {
		$("#timeOneId").prop("disabled", true);
		$("#timeTwoId").prop("disabled", true);
		$("#timeThreeId").prop("disabled", true);
		$("#setId").prop("disabled", true);
	}


	$('#checkboxId').click(function() {

		if (!$('#checkboxId').is(':checked')) {
			$("#timeOneId").prop("disabled", true);
			$("#setId").prop("disabled", true);
			$("#nowId").prop("disabled", false);
			$("#timeTwoId").prop("disabled", true);
			$("#timeThreeId").prop("disabled", true);

		} else {
			$("#timeOneId").prop("disabled", false);
			$("#setId").prop("disabled", false);
			$("#nowId").prop("disabled", true);
			$("#timeTwoId").prop("disabled", false);
			$("#timeThreeId").prop("disabled", false);
		}

	});

});

