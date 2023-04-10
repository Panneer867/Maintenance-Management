$(document).ready(function() {

	$("#return-item-id").change(function() {
		var reurnItemId = $(this).val();
		if (reurnItemId == "") {
			alert("Select Item !!");
			return false;
		}

		var complNo = $("#return-stock-complNo").val();
		if (complNo == "") {
			alert("Click On Click Button !!");
			return false;
		}

		//Getting Item Data From Approved OutWard Stocks
		$.ajax({
			type: "GET",
			url: "/help/get/stockorder/items/" + reurnItemId + "/" + complNo,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				$("#odered-final-quantity").val(jsonobject.finalQuantity);
				$("#return-stock-type").val(jsonobject.stockType);
				$("#return-stock-stockorderNo").val(jsonobject.stockOrderNo);
				$("#return-stock-department").val(jsonobject.departmentName);
				$("#return-stock-itemName").val(jsonobject.itemName);
			},
		});
	});

	$("#add-return-items").click(function() {

		var workOrderNo = $("#return-workorderNo").val();
		if (workOrderNo == "") {
			alert("Click On Click Button  !!");
			return false;
		}
		var complNo = $("#return-stock-complNo").val();
		if (complNo == "") {
			alert("Click On Click Button !!");
			return false;
		}
		var indentNo = $("#return-stock-indentNo").val();
		if (returnItemId == "") {
			alert("Click On Click Button !!");
			return false;
		}
		var returnItemId = $("#return-item-id").val();
		if (returnItemId == "") {
			alert("Select Item Name !!");
			return false;
		}
		var returnQty = $("#return-item-quantity").val();
		if (returnQty == "") {
			alert("Enter Retirn Quantity  !!");
			return false;
		}

		$.ajax({
			type: "post",
			url: "/help/save/add/returnItems",
			data: {
				indentNo: $("#return-stock-indentNo").val(),
				complNo: $("#return-stock-complNo").val(),
				workOrderNo: $("#return-workorderNo").val(),
				orderedQty: $("#odered-final-quantity").val(),
				returnQty: $("#return-item-quantity").val(),
				stockType: $("#return-stock-type").val(),
				stockOrderNo: $("#return-stock-stockorderNo").val(),
				department: $("#return-stock-department").val(),
				itemId: $("#return-item-id").val(),
				itemName: $("#return-stock-itemName").val(),
			},
			success: function(result) {

				$("#odered-final-quantity").val(""),
					$("#return-item-quantity").val(""),
					$("#return-stock-type").val(""),
					$("#return-stock-stockorderNo").val(""),
					$("#return-stock-department").val(""),
					$("#return-item-id").val(""),
					$("#return-stock-itemName").val(""),

					//Show Added Data		
					$.ajax({
						type: "GET",
						url: "/help/get/added/return/items/" + indentNo + "/" + complNo,
						success: function(data) {
							var json = JSON.stringify(data);
							var result = JSON.parse(json);
							var row = '';
							result.forEach(function(items) {
								row += '<tr>';
								row += '<td>' + items.itemName + '</td>';
								row += '<td>' + items.orderedQty + '</td>';
								row += '<td>' + items.returnQty + '</td>';
								row += '<td>' + items.stockType + '</td>';
								row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-return-id="' + items.returnId + '"></i></a></td>';
								row += '</tr>';
							});
							// Insert the generated HTML into the table body
							$('#returnTable tbody').html(row);
						},
					});

				$('table').on('click', '.delete-btn', function(e) {
					e.preventDefault();
					var row = $(this).closest('tr');
					var returnId = $(this).data('return-id');
					$.ajax({
						type: 'DELETE',
						url: '/help/delete/returnItem/' + returnId,
						success: function(result) {
							alert(result);
							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/help/get/added/return/items/" + indentNo + "/" + complNo,
								success: function(data) {
									var json = JSON.stringify(data);
									var result = JSON.parse(json);
									var row = '';
									result.forEach(function(items) {
										row += '<tr>';
										row += '<td>' + items.itemName + '</td>';
										row += '<td>' + items.orderedQty + '</td>';
										row += '<td>' + items.returnQty + '</td>';
										row += '<td>' + items.stockType + '</td>';
										row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-return-id="' + items.returnId + '"></i></a></td>';
										row += '</tr>';
									});
									// Insert the generated HTML into the table body
									$('#returnTable tbody').html(row);
								},
							});


						},
						error: function(err) {

						}
					});

				});

			},
			error: function(err) {
				alert("Something Went Wrong !! Data Not Added" + err);
			}
		});

	});

});