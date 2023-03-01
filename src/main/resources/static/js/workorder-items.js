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
		$('#sub-total').val((subtotalVal).toFixed(2));
		$(".gstamt").text(((subtotalVal / 100) * 18).toFixed(2));
		$("#gst-amt").val(((subtotalVal / 100) * 18).toFixed(2));
		var vatVal = ((subtotalVal / 100) * 18).toFixed(2);
		var total = parseFloat(subtotalVal) + parseFloat(vatVal);
		$(".grandtotal").text((total).toFixed(2));
		$("#grand-total").val((total).toFixed(2));
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


