
var workorderNo = $("#get-workorder-no").val();

$("#set-workorder-no").val(workorderNo);
$(".igst-outward").prop('disabled', true);
$(".cgst-outward").prop('disabled', true);
$(".sgst-outward").prop('disabled', true);

$('#select-gst-outward').change(function() {

	if ($(this).val() === 'Taxable') {
		
		$(".igst-outward").prop('disabled', false);
			
	} else {
		
		$(".igst-outward").prop('disabled', true);
		$(".igst-outward").val(0);
		$(".cgst-outward").val(0);
		$(".sgst-outward").val(0);
		$("#get-igst-outward").val(0);
		$("#get-cgst-outward").val(0);
		$("#get-sgst-outward").val(0);
	};
});

$(".igst-outward").bind("keyup change", function() {

	var gst = parseFloat($(".igst-outward").val()) || 0;
	var subtotalNew = parseFloat($(".subtotal").text()) || 0;
	var gstVal = ((subtotalNew / 100) * parseFloat(gst));
	var grandTotal = parseFloat(subtotalNew) + parseFloat(gstVal);
	var value = gst / 2;

	if (!isNaN(value) && value !== Infinity) {

		$(".cgst-outward").val((value).toFixed(2));
		$(".sgst-outward").val((value).toFixed(2));
		$("#get-igst-outward").val((gst).toFixed(2));
		$("#get-cgst-outward").val((value).toFixed(2));
		$("#get-sgst-outward").val((value).toFixed(2));
	}

	$(".subtotal").val((subtotalNew).toFixed(2));
	$(".grand-total").text((grandTotal).toFixed(2));
	$("#sub-total-outward").val((subtotalNew).toFixed(2));
	$("#grand-total-outward").val((grandTotal).toFixed(2));


});


$(function() {
	calculate();
	$(".button").on("click", function() {
		var $button = $(this);
		var oldQty = $button.parent().parent().find("input").val();
		var quantity = {};
		var $row = $(this).closest("tr");
		quantity.itemId = $row.find(".item-id").text();
		quantity.mrpRate = $row.find('input.price').val();
		
		$(".igst-outward").val(0);
		$(".cgst-outward").val(0);
		$(".sgst-outward").val(0);
		$("#get-igst-outward").val(0);
		$("#get-cgst-outward").val(0);
		$("#get-sgst-outward").val(0);

		$.ajax({
			type: 'POST',
			url: '/stocks/get/quantity/' + quantity.itemId,
			success: function(data) {
				var json = JSON.stringify(data);
				var stockQty = JSON.parse(json);
				var workorder = parseInt($("#get-workorder-no").val());
				if ($button.find('i').hasClass('fa-plus')) {
					var newQty1 = parseFloat(oldQty) + 1;
					if (stockQty >= newQty1) {
						var newQty = parseFloat(oldQty) + 1;
						quantity.qty = newQty;
						quantity.workOrderNo = workorder;
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
						quantity.workOrderNo = workorder;
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
						quantity.workOrderNo = workorder;
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

		var subtotalOld = parseFloat($(".subtotal").text()) || 0;
		var gst = parseFloat($(".igst-outward").val()) || 0;

		if (gst === 0) {
			$(".grand-total").text((subtotalOld).toFixed(2));
			$("#grand-total-outward").val((subtotalOld).toFixed(2));
			$("#sub-total-outward").val((subtotalOld).toFixed(2));
		} else {

			var gstVal = ((ss / 100) * parseFloat(gst));
			var grandTotal = parseFloat(subtotalOld) + parseFloat(gstVal);
			$(".grand-total").text((grandTotal).toFixed(2));
			$("#sub-total-outward").val((subtotalOld).toFixed(2));
			$("#grand-total-outward").val((grandTotal).toFixed(2));		
		}
	}
	$(".remove-item").click(function() {
		if (confirm("Do you really want to remove this item?")) {
			var item = {};
			var $row = $(this).closest("tr");
			var workorder = parseInt($("#get-workorder-no").val());
			item.itemId = $row.find(".item-id").text();
			item.workOrderNo = workorder;
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





