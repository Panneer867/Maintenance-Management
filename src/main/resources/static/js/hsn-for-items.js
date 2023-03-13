$(document).ready(function() {
	
	$("#itemId").change(function() {
		
		var hsnId = $(this).val();
		
		if (hsnId != null) {
			$.ajax({
				type: 'GET',
				url: '/get/item/hsn/' + hsnId,
				success: function(data) {
					
					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);
					
					$('#get-hsncode').val(jsonobject.hsnCode);
					$('#get-hsn').val(jsonobject.hsnCode);
				
				}
			});
		}
		
	});
	
	
});