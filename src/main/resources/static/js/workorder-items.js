$(function() {
	calculate();
	$(".button").on("click", function() {

		var $button = $(this);
		var oldQty = $button.parent().parent().find("input").val();
		var quantity = {};
		var $row = $(this).closest("tr");
		quantity.itemId = $row.find(".item-id").text();
		quantity.mrpRate = $row.find('input.price').val();

		$.ajax({
			type: 'POST',
			url: '/stocks/get/quantity/' + quantity.itemId,
			success: function(data) {
				var json = JSON.stringify(data);
				var stockQty = JSON.parse(json);

				if ($button.find('i').hasClass('fa-plus')) {


					var newQty1 = parseFloat(oldQty) + 1;
					if (stockQty >= newQty1) {
						var newQty = parseFloat(oldQty) + 1;
						quantity.qty = newQty;
						var json = JSON.stringify(quantity);
						$.ajax({
							url: '/stocks/outward/item/quantity',
							method: 'POST',
							data: json,
							contentType: "application/json; charset=utf-8",
							success: function() {
							},
							error: function(e) {
								alert('error occured while posting data' + e);
							}
						});


					} else {
						alert('Available stocks is ' + stockQty + ' cant add more than that !');
						newQty = stockQty;
						quantity.qty = stockQty;
						var json = JSON.stringify(quantity);
						$.ajax({
							url: '/stocks/outward/item/quantity',
							method: 'POST',
							data: json,
							contentType: "application/json; charset=utf-8",
							success: function() {
							},
							error: function(e) {
								alert('error occured while posting data' + e);
							}
						});
					}
				} else {
					if (oldQty > 1) {
						var newQty = parseFloat(oldQty) - 1;
						quantity.qty = newQty;
						var json = JSON.stringify(quantity);
						$.ajax({
							url: '/stocks/outward/item/quantity',
							method: 'POST',
							data: json,
							contentType: "application/json; charset=utf-8",
							success: function() {
							},
							error: function(e) {
								alert('error occured while posting data' + e);
							}
						});
					} else {
						newQty = 1;
					}
				}

				$button.parent().parent().find("input").val(newQty);
				calculate();
			}
		});
	});

	function calculate() {
		$(".basket-tbl tr").each(function() {
			var priceVal = $(this).find('input.price').val();
			var qtyVal = $(this).find("input.qty").val();
			var costVal = (priceVal * qtyVal);
			$(this).find('input.cost').val((costVal).toFixed(2));
		});
		var subtotalVal = 0;
		$('.cost').each(function() {
			subtotalVal += parseFloat($(this).val());
		});
		$('.subtotal').text((subtotalVal).toFixed(2));
		$(".gstamt").text(((subtotalVal / 100) * 18).toFixed(2));
		var vatVal = ((subtotalVal / 100) * 18).toFixed(2);
		var total = parseFloat(subtotalVal) + parseFloat(vatVal);
		$(".grandtotal").text((total).toFixed(2));

		var ss = parseFloat($(".subtotal").text()) || 0;

		var gst = parseFloat($("#get-igst-outward").val()) || 0;
		if (gst === 0) {
			$(".set-item-grand-total-outward").text((ss).toFixed(2));
		} else {

			var gstVal = ((ss / 100) * parseFloat(gst));
			var grandTotal = parseFloat(ss) + parseFloat(gstVal);
			$(".set-item-grand-total-outward").text((grandTotal).toFixed(2));
		}
	}
	$(".remove-item").click(function() {
		if (confirm("Do you really want to remove this item?")) {
			var item = {};
			var $row = $(this).closest("tr");
			item.itemId = $row.find(".item-id").text();
			var json = JSON.stringify(item);
			$(this).parent().parent().remove();
			calculate();
			$.ajax({
				url: '/stocks/outward/item/delete',
				method: 'POST',
				data: json,
				contentType: "application/json; charset=utf-8",
				success: function() {
				},
				error: function(e) {
					alert('error occured while deleting data' + e);
				}
			});
		}
	});
});

var subTotal = parseFloat($("#get-item-sub-total-outward").text()) || 0;


$("#sub-total-outward").val((subTotal).toFixed(2));
$(".set-item-grand-total-outward").text(0);

$("#get-igst-outward").prop('disabled', true);
$("#get-cgst-outward").prop('disabled', true);
$("#get-sgst-outward").prop('disabled', true);

$('#select-gst-outward').change(function() {
	if ($(this).val() === 'Taxable') {

		$("#get-igst-outward").prop('disabled', false);

	} else {
		$("#grand-total-outward").val(subTotal);

		$("#get-igst-outward").prop('disabled', true);
		$("#get-igst-outward").val(0);
		$("#get-cgst-outward").val(0);
		$("#get-sgst-outward").val(0);

		$("#get-imp-cgst-outward").val(0);
		$("#get-imp-sgst-outward").val(0);

		var ss = parseFloat($("#get-item-sub-total-outward").text()) || 0;
		$(".set-item-grand-total-outward").text((ss).toFixed(2));
	};
});

$(".calculate-outward").bind("keyup change", function() {
	var gst = parseFloat($("#get-igst-outward").val()) || 0;
	var ss = parseFloat($("#get-item-sub-total-outward").text()) || 0;
	var workorder = parseInt($("#get-workorder-no").val()) || 0;

	var value = gst / 2;
	if (!isNaN(value) && value !== Infinity) {

		$("#get-cgst-outward").val((value).toFixed(2));
		$("#get-sgst-outward").val((value).toFixed(2));

		$("#get-imp-cgst-outward").val((value).toFixed(2));
		$("#get-imp-sgst-outward").val((value).toFixed(2));
	}

	var gstVal = ((ss / 100) * parseFloat(gst));
	var grandTotal = parseFloat(ss) + parseFloat(gstVal);

	$(".set-item-grand-total-outward").text((grandTotal).toFixed(2));
	$("#set-workorder-no").val(workorder);
	$("#get-sub-total-outward").val((ss).toFixed(2));
	$("#get-grand-total-outward").val((grandTotal).toFixed(2));

});



