$(document).ready(function() {

	var subTotal = parseFloat($(".get-item-sub-total-spare").text()) || 0;


	$("#sub-total-spare").val((subTotal).toFixed(2));
	$(".set-item-grand-total-spare").text(0);

	$("#get-igst-spare").prop('disabled', true);
	$("#get-cgst-spare").prop('disabled', true);
	$("#get-sgst-spare").prop('disabled', true);


	$('#select-gst-spare').change(function() {
		if ($(this).val() === 'Taxable') {

			$("#get-igst-spare").prop('disabled', false);

		} else {

			$(".set-item-grand-total-spare").text(subTotal);
			$("#grand-total-spare").val(subTotal);

			$("#get-igst-spare").prop('disabled', true);

			$("#get-cgst-spare").val(0);
			$("#get-sgst-spare").val(0);

			$("#get-imp-cgst-spare").val(0);
			$("#get-imp-sgst-spare").val(0);

		};
	});

	$(".calculate-spare").bind("keyup change", function() {
		var gst = parseFloat($("#get-igst-spare").val()) || 0;
		var value = gst / 2;

		if (!isNaN(value) && value !== Infinity) {

			$("#get-cgst-spare").val((value).toFixed(2));
			$("#get-sgst-spare").val((value).toFixed(2));

			$("#get-imp-cgst-spare").val((value).toFixed(2));
			$("#get-imp-sgst-spare").val((value).toFixed(2));

		}

		var gstVal = ((subTotal / 100) * parseFloat(gst));
		var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

		$(".set-item-grand-total-spare").text((grandTotal).toFixed(2));
		$("#grand-total-spare").val((grandTotal).toFixed(2));
	});


});