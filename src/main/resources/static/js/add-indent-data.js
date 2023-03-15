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

		/* added data in second and third tab*/

		$("#pump-labour-division").val($("#master-division-list").val());
		$("#pump-labour-subdivision").val($("#master-sub-division-list").val());
		$("#pump-labour-worksite").val($("#pump-parts-workSite").val());
		$("#pump-labour-startDate").val($("#pump-parts-startDate").val());
		$("#pump-labour-endDate").val($("#pump-parts-endDate").val());
		$("#pump-labour-contactNo").val($("#pump-parts-contactNo").val());
		$("#pump-labour-workpriority").val($("#pump-parts-workPriority").val());
		$("#pump-vehicle-division").val($("#master-division-list").val());
		$("#pump-vehicle-subdivision").val($("#master-sub-division-list").val());
		$("#pump-vehicle-worksite").val($("#pump-parts-workSite").val());
		$("#pump-vehicle-stratdate").val($("#pump-parts-startDate").val());
		$("#pump-vehicle-enddate").val($("#pump-parts-endDate").val());
		$("#pump-vehicle-contactNo").val($("#pump-parts-contactNo").val());
		$("#pump-vehicle-workpriority").val($("#pump-parts-workPriority").val());


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
		var worksite = $("#pump-parts-workSite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
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
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
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
		var worksite = $("#pump-parts-workSite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
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
		var contactNo = $("#pump-parts-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var complDtls = $("#pump-parts-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var workPriority = $("#pump-parts-workPriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
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

		$.ajax({
			type: "post",
			url: "/pump/save/add/materialData",
			data: {
				indentNo: $("#pump-spare-indentNo").val(),
				complNo: $("#pump-spare-complNo").val(),
				division: $("#master-division-list").val(),
				subDivision: $("#master-sub-division-list").val(),
				workSite: $("#pupm-parts-workSite").val(),
				startDate: $("#pump-parts-startDate").val(),
				endDate: $("#pump-parts-endDate").val(),
				contactNo: $("#pump-parts-contactNo").val(),
				complDtls: $("#pump-parts-complDtls").val(),
				workPriority: $("#pump-parts-workPriority").val(),
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
					url: "/pump/add/get/materials/MTS/" + indentNo + "/" + complNo,
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
							row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id="' + item.pumMaterialId + '"></i></a></td>';
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
					var pumMaterialId = $(this).data('pum-material-id');
					//alert(value);

					$.ajax({
						type: 'DELETE',
						url: '/pump/delete/materials/' + pumMaterialId,
						success: function(result) {
							alert(result);
							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/pump/add/get/materials/MTS/" + indentNo + "/" + complNo,
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
										row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id="' + item.pumMaterialId + '"></i></a></td>';
										//row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn">' + item.pumMaterialId + '</i></a></td>';
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

	});

	/*********************** End Added Data In First Tab**********************************/

	/***********************Strat Added Data In Second Tab**********************************/

	$("#pump-labour-addButton").click(function() {

		var indentNo = $("#pump-labour-indent").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-labour-indentDepartment").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-labour-comlNo").val();
		if (complNo == "") {
			alert("Enter Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-labour-comlCategory").val();
		if (complCategory == "") {
			alert("Enter Complaint No !!");
			return false;
		}
		var division = $("#pump-labour-division").val();
		if (division == "") {
			alert("Enter Division !!");
			return false;
		}
		var subDivision = $("#pump-labour-subdivision").val();
		if (subDivision == "") {
			alert("Enter Sub-Division !!");
			return false;
		}
		var worksite = $("#pump-labour-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var startDate = $("#pump-labour-startDate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-labour-endDate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var contactNo = $("#pump-labour-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var complDtls = $("#pump-labour-comlDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var workPriority = $("#pump-labour-workpriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
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
			type: "post",
			url: "/pump/save/add/labourData",
			data: {
				indentNo: $("#pump-labour-indent").val(),
				complNo: $("#pump-labour-comlNo").val(),
				division: $("#pump-labour-division").val(),
				subDivision: $("#pump-labour-subdivision").val(),
				workSite: $("#pump-labour-worksite").val(),
				startDate: $("#pump-labour-startDate").val(),
				endDate: $("#pump-labour-endDate").val(),
				contactNo: $("#pump-labour-contactNo").val(),
				complDtls: $("#pump-labour-comlDtls").val(),
				workPriority: $("#pump-labour-workpriority").val(),
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
					url: "/pump/add/get/materials/LBR/" + indentNo + "/" + complNo,
					success: function(data) {
						var json = JSON.stringify(data);
						var result = JSON.parse(json);
						var row = '';
						var count = 0;
						result.forEach(function(item) {
							count = count + 1
							row += '<tr>';
							row += '<td>' + count + '</td>';
							row += '<td>' + item.empCategory + '</td>';
							row += '<td>' + item.members + '</td>';
							row += '<td>' + item.daysRequired + '</td>';
							row += '<td>' + item.timeRequired + '</td>';
							row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id1="' + item.pumMaterialId + '"></i></a></td>';
							row += '</tr>';
						});
						// Insert the generated HTML into the table body
						$('#myTable1 tbody').html(row);
					},
				});

				$('table').on('click', '.delete-btn', function(e) {
					e.preventDefault();
					var row = $(this).closest('tr');
					//var pumMaterialId = row.find('.item-delete').text();
					var pumMaterialId = $(this).data('pum-material-id1');
					//alert(value);

					$.ajax({
						type: 'DELETE',
						url: '/pump/delete/materials/' + pumMaterialId,
						success: function(result) {
							alert(result);
							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/pump/add/get/materials/LBR/" + indentNo + "/" + complNo,
								success: function(data) {
									var json = JSON.stringify(data);
									var result = JSON.parse(json);
									var row = '';
									var count = 0;
									result.forEach(function(item) {
										count = count + 1
										row += '<tr>';
										row += '<td>' + count + '</td>';
										row += '<td>' + item.empCategory + '</td>';
										row += '<td>' + item.members + '</td>';
										row += '<td>' + item.daysRequired + '</td>';
										row += '<td>' + item.timeRequired + '</td>';
										row += '<td class="item-delete"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn" data-pum-material-id1="' + item.pumMaterialId + '"></i></a></td>';
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


	});

	/*********************** End Added Data In Second Tab**********************************/

	/*********************** Start Added Data In Third Tab**********************************/

	$("#pump-vehicle-addButton").click(function() {

		var indentNo = $("#pump-vehicle-indentNo").val();
		if (indentNo == "") {
			alert("Enter Indent Number !!");
			return false;
		}
		var indentDept = $("#pump-vehicle-indentDept").val();
		if (indentDept == "") {
			alert("Enter Indent Department !!");
			return false;
		}
		var complNo = $("#pump-vehicle-complNo").val();
		if (complNo == "") {
			alert("Enter Complaint No !!");
			return false;
		}
		var complCategory = $("#pump-vehicle-complCategory").val();
		if (complCategory == "") {
			alert("Enter Complaint No !!");
			return false;
		}
		var division = $("#pump-vehicle-division").val();
		if (division == "") {
			alert("Enter Division !!");
			return false;
		}
		var subDivision = $("#pump-vehicle-subdivision").val();
		if (subDivision == "") {
			alert("Enter Sub-Division !!");
			return false;
		}
		var worksite = $("#pump-vehicle-worksite").val();
		if (worksite == "") {
			alert("Enter Work Site Address !!");
			return false;
		}
		var startDate = $("#pump-vehicle-stratdate").val();
		if (startDate == "") {
			alert("Enter Expected Start Date !!");
			return false;
		}
		var endDate = $("#pump-vehicle-enddate").val();
		if (endDate == "") {
			alert("Enter Expected End Date !!");
			return false;
		}
		var contactNo = $("#pump-vehicle-contactNo").val();
		if (contactNo == "") {
			alert("Enter Contact No !!");
			return false;
		}
		var complDtls = $("#pump-vehicle-complDtls").val();
		if (complDtls == "") {
			alert("Enter Complain Details !!");
			return false;
		}
		var workPriority = $("#pump-vehicle-workpriority").val();
		if (workPriority == "") {
			alert("Select Work Priority !!");
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

		$.ajax({
			type: "post",
			url: "/pump/save/add/vehicleData",
			data: {
				indentNo: $("#pump-vehicle-indentNo").val(),
				complNo: $("#pump-vehicle-complNo").val(),
				division: $("#pump-vehicle-division").val(),
				subDivision: $("#pump-vehicle-subdivision").val(),
				workSite: $("#pump-vehicle-worksite").val(),
				startDate: $("#pump-vehicle-stratdate").val(),
				endDate: $("#pump-vehicle-enddate").val(),
				contactNo: $("#pump-vehicle-contactNo").val(),
				complDtls: $("#pump-vehicle-complDtls").val(),
				workPriority: $("#pump-vehicle-workpriority").val(),
				vehicleType: $("#master-vehicleType-list").val(),
				vehicle: $("#master-vehicleNos-list").val(),
				driverName: $("#pump-vehicle-driverName").val(),
				driverPhone: $("#pump-vehicle-driverPhone").val(),
				meterReading: $("#pump-vehicle-meterReading").val(),
				stratTime: $("#pump-vehicle-stratingTime").val(),

			},
			success: function(result) {

				$("#master-vehicleType-list").val(""),
					$("#master-vehicleNos-list").val(""),
					$("#pump-vehicle-driverName").val(""),
					$("#pump-vehicle-driverPhone").val(""),
					$("#pump-vehicle-meterReading").val(""),
					$("#pump-vehicle-stratingTime").val(""),

					//Show Added Data		
					$.ajax({
						type: "GET",
						url: "/pump/add/get/materials/VHE/" + indentNo + "/" + complNo,
						success: function(data) {
							var json = JSON.stringify(data);
							var result = JSON.parse(json);
							var row = '';
							var count = 0;
							result.forEach(function(item) {
								count = count + 1
								row += '<tr>';
								row += '<td>' + count + '</td>';
								row += '<td>' + item.vehicleType + '</td>';
								row += '<td>' + item.vehicleNo + '</td>';
								row += '<td>' + item.driverName + '</td>';
								row += '<td>' + item.driverPhone + '</td>';
								row += '<td>' + item.meterReading + '</td>';
								row += '<td>' + item.stratTime + '</td>';
								row += '<td class="item-delete2"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn2" data-pum-material-id2="' + item.pumMaterialId + '"></i></a></td>';
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
					var pumMaterialId = $(this).data('pum-material-id2');
					//alert(value);

					$.ajax({
						type: 'DELETE',
						url: '/pump/delete/materials/' + pumMaterialId,
						success: function(result) {
							alert(result);
							//Show Added Data		
							$.ajax({
								type: "GET",
								url: "/pump/add/get/materials/VHE/" + indentNo + "/" + complNo,
								success: function(data) {
									var json = JSON.stringify(data);
									var result = JSON.parse(json);
									var row = '';
									var count = 0;
									result.forEach(function(item) {
										count = count + 1
										row += '<tr>';
										row += '<td>' + count + '</td>';
										row += '<td>' + item.vehicleType + '</td>';
										row += '<td>' + item.vehicleNo + '</td>';
										row += '<td>' + item.driverName + '</td>';
										row += '<td>' + item.driverPhone + '</td>';
										row += '<td>' + item.meterReading + '</td>';
										row += '<td>' + item.stratTime + '</td>';
										row += '<td class="item-delete2"><a href="#"><i class="glyphicon glyphicon-trash text-danger delete-btn2" data-pum-material-id2="' + item.pumMaterialId + '"></i></a></td>';
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



	});


	/*********************** End Added Data In Third Tab**********************************/

});