

$('#mySelect2').on('select2:select', function() {
	var workOrderNo = $(this).val();
		
	if (workOrderNo != null) {
			$.ajax({
				type: 'GET',
				url: '/return/item/details/' + itemId,
				success: function(data) {

					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);

					$('#return-category').val(jsonobject.category);
					$('#return-categoryName').val(jsonobject.category);

					$('#return-hsn').val(jsonobject.hsnCode);
					$('#return-hsncode').val(jsonobject.hsnCode);
					
					$('#return-brand').val(jsonobject.brand);
					$('#return-brandName').val(jsonobject.brand);
					
					$('#return-unit').val(jsonobject.unitOfMeasure);
					$('#return-unitOfMeasure').val(jsonobject.unitOfMeasure);
					
					$('#return-cost').val(jsonobject.costRate);
					$('#return-costRate').val(jsonobject.costRate);
					
					$('#return-mrp').val(jsonobject.mrpRate);
					$('#return-mrpRate').val(jsonobject.mrpRate);
					
					$('#return-quantity').val(jsonobject.quantity);
					$('#return-order-quantity').val(jsonobject.quantity);
										
				}
			});
		}
});

