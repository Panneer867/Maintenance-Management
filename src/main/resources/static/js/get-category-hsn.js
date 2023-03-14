$(document).ready(function() {

	$("#itemNameId").change(function() {

		var itemId = $(this).val();

		if (itemId != null) {
			$.ajax({
				type: 'GET',
				url: '/get/item/details/' + itemId,
				success: function(data) {

					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);

					$('#get-hsncode').val(jsonobject.hsnCode);
					$('#get-hsn').val(jsonobject.hsnCode);

					$('#get-category').val(jsonobject.categoryName);
					$('#get-categoryName').val(jsonobject.categoryName);

				}
			});
		}

	});

});