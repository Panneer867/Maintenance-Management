
$("#return-quantity").on("input", function() {
	var returnQuantity = parseInt($(this).val());
	var orderQuantity = parseInt($('#sr-orderQuantity').val());

	if (returnQuantity <= 0) {
		returnQuantity = 1;
		$("#return-quantity").val(returnQuantity);
	}

	if (returnQuantity > orderQuantity) {
		alert("The return quantity is out of range for the ordered quantity.");
		returnQuantity = 1;
		$("#return-quantity").val(returnQuantity);
	}
});

$('#mySelect2').on('select2:select', function() {
	var stockOrderNo = $(this).val();
	if (stockOrderNo != null) {

		$.ajax({
			type: 'GET',
			url: '/stocks/return/items/' + stockOrderNo,
			success: function(data) {
				try {
					var result = JSON.parse(data);
					var s = '<option value="">Select</option>';
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

		$.ajax({
			type: 'GET',
			url: '/stocks/return/stockorder/' + stockOrderNo,
			success: function(data) {
				try {
					$('#igst-sr, #sr-igst').val(data.igst);

					$('#sgst-sr').val(data.sgst);

					$('#cgst-sr').val(data.cgst);

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

	var stockOrderNo = $('#mySelect2').val();


	if (itemId != null) {

		$.ajax({
			type: 'GET',
			url: '/stocks/return/items/details/' + itemId + '/' + stockOrderNo,
			success: function(data) {
				try {

					switch (data.stockType) {
						case 'ML':
							var type = 'Material';
							break;
						case 'SP':
							var type = 'Spare';
							break;
						case 'TE':
							var type = 'Tool/Equipment';
							break;
						default:
							var type = '';
					}

					$('#sr-stockType').val(type);

					$('#stockType-sr').val(data.stockType);

					$('#sr-category, #category-sr').val(data.category);

					$('#sr-itemName, #itemName-sr').val(data.itemName);

					$('#sr-unitOfMeasure, #unitOfMeasure-sr').val(data.unitOfMeasure);

					$('#sr-mrpRate, #mrpRate-sr').val(data.mrpRate);

					$('#sr-orderQuantity, #orderQuantity-sr').val(data.finalQuantity);

					$('#sr-orderTotalCost, #orderTotalCost-sr').val(data.totalCost);
					
					$('#sr-complNo, #complNo-sr').val(data.complNo);
					
					$('#sr-indentNo, #indentNo-sr').val(data.indentNo);
					
					$('#sr-department, #department-sr').val(data.departmentName);

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
