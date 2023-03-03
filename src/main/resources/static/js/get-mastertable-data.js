$(document).ready(function() {	
						
		$("#item-master-category").change(function(){			
			var category = $("#item-master-category").val();	
			var s = '<option value="">--Select--</option>';		
			if (category == "") {
			alert("Please Select Category !!");
			return false;
		}
		$.ajax({
			type: "GET",
			url: "/masters/get/hsnCode/" + category,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				
				$("#item-master-hsnCode").val(jsonobject.hsnCode);
				$("#pump-hsnCode").val(jsonobject.hsnCode);				
			},
		});
		
		$.ajax({
			type: "GET",
			url: "/masters/get/items/" + category,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option>' + jsonobject[i].itemName + "</option>";
				}
				$("#pump-item-list").html(s);
			},
		});
		
	});	
		
				  	
});