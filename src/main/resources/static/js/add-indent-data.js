$(document).ready(function() {

	$(".tablinks").addClass('active');
	$('.btnNext').click(function() {
		var $activeTab = $('.nav-tabs > .active');
		var $nextTab = $activeTab.next('li');
		if (validate($activeTab) === false) {
			return false;
		}
		var tab2Btn = document.querySelector('button[href="#tab2"]');
		tab2Btn.removeAttribute('disabled');
		var tab2Btn = document.querySelector('button[href="#tab3"]');
		tab2Btn.removeAttribute('disabled');
		$nextTab.find('button').trigger('click');
	});

	function validate($tab) {
		var indentNo = $("#pump-spare-indentNo").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-spare-indentDept").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-spare-complNo").val();
		if (complNo == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-spare-complCategory").val();
		if (complCategory == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var division = $("#master-division-list").val();
		if (division == "") {
			alert("Select Division !!");
			return false;
		}
		var subDivision = $("#master-sub-division-list").val();
		if (subDivision == "") {
			alert("Select Sub-Division !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
			return false;
		}
		var worksite = $("#pump-parts-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var startDate = $("#pump-parts-startDate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-parts-endDate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
	}

	$('button.tablinks').click(function() {
		$('button.tablinks').removeClass('active');
		$(this).addClass('active');
	});


	$('.btnPrevious').click(function() {
		$('.nav-tabs > .active').prev('li').find('button').trigger('click');
	});

	/***********************Strat Added Data In First Tab**********************************/

	$("#pump-parts-addbutton").click(function() {

		var indentNo = $("#pump-spare-indentNo").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-spare-indentDept").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-spare-complNo").val();
		if (complNo == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-spare-complCategory").val();
		if (complCategory == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var division = $("#master-division-list").val();
		if (division == "") {
			alert("Select Division !!");
			return false;
		}
		var subDivision = $("#master-sub-division-list").val();
		if (subDivision == "") {
			alert("Select Sub-Division !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
			return false;
		}
		var worksite = $("#pump-parts-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var startDate = $("#pump-parts-startDate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-parts-endDate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var category = $("#pump-matrial-category").val();
		if (category == "") {
			alert("Select Category !!");
			return false;
		}
		var itemName = $("#pump-spare-item-list").val();
		if (itemName == "") {
			alert("Select Item Name !!");
			return false;
		}
		var hsnCode = $("#pump-spare-hsnCode").val();
		if (hsnCode == "") {
			alert("Enter Hsn Code !!");
			return false;
		}
		var unitOfMesure = $("#pump-spare-unitMesure").val();
		if (unitOfMesure == "") {
			alert("Select Unit Of Mesure !!");
			return false;
		}
		var quantity = $("#pump-spare-qantity").val();
		if (quantity == "") {
			alert("Enter Quantity !!");
			return false;
		}
		var stockType = $("#pump-spare-stockType").val();
		if (stockType == "") {
			alert("Enter Stock Type !!");
			return false;
		}

		var itemId = $("#pump-spare-item-list").val();
		$.ajax({
			type: "GET",
			url: "/pump/add/materials/item/verify/" + itemId,
			success: function(data) {
				if (data === "true") {
					alert("Item Already Added , You Have To Delete For Re-Enter !!")
					return false;
				} else {

					$.ajax({
						type: "post",
						url: "/pump/save/add/materialData",
						data: {
							indentNo: $("#pump-spare-indentNo").val(),
							complNo: $("#pump-spare-complNo").val(),
							division: $("#master-division-list").val(),
							subDivision: $("#master-sub-division-list").val(),
							workSite: $("#pump-parts-worksite").val(),
							startDate: $("#pump-parts-startDate").val(),
							endDate: $("#pump-parts-endDate").val(),
							contactNo: $("#pump-parts-contactNo").val(),
							complDtls: $("#pump-parts-complDtls").val(),
							workPriority: $("#pump-parts-workPriority").val(),
							departmentName: $("#pump-spare-indentDept").val(),
							categoryName: $("#pump-matrial-category").val(),
							itemName: $("#pump-material-itemName").val(),
							itemId: $("#pump-spare-item-list").val(),
							unitOfMesure: $("#pump-spare-unitMesure").val(),
							hsnCode: $("#pump-spare-hsnCode").val(),
							quantity: $("#pump-spare-qantity").val(),
							stockType: $("#pump-material-stockType").val(),
							stockTypeName: $("#pump-spare-stockType").val(),


						},
						success: function(result) {

							$("#pump-matrial-category").val("");
							$("#pump-spare-item-list").val("");
							$("#pump-material-itemName").val("");
							$("#pump-spare-unitMesure").val("");
							$("#pump-spare-hsnCode").val("");
							$("#pump-spare-qantity").val("");
							$("#pump-spare-stockType").val("");
							$("#pump-spare-stockType").val("");

							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/pump/add/get/materials/" + indentNo + "/" + complNo,
								success: function(data) {
									var json = JSON.stringify(data);
									var result = JSON.parse(json);
									var row = '';
									var count = 0;
									result.forEach(function(item) {
										count = count + 1
										row += '<tr>';
										row += '<td>' + count + '</td>';
										row += '<td>' + item.categoryName + '</td>';
										row += '<td>' + item.itemName + '</td>';
										row += '<td>' + item.hsnCode + '</td>';
										row += '<td>' + item.unitOfMesure + '</td>';
										row += '<td>' + item.quantity + '</td>';
										row += '<td>' + item.stockTypeName + '</td>';
										row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id="' + item.id + '"></i></a></td>';
										row += '</tr>';
									});
									// Insert the generated HTML into the table body
									$('#myTable tbody').html(row);
								},
							});

							$('table').on('click', '.delete-btn', function(e) {
								e.preventDefault();
								var row = $(this).closest('tr');
								//var pumMaterialId = row.find('.item-delete').text();
								var itemId = $(this).data('pum-material-id');
								//alert(value);

								$.ajax({
									type: 'DELETE',
									url: '/pump/delete/materials/' + itemId,
									success: function(result) {
										alert(result);
										//Show Added Data		
										$.ajax({
											type: "GET",
											url: "/pump/add/get/materials/" + indentNo + "/" + complNo,
											success: function(data) {
												var json = JSON.stringify(data);
												var result = JSON.parse(json);
												var row = '';
												var count = 0;
												result.forEach(function(item) {
													count = count + 1
													row += '<tr>';
													row += '<td>' + count + '</td>';
													row += '<td>' + item.categoryName + '</td>';
													row += '<td>' + item.itemName + '</td>';
													row += '<td>' + item.hsnCode + '</td>';
													row += '<td>' + item.unitOfMesure + '</td>';
													row += '<td>' + item.quantity + '</td>';
													row += '<td>' + item.stockType + '</td>';
													row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id="' + item.id + '"></i></a></td>';
													row += '</tr>';
												});
												// Insert the generated HTML into the table body
												$('#myTable tbody').html(row);
											},
										});


									},
									error: function(err) {

									}
								});

							});


						},
						error: function(err) {
							alert("Something Went Wrong !! Data Not Added" + err);
						}
					});

				}
			},
		});

	});

	/*********************** End Added Data In First Tab**********************************/

	/***********************Strat Added Data In Second Tab**********************************/

	$("#pump-labour-addButton").click(function() {

		var indentNo = $("#pump-spare-indentNo").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-spare-indentDept").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-spare-complNo").val();
		if (complNo == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-spare-complCategory").val();
		if (complCategory == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var division = $("#master-division-list").val();
		if (division == "") {
			alert("Select Division !!");
			return false;
		}
		var subDivision = $("#master-sub-division-list").val();
		if (subDivision == "") {
			alert("Select Sub-Division !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
			return false;
		}
		var worksite = $("#pump-parts-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var startDate = $("#pump-parts-startDate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-parts-endDate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var category = $("#pump-labour-empCategory").val();
		if (category == "") {
			alert("Select Category !!");
			return false;
		}
		var membour = $("#pump-labour-members").val();
		if (membour == "") {
			alert("Enter Members !!");
			return false;
		}
		var days = $("#pump-labour-days").val();
		if (days == "") {
			alert("Enter Days Required !!");
			return false;
		}
		var time = $("#pump-labour-time").val();
		if (time == "") {
			alert("Enter Time Required !!");
			return false;
		}

		$.ajax({
			type: "GET",
			url: "/pump/add/labor/empcategory/verify/" + category,
			success: function(data) {
				if (data === "true") {
					alert("Employee Category Already Added , You Have To Delete For Re-Enter !!")
					return false;
				} else {
					$.ajax({
						type: "post",
						url: "/pump/save/add/labourData",
						data: {
							indentNo: $("#pump-spare-indentNo").val(),
							complNo: $("#pump-spare-complNo").val(),
							division: $("#master-division-list").val(),
							subDivision: $("#master-sub-division-list").val(),
							workSite: $("#pump-parts-worksite").val(),
							startDate: $("#pump-parts-startDate").val(),
							endDate: $("#pump-parts-endDate").val(),
							contactNo: $("#pump-parts-contactNo").val(),
							complDtls: $("#pump-parts-complDtls").val(),
							workPriority: $("#pump-parts-workPriority").val(),
							departmentName: $("#pump-spare-indentDept").val(),
							empCategory: $("#pump-labour-empCategory").val(),
							members: $("#pump-labour-members").val(),
							daysRequired: $("#pump-labour-days").val(),
							timeRequired: $("#pump-labour-time").val(),


						},
						success: function(result) {

							$("#pump-labour-empCategory").val("");
							$("#pump-labour-members").val("");
							$("#pump-labour-days").val("");
							$("#pump-labour-time").val("");

							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/pump/add/get/labour/" + indentNo + "/" + complNo,
								success: function(data) {
									var json = JSON.stringify(data);
									var result = JSON.parse(json);
									var row = '';
									var count = 0;
									result.forEach(function(labor) {
										count = count + 1
										row += '<tr>';
										row += '<td>' + count + '</td>';
										row += '<td>' + labor.empCategory + '</td>';
										row += '<td>' + labor.members + '</td>';
										row += '<td>' + labor.daysRequired + '</td>';
										row += '<td>' + labor.timeRequired + '</td>';
										row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn1" data-pum-labor-id="' + labor.id + '"></i></a></td>';
										row += '</tr>';
									});
									// Insert the generated HTML into the table body
									$('#myTable1 tbody').html(row);
								},
							});

							$('table').on('click', '.delete-btn1', function(e) {
								e.preventDefault();
								var row = $(this).closest('tr');
								//var pumMaterialId = row.find('.item-delete').text();
								var labourId = $(this).data('pum-labor-id');
								//alert(value);

								$.ajax({
									type: 'DELETE',
									url: '/pump/delete/labour/' + labourId,
									success: function(result) {
										alert(result);
										//Show Added Data		
										$.ajax({
											type: "GET",
											url: "/pump/add/get/labour/" + indentNo + "/" + complNo,
											success: function(data) {
												var json = JSON.stringify(data);
												var result = JSON.parse(json);
												var row = '';
												var count = 0;
												result.forEach(function(labor) {
													count = count + 1
													row += '<tr>';
													row += '<td>' + count + '</td>';
													row += '<td>' + labor.empCategory + '</td>';
													row += '<td>' + labor.members + '</td>';
													row += '<td>' + labor.daysRequired + '</td>';
													row += '<td>' + labor.timeRequired + '</td>';
													row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn1" data-pum-labor-id="' + labor.id + '"></i></a></td>';
													row += '</tr>';
												});
												// Insert the generated HTML into the table body
												$('#myTable1 tbody').html(row);
											},
										});


									},
									error: function(err) {

									}
								});

							});


						},
						error: function(err) {
							alert("Something Went Wrong !! Data Not Added" + err);
						}
					});

				}
			},

		});
	});

	/*********************** End Added Data In Second Tab**********************************/

	/*********************** Start Added Data In Third Tab**********************************/

	$("#pump-vehicle-addButton").click(function() {

		var indentNo = $("#pump-spare-indentNo").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-spare-indentDept").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-spare-complNo").val();
		if (complNo == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-spare-complCategory").val();
		if (complCategory == "") {
			alert("Select Complaint No !!");
			return false;
		}
		var division = $("#master-division-list").val();
		if (division == "") {
			alert("Select Division !!");
			return false;
		}
		var subDivision = $("#master-sub-division-list").val();
		if (subDivision == "") {
			alert("Select Sub-Division !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
			return false;
		}
		var worksite = $("#pump-parts-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var startDate = $("#pump-parts-startDate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-parts-endDate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var vehicleType = $("#master-vehicleType-list").val();
		if (vehicleType == "") {
			alert("Select Vehicle Type !!");
			return false;
		}
		var vehicleId = $("#master-vehicleNos-list").val();
		if (vehicleId == "") {
			alert("Select Vehicle Number !!");
			return false;
		}
		var driverName = $("#pump-vehicle-driverName").val();
		if (driverName == "") {
			alert("Enter Driver Name !!");
			return false;
		}
		var driverContact = $("#pump-vehicle-driverPhone").val();
		if (driverContact == "") {
			alert("Enter Driver Contact No !!");
			return false;
		}
		var meterReading = $("#pump-vehicle-meterReading").val();
		if (meterReading == "") {
			alert("Enter Start Meter Reading  !!");
			return false;
		}
		var stratingTime = $("#pump-vehicle-stratingTime").val();
		if (stratingTime == "") {
			alert("Enter Starting Time  !!");
			return false;
		}

		var vehicleNo = $("#pump-vehicle-vehicleNo").val();

		$.ajax({
			type: "GET",
			url: "/pump/add/vehicle/number/verify/" + vehicleNo,
			success: function(data) {
				if (data === "true") {
					alert("Vehicle No Already Added , You Have To Delete For Re-Enter !!")
					return false;
				} else {

					$.ajax({
						type: "post",
						url: "/pump/save/add/vehicleData",
						data: {
							indentNo: $("#pump-spare-indentNo").val(),
							complNo: $("#pump-spare-complNo").val(),
							division: $("#master-division-list").val(),
							subDivision: $("#master-sub-division-list").val(),
							workSite: $("#pump-parts-worksite").val(),
							startDate: $("#pump-parts-startDate").val(),
							endDate: $("#pump-parts-endDate").val(),
							contactNo: $("#pump-parts-contactNo").val(),
							complDtls: $("#pump-parts-complDtls").val(),
							workPriority: $("#pump-parts-workPriority").val(),
							departmentName: $("#pump-spare-indentDept").val(),
							vehicleType: $("#master-vehicleType-list").val(),
							vehicleId: $("#master-vehicleNos-list").val(),
							driverName: $("#pump-vehicle-driverName").val(),
							driverPhone: $("#pump-vehicle-driverPhone").val(),
							meterReading: $("#pump-vehicle-meterReading").val(),
							stratTime: $("#pump-vehicle-stratingTime").val(),
							vehicleNo: $("#pump-vehicle-vehicleNo").val(),							

						},
						success: function(result) {

							    $("#master-vehicleType-list").val(""),
								$("#master-vehicleNos-list").val(""),
								$("#pump-vehicle-driverName").val(""),
								$("#pump-vehicle-driverPhone").val(""),
								$("#pump-vehicle-meterReading").val(""),
								$("#pump-vehicle-stratingTime").val(""),
								$("#pump-vehicle-vehicleNo").val(""),

								//Show Added Data		
								$.ajax({
									type: "GET",
									url: "/pump/add/get/vehicle/" + indentNo + "/" + complNo,
									success: function(data) {
										var json = JSON.stringify(data);
										var result = JSON.parse(json);
										var row = '';
										var count = 0;
										result.forEach(function(vehicle) {
											count = count + 1
											row += '<tr>';
											row += '<td>' + count + '</td>';
											row += '<td>' + vehicle.vehicleType + '</td>';
											row += '<td>' + vehicle.vehicleNo + '</td>';
											row += '<td>' + vehicle.driverName + '</td>';
											row += '<td>' + vehicle.driverPhone + '</td>';
											row += '<td>' + vehicle.meterReading + '</td>';
											row += '<td>' + vehicle.stratTime + '</td>';
											row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn2" data-pum-vehicle-id="' + vehicle.id + '"></i></a></td>';
											row += '</tr>';
										});
										// Insert the generated HTML into the table body
										$('#myTable2 tbody').html(row);
									},
								});

							$('table').on('click', '.delete-btn2', function(e) {
								e.preventDefault();
								var row = $(this).closest('tr');
								//var pumMaterialId = row.find('.item-delete').text();
								var vehicleReqId = $(this).data('pum-vehicle-id');
								//alert(value);

								$.ajax({
									type: 'DELETE',
									url: '/pump/delete/vehicle/' + vehicleReqId,
									success: function(result) {
										alert(result);
										//Show Added Data		
										$.ajax({
											type: "GET",
											url: "/pump/add/get/vehicle/" + indentNo + "/" + complNo,
											success: function(data) {
												var json = JSON.stringify(data);
												var result = JSON.parse(json);
												var row = '';
												var count = 0;
												result.forEach(function(vehicle) {
													count = count + 1
													row += '<tr>';
													row += '<td>' + count + '</td>';
													row += '<td>' + vehicle.vehicleType + '</td>';
													row += '<td>' + vehicle.vehicleNo + '</td>';
													row += '<td>' + vehicle.driverName + '</td>';
													row += '<td>' + vehicle.driverPhone + '</td>';
													row += '<td>' + vehicle.meterReading + '</td>';
													row += '<td>' + vehicle.stratTime + '</td>';
													row += '<td><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn2" data-pum-vehicle-id="' + vehicle.id + '"></i></a></td>';
													row += '</tr>';
												});
												// Insert the generated HTML into the table body
												$('#myTable2 tbody').html(row);
											},
										});


									},
									error: function(err) {

									}
								});

							});

						},
						error: function(err) {
							alert("Something Went Wrong !! Data Not Added" + err);
						}
					});

				}
			},
		});

	});


	/*********************** End Added Data In Third Tab**********************************/

});