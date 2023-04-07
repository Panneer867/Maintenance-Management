$(document).ready(function() {
	
	//Getting HsnCode from Category Master
	$("#item-master-category").change(function() {		
		var categoryId = $(this).val();
		if (categoryId == "") {
			alert("Select Category !!");
			return false;
		}
				//Getting Stock Type 
		$.ajax({
			type: "GET",
			url: "/masters/get/hsnCode/" + categoryId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);				
				$("#item-master-hsnCode").val(jsonobject.hsnCode);				
			},
		});		
		
	});

	//Getting SubDivision From Master According To Division
	$("#master-division-list").change(function() {
		var division = $(this).val();
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

//Getting SubDivision From Master According To Division for Contact management
	$("#master-division-list-contact").change(function() {
		var division = $(this).val();
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

				$("#master-sub-division-list-contact").html(s);
			},
		});

	});
	
	//Getting Distibution Location From Master According To SubDivision
	$("#master-sub-division-list").change(function() {
		var subDivision = $(this).val();
		if (subDivision == "") {
			alert("Select Sub-Division !!");
			return false;
		}
		var s = '<option value="">--Select--</option>';

		$.ajax({
			type: "GET",
			url: "/masters/get/distribution/location/" + subDivision,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option>' + jsonobject[i].distlocation + "</option>";
				}
				$("#master-distlocation-list").html(s);
			},
		});

	});

	//Getting Data on Change Of Category Id
	$("#pump-matrial-category").change(function() {
		var categoryName = $(this).val();
		var s = '<option value="">--Select--</option>';
		if (categoryName == "") {
			alert("Please Select Category !!");
			return false;
		}
		//Getting List Of Items
		$.ajax({
			type: "GET",
			url: "/pump/get/itemname/" + categoryName,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				console.log(data);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option value="' + jsonobject[i].itemId + '">' + jsonobject[i].itemName + "</option>";
				}
				$("#pump-spare-item-list").html(s);
			},
		});

	});

	//Getting Data on Change Of Item Id
	$("#pump-spare-item-list").change(function() {
		var itemId = $(this).val();
		if (itemId == "") {
			alert("Select Item Name !!");
			return false;
		}
		//Getting Stock Type 
		$.ajax({
			type: "GET",
			url: "/pump/get/stockItem/" + itemId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				var stocktype = "";
				if (jsonobject.stockType == "ML") {
					stocktype = "Materials"
				}
				if (jsonobject.stockType == "SP") {
					stocktype = "Spares"
				}
				if (jsonobject.stockType == "TE") {
					stocktype = "Tools & Equipments"
				}

				$("#pump-spare-stockType").val(stocktype);
				$("#pump-material-stockType").val(jsonobject.stockType);
				$("#pump-spare-hsnCode").val(jsonobject.hsnCode);
				$("#pump-spare-unitMesure").val(jsonobject.unitOfMeasure);
				$("#pump-material-itemName").val(jsonobject.itemName);
			},
		});
	});

	//Get Vehicle Number List According to Vehicle Type List
	$("#master-vehicleType-list").change(function() {
		var vehicleType = $(this).val();
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
						'<option value="' + jsonobject[i].vehicleDtlsId + '">' + jsonobject[i].vehicleNo + "</option>";
				}
				$("#master-vehicleNos-list").html(s);
			},
		});

	});

	//Get Vehicle Details By Vehicle Id
	$("#master-vehicleNos-list").change(function() {
		var vehicleId = $(this).val();
		if (vehicleId == "") {
			alert("Select Vehicle Number !!")
			return false;
		}

		//Getting Vehicle Details
		$.ajax({
			type: "GET",
			url: "/masters/get/vehicle/driver/" + vehicleId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				$("#pump-vehicle-driverName").val(jsonobject.driverName);
				$("#pump-vehicle-driverPhone").val(jsonobject.driverMob);
				$("#pump-vehicle-vehicleNo").val(jsonobject.vehicleNo);
			},
		});
	});
	
	//Getting Pump Master Data By Pump Master Id
	$("#master-pump-id").change(function() {		
		var pumpMasterId = $(this).val();
		if (pumpMasterId == "") {
			alert("Select Pump Id !!");
			return false;
		}
				//Getting Pump Deatils 
		$.ajax({
			type: "GET",
			url: "/masters/get/PumpMaster/details/" + pumpMasterId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);				
				$("#pump-master-pumpType").val(jsonobject.pumpType);
				$("#pump-master-warranty").val(jsonobject.warranty);
				$("#pump-master-purchageDate").val(jsonobject.purchageDate);
				$("#pump-master-contactNo").val(jsonobject.contactNo);
				$("#pump-master-manufactName").val(jsonobject.manufactName);
				$("#pump-master-pumpMake").val(jsonobject.pumpMake);
				$("#pump-master-pumpPower").val(jsonobject.pumpPower);
				$("#pump-master-pumpId").val(jsonobject.pumpId);				
			},
		});		
		
	});
	
	//Getting DamaWard Master Data By wardNumber
	$("#master-dmaward-wardno").change(function() {		
		var wardNumber = $(this).val();
		if (wardNumber == "") {
			alert("Select Ward Number !!");
			return false;
		}
		$.ajax({
			type: "GET",
			url: "/masters/get/dmaWard/" + wardNumber,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);				
				$("#dmaward-master-wardName").val(jsonobject.wardName);					
			},
		});		
		
	});
	
	//Getting TeamCode Master Data By teamCodeId
	$("#teamCode-master-teamCodeId").change(function() {		
		var teamCodeId = $(this).val();
		if (teamCodeId == "") {
			alert("Select Team Code !!");
			return false;
		}			
		$.ajax({
			type: "GET",
			url: "/masters/get/teamCode/details/" + teamCodeId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);				
				$("#teamCode-master-siteEnginner").val(jsonobject.siteEnginner);	
				$("#teamCode-master-siteSuperwiser").val(jsonobject.siteSuperwiser);
				$("#teamCode-master-teamcode").val(jsonobject.teamCode);					
			},
		});		
		
	});

});