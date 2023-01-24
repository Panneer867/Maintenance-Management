$(document).ready(function() {

	var subTotal = parseFloat($(".get-item-sub-total-material").text()) || 0;


	$("#sub-total-material").val((subTotal).toFixed(2));
	$(".set-item-grand-total-material").text(0);

	$("#get-igst-material").prop('disabled', true);
	$("#get-cgst-material").prop('disabled', true);
	$("#get-sgst-material").prop('disabled', true);


	$('#select-gst-material').change(function() {
		if ($(this).val() === 'Taxable') {

			$("#get-igst-material").prop('disabled', false);

		} else {

			$(".set-item-grand-total-material").text(subTotal);
			$("#grand-total-material").val(subTotal);

			$("#get-igst-material").prop('disabled', true);

			$("#get-cgst-material").val(0);
			$("#get-sgst-material").val(0);

			$("#get-imp-cgst-material").val(0);
			$("#get-imp-sgst-material").val(0);

		};
	});

	$(".calculate-material").bind("keyup change", function() {
		var gst = parseFloat($("#get-igst-material").val()) || 0;
		var value = gst / 2;

		if (!isNaN(value) && value !== Infinity) {

			$("#get-cgst-material").val((value).toFixed(2));
			$("#get-sgst-material").val((value).toFixed(2));

			$("#get-imp-cgst-material").val((value).toFixed(2));
			$("#get-imp-sgst-material").val((value).toFixed(2));

		}

		var gstVal = ((subTotal / 100) * parseFloat(gst));
		var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

		$(".set-item-grand-total-material").text((grandTotal).toFixed(2));
		$("#grand-total-material").val((grandTotal).toFixed(2));
	});


});