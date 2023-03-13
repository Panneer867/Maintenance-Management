$(document).ready(function() {


	//Getting SubDivision From Master According To Division
	$("#master-division-list").change(function() {
		var division = $("#master-division-list").val();
		if (division == "") {
			alert("Select Division !!");
			return false;
		}
		var s = '<option value="">--Select--</option>';

		$.ajax({
			type: "GET",
			url: "/masters/get/subdivision/" + division,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option>' + jsonobject[i].subdivision + "</option>";
				}
				$("#master-sub-division-list").html(s);
			},
		});

	});
	
	//Getting Data on Change Of Category Id
	$("#item-master-category").change(function() {
		var categoryId = $("#item-master-category").val();
		var s = '<option value="">--Select--</option>';
		if (categoryId == "") {
			alert("Please Select Category !!");
			return false;
		}
		//Getting Hsn Code
		$.ajax({
			type: "GET",
			url: "/masters/get/hsnCode/" + categoryId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				$("#item-master-hsnCode").val(jsonobject.hsnCode);
				$("#pump-spare-hsnCode").val(jsonobject.hsnCode);
			},
		});

        //Getting List Of Items
		$.ajax({
			type: "GET",
			url: "/masters/get/items/" + categoryId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option value="'+ jsonobject[i].itemMasterId + '">' + jsonobject[i].itemName + "</option>";
				}
				$("#pump-spare-item-list").html(s);
			},
		});

	});
	
	//Getting Data on Change Of Item Id
	$("#pump-spare-item-list").change(function() {
		var itemId = $("#pump-spare-item-list").val();
		if(itemId == ""){
			alert("Select Item Name !!");
			return false;
		}
		//Getting Stock Type 
		$.ajax({
			type: "GET",
			url: "/masters/get/stocktype/" + itemId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				
				$("#pump-spare-stockType").val(jsonobject.stockType);							
			},
		});			
	});
	
	//Get Vehicle Number List According to Vehicle Type List
	$("#master-vehicleType-list").change(function() {
		var vehicleType = $("#master-vehicleType-list").val();
		var s = '<option value="">--Select--</option>';
		if (vehicleType == "") {
			alert("Please Select Vehicle Type !!");
			return false;
		}
		
		 //Getting List Of Vehicle Numbers
		$.ajax({
			type: "GET",
			url: "/masters/get/vehicleNo/" + vehicleType,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option value="'+ jsonobject[i].vehicleDtlsId + '">' + jsonobject[i].vehicleNo + "</option>";
				}
				$("#master-vehicleNos-list").html(s);
			},
		});
		
	});	
	
	//Get Vehicle Details By Vehicle Id
	$("#master-vehicleNos-list").change(function() {
		var vehicleId = $("#master-vehicleNos-list").val();
		if(vehicleId ==""){
			alert("Select Vehicle Number !!")
			return false;
		}
		
		//Getting Stock Type 
		$.ajax({
			type: "GET",
			url: "/masters/get/vehicle/driver/" + vehicleId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				
				$("#pump-vehicle-driverName").val(jsonobject.driverName);
				$("#pump-vehicle-driverPhone").val(jsonobject.driverMob);							
			},
		});	
	});

});