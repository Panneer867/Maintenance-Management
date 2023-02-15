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


	$("#view-schedule").on("click", function() {

		$.ajax({
			type: "GET",
			url: "/admin/get/backup/schedule",
			dataType: "json",
			success: function(data) {
				$('#get-schedule').val(data.schedule);
				$('#get-timeOne').val(data.timeOne);
				$('#get-timeTwo').val(data.timeTwo);
				$('#get-timeThree').val(data.timeThree);
				$('#get-drive').val(data.drive);
				$('#get-path').val(data.path);
			}

		});
	});

});

