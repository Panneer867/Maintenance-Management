$("#mis-category").on("change", function() {
	var $button = $(this).val();

	if ($button === "ASSETS") {
		var options = [
			{ id: "op1", value: "ASSETS", text: "Assets Report" },
		];
	}

	if ($button === "STOCKS") {
		var options = [
			{ id: "op1", value: "INWARDMATERIALS", text: "Inward Materials Report" },
			{ id: "op2", value: "INWARDSPARES", text: "Inward Spares Report" },
			{ id: "op3", value: "INWARDTOOLS", text: "Inward Tools Report" },
			{ id: "op4", value: "OUTWARDSTOCKS", text: "Outward Stocks Report" },
			{ id: "op5", value: "STOCKSRETURN", text: "Stocks Return Report" }
		];
	}

	if ($button === "WORKORDERS") {
		var options = [
			{ id: "op1", value: "WAITINGWORKORDERITEMS", text: "Waiting Workorder Items Report" },
			{ id: "op2", value: "WAITINGWORKORDERLABOURS", text: "Waiting Workorder Labours Report" },
			{ id: "op3", value: "WAITINGWORKORDERVEHICLES", text: "Waiting Workorder Vehicles Report" },
		];
	}
	options.forEach(function(option) {
		$('#' + option.id).remove();
		$('<option/>', { id: option.id, value: option.value, text: option.text }).appendTo('#mis-subcategory');
	});



});