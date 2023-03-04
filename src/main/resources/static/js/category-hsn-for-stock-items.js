$(document).ready(function() {

	$("#itemId").change(function() {

		var id = $(this).val();

		if (itemId != null) {
			$.ajax({
				type: 'GET',
				url: '/get/item/details/' + id,
				success: function(data) {

					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);

					$('#get-hsncode').val(jsonobject.hsnCode);
					$('#get-hsn').val(jsonobject.hsnCode);

					$('#get-category').val(jsonobject.category);
					$('#get-categoryName').val(jsonobject.category);

				}
			});
		}

	});

});