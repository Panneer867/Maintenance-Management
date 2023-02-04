$(document).ready(function() {

	var subTotal = parseFloat($(".get-item-sub-total-tool").text()) || 0;


	$("#sub-total-tool").val((subTotal).toFixed(2));
	$(".set-item-grand-total-tool").text(0);

	$("#get-igst-tool").prop('disabled', true);
	$("#get-cgst-tool").prop('disabled', true);
	$("#get-sgst-tool").prop('disabled', true);


	$('#select-gst-tool').change(function() {
		if ($(this).val() === 'Taxable') {

			$("#get-igst-tool").prop('disabled', false);

		} else {

			$(".set-item-grand-total-tool").text(subTotal);
			$("#grand-total-tool").val(subTotal);

			$("#get-igst-tool").prop('disabled', true);
			$("#get-igst-tool").val(0);
			$("#get-cgst-tool").val(0);
			$("#get-sgst-tool").val(0);

			$("#get-imp-cgst-tool").val(0);
			$("#get-imp-sgst-tool").val(0);

		};
	});

	$(".calculate-tool").bind("keyup change", function() {
		var gst = parseFloat($("#get-igst-tool").val()) || 0;
		var value = gst / 2;

		if (!isNaN(value) && value !== Infinity) {

			$("#get-cgst-tool").val((value).toFixed(2));
			$("#get-sgst-tool").val((value).toFixed(2));

			$("#get-imp-cgst-tool").val((value).toFixed(2));
			$("#get-imp-sgst-tool").val((value).toFixed(2));

		}

		var gstVal = ((subTotal / 100) * parseFloat(gst));
		var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

		$(".set-item-grand-total-tool").text((grandTotal).toFixed(2));
		$("#grand-total-tool").val((grandTotal).toFixed(2));
	});


});