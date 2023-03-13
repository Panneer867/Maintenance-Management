$(document).ready(function() {

	$("#esclate-invest-data").prop('disabled', true);
	$("#with-material-button").prop('disabled', true);
	//$("#material-link").hide();
	//$("#with-material-button").off('click');	
	
	$(".complNo-click").click(function(){
		//alert("click");
		//$("#material-link").show();	
	});

	$("#user-designation").change(function() {
		var designation = $("#user-designation").val();
		var s = '<option value=" ">--Select--</option>';
		if (designation == " ") {
			alert("Please Select Designation !!");
			return false;
		}
		$.ajax({
			type: "GET",
			url: "/admin/user/getIds/" + designation,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);
				for (var i = 0; i < jsonobject.length; i++) {
					s +=
						'<option>' + jsonobject[i].ubarmsUserId + "</option>";
				}
				$("#all-user-ids").html(s);
			},
		});

	});

	$("#all-user-ids").change(function() {
		var userid = $("#all-user-ids").val();
		if (userid == "") {
			alert("Please Select User Id !!");
			return false;
		}
		$.ajax({
			type: "GET",
			url: "/admin/user/getDtls/" + userid,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				$("#user-name").val(jsonobject.name);
				$("#user-email").val(jsonobject.email);
				$("#user-mobile").val(jsonobject.mobile);

			},
		});
	});

	//Get Esclation Data
	$("#job-done-notdone").change(function() {

		var jobSts = $("#job-done-notdone").val();
		var esclatedId = $("#esclatedFromId").val();
		var esclatedTo = $("#esclatedTo").val();
		
		if (esclatedId == "") {
			alert("Complaint No Not Found !!")

			$("#submit-invest-data").prop('disabled', true);
			$("#esclate-invest-data").prop('disabled', true);
			$("#with-material-button").prop('disabled', true);

			return false;
		}
				
		if (jobSts == 'Y') {

			$("#submit-invest-data").prop('disabled', false);
			$("#with-material-button").prop('disabled', true);
			$("#esclate-invest-data").prop('disabled', true);
		}
		
		if (jobSts == 'N') {

			$("#submit-invest-data").prop('disabled', true);
			$("#with-material-button").prop('disabled', false);
			$("#esclate-invest-data").prop('disabled', true);
		}
		
		if (jobSts == 'E') {

			$("#submit-invest-data").prop('disabled', true);
			$("#esclate-invest-data").prop('disabled', false);
			$("#with-material-button").prop('disabled', true);

			var s = '<option value=" ">--Select--</option>';

			$.ajax({
				type: "GET",
				url: "/admin/user/getIds/" + esclatedTo,
				success: function(data) {
					var json = JSON.stringify(data);
					var jsonobject = JSON.parse(json);
					for (var i = 0; i < jsonobject.length; i++) {
						s +=
							'<option>' + jsonobject[i].ubarmsUserId + "</option>";
					}
					$("#esclated-to-id").html(s);
				},
			});
		}
	});

	$("#esclated-to-id").change(function() {
		var esclationToId = $("#esclated-to-id").val();
		if (esclationToId == "") {
			alert("Please Select Id !!");
			return false;
		}
		$.ajax({
			type: "GET",
			url: "/admin/user/getDtls/" + esclationToId,
			success: function(data) {
				var json = JSON.stringify(data);
				var jsonobject = JSON.parse(json);

				$("#esclation-to-name").val(jsonobject.name);
			},
		});
	});

	$("#submit-invest-data").click(function() {
		var visitedDate = $("#visitedDate").val();
		var fieldVisitRemark = $("#field-visit-remark").val();
		var jobDoneRemark = $("#jobDoneRemark").val();
		var jobsts = $("#job-done-notdone").val();

		if (visitedDate == "") {
			alert("Please Enter Visited Date !!");
			return false;
		}
		if (fieldVisitRemark == "") {
			alert("Please Write Visited Remark !!");
			return false;
		}
		if (jobDoneRemark == "") {
			alert("Please Write Job Done Remark !!");
			return false;
		}
		if (jobsts == "") {
			alert("Please Select Job Done/Not Done/Esclate !!");
			return false;
		}
	});
	
	$("#with-material-button").click(function() {
		var visitedDate = $("#visitedDate").val();
		var fieldVisitRemark = $("#field-visit-remark").val();
		
		if (visitedDate == "") {
			alert("Please Enter Visited Date !!");
			return false;
		}
		if (fieldVisitRemark == "") {
			alert("Please Write Visited Remark !!");
			return false;
		}
	});
		
	$("#esclate-invest-data").click(function() {
		var esclatedReason = $("#esclatedReason").val();
		var esclatedDate = $("#esclatedDate").val();
		var esclationToName = $("#esclation-to-name").val();
		var jobsts = $("#job-done-notdone").val();
		var visitedDate = $("#visitedDate").val();
		var fieldVisitRemark = $("#field-visit-remark").val();

        if (visitedDate == "") {
			alert("Please Enter Visited Date !!");
			return false;
		}
		if (fieldVisitRemark == "") {
			alert("Please Write Visited Remark !!");
			return false;
		}
		if (esclatedDate == "") {
			alert("Please Enter Esclation Date !!");
			return false;
		}
		if (esclationToName == "") {
			alert("Please Select Esclate To Id !!");
			return false;
		}
		if (esclatedReason == "") {
			alert("Please Write Esclation Reason !!");
			return false;
		}
		if (jobsts == "") {
			alert("Please Select Job Done/Not Done/Esclate !!");
			return false;
		}
	});		

});
