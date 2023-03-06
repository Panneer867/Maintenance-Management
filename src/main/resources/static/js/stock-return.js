

$('#mySelect2').on('select2:select', function() {
	var workOrderNo = $(this).val();
	if (workOrderNo != null) {

		$.ajax({
			type: 'GET',
			url: '/stocks/return/items/' + workOrderNo,
			success: function(data) {
				try {
					var result = JSON.parse(data);
					var s = '';
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i].itemId + '">' + result[i].itemId + '</option>';
					}
					$('#mySelect3').html(s);

				} catch (e) {
					console.error('Error parsing response:', e);
				}
			},
			error: function(xhr, status, error) {
				console.error('Error getting data:', error);
			}
		});
	}
});

$('#mySelect3').on('select2:select', function() {
	var itemId = $(this).val();

	var workOrderNo = $('#mySelect2').val();


	if (itemId != null) {

		$.ajax({
			type: 'GET',
			url: '/stocks/return/items/details/' + itemId + '/' + workOrderNo,
			success: function(data) {
				try {
					console.log(data);
					$('#return-category').val(data.category);
					$('#return-categoryName').val(data.category);

					$('#return-hsn').val(data.hsnCode);
					$('#return-hsncode').val(data.hsnCode);

					$('#return-brand').val(data.brand);
					$('#return-brandName').val(data.brand);

					$('#return-unit').val(data.unitOfMeasure);
					$('#return-unitOfMeasure').val(data.unitOfMeasure);

					$('#return-cost').val(data.costRate);
					$('#return-costRate').val(data.costRate);

					$('#return-mrp').val(data.mrpRate);
					$('#return-mrpRate').val(data.mrpRate);

					$('#return-quantity').val(data.quantity);
					$('#return-order-quantity').val(data.quantity);

				} catch (e) {
					console.error('Error parsing response:', e);
				}
			},
			error: function(xhr, status, error) {
				console.error('Error getting data:', error);
			}
		});
	}
});
