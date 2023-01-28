$(document).ready(function() {

	var subTotal = parseFloat($(".get-item-sub-total-tools").text()) || 0;


	$("#sub-total-tools").val((subTotal).toFixed(2));
	$(".set-item-grand-total-tools").text(0);

	$("#get-igst-tools").prop('disabled', true);
	$("#get-cgst-tools").prop('disabled', true);
	$("#get-sgst-tools").prop('disabled', true);


	$('#select-gst-tools').change(function() {
		if ($(this).val() === 'Taxable') {

			$("#get-igst-tools").prop('disabled', false);

		} else {

			$(".set-item-grand-total-tools").text(subTotal);
			$("#grand-total-tools").val(subTotal);

			$("#get-igst-tools").prop('disabled', true);
			$("#get-igst-tools").val(0);
			$("#get-cgst-tools").val(0);
			$("#get-sgst-tools").val(0);

			$("#get-imp-cgst-tools").val(0);
			$("#get-imp-sgst-tools").val(0);

		};
	});

	$(".calculate-tools").bind("keyup change", function() {
		var gst = parseFloat($("#get-igst-tools").val()) || 0;
		var value = gst / 2;

		if (!isNaN(value) && value !== Infinity) {

			$("#get-cgst-tools").val((value).toFixed(2));
			$("#get-sgst-tools").val((value).toFixed(2));

			$("#get-imp-cgst-tools").val((value).toFixed(2));
			$("#get-imp-sgst-tools").val((value).toFixed(2));

		}

		var gstVal = ((subTotal / 100) * parseFloat(gst));
		var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

		$(".set-item-grand-total-tools").text((grandTotal).toFixed(2));
		$("#grand-total-tools").val((grandTotal).toFixed(2));
	});


});