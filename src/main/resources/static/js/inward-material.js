$(document).ready(function() {

	var subTotal = parseFloat($(".get-item-sub-total").text()) || 0;


	$("#sub-total").val((subTotal).toFixed(2));
	$(".set-item-grand-total").text(0);

	$("#get-igst").prop('disabled', true);
	$("#get-cgst").prop('disabled', true);
	$("#get-sgst").prop('disabled', true);


	$('#select-gst').change(function() {
		if ($(this).val() === 'Taxable') {

			$("#get-igst").prop('disabled', false);

		} else {

			$(".set-item-grand-total").text(subTotal);
			$("#grand-total").val(subTotal);

			$("#get-igst").prop('disabled', true);

			$("#get-cgst").val(0);
			$("#get-sgst").val(0);

			$("#get-imp-cgst").val(0);
			$("#get-imp-sgst").val(0);

		};
	});

	$(".calculate").bind("keyup change", function() {
		var gst = parseFloat($("#get-igst").val()) || 0;
		var value = gst / 2;

		if (!isNaN(value) && value !== Infinity) {

			$("#get-cgst").val((value).toFixed(2));
			$("#get-sgst").val((value).toFixed(2));

			$("#get-imp-cgst").val((value).toFixed(2));
			$("#get-imp-sgst").val((value).toFixed(2));

		}

		var gstVal = ((subTotal / 100) * parseFloat(gst));
		var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

		$(".set-item-grand-total").text((grandTotal).toFixed(2));
		$("#grand-total").val((grandTotal).toFixed(2));
	});


});