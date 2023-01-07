$(document).ready(function() {
	$("#states").change(function() {
		var stateId = $(this).val()
		var s = '<option value="">Select</option>';
		if (stateId != null) {
			$.ajax({
				type: 'GET',
				url: '/get/city',
				data: { "stateId": stateId },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][1] + '">' + result[i][1] + '</option>';
					}
					$('#cities').html(s);
				}
			});
		}
		$('#cities').html(s);
	});
});