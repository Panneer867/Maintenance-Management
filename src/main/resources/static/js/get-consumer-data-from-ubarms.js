$(document).ready(function () {
	$("#esclate-invest-data").prop('disabled', true);		
	$("#user-designation").change(function () {		
		var designation = $("#user-designation").val();
		var s = '<option value=" ">--Select--</option>';
		 if (designation == " ") {	      
	      alert("Please Select Designation !!");
	      return false;
	    }		
		$.ajax({
		      type: "GET",
		      url: "/admin/user/getIds/"+designation,
		      success: function (data) {
		        var json = JSON.stringify(data);
		        var jsonobject = JSON.parse(json);
					for (var i = 0; i < jsonobject.length; i++) {
			          s +=
			            '<option>' + jsonobject[i].ubarmsUserId +"</option>";
			        }
			        $("#all-user-ids").html(s);
			      },		      
		    });   			
		
	});
	
	$("#all-user-ids").change(function () {	
		var userid = $("#all-user-ids").val();
		if(userid ==""){
		  alert("Please Select User Id !!");
	      return false;
		}
		$.ajax({
		      type: "GET",
		      url: "/admin/user/getDtls/"+userid,
		      success: function (data) {
		        var json = JSON.stringify(data);
		        var jsonobject = JSON.parse(json);
		        
					$("#user-name").val(jsonobject.name);
					$("#user-email").val(jsonobject.email);
					$("#user-mobile").val(jsonobject.mobile);
					
			      },		      
		    });  
	});
	
	//Get Esclation Data
	$("#job-done-notdone").change(function () {	
		
		$("#esclate-invest-data").prop('disabled', false);
		$("#submit-invest-data").prop('disabled', true);
		
		var jobSts = $("#job-done-notdone").val();
		var esclatedId = $("#esclatedFromId").val();
		var esclatedTo = $("#esclatedTo").val();
		var s = '<option value=" ">--Select--</option>';
		if(esclatedId ==""){
			alert("Complaint No Not Found !!")
			return false;
		}
		else if(jobSts == 'Y'){
			$("#submit-invest-data").prop('disabled', false);
			$("#esclate-invest-data").prop('disabled', true);
			return false;		
		}
		$.ajax({
		      type: "GET",
		      url: "/admin/user/getIds/"+esclatedTo,
		      success: function (data) {
		        var json = JSON.stringify(data);
		        var jsonobject = JSON.parse(json);
					for (var i = 0; i < jsonobject.length; i++) {
			          s +=
			            '<option>' + jsonobject[i].ubarmsUserId +"</option>";
			        }
			        $("#esclated-to-id").html(s);
			      },		      
		    }); 
	});
	
	$("#esclated-to-id").change(function () {	
		var esclationToId = $("#esclated-to-id").val();
		if(esclationToId ==""){
		  alert("Please Select Id !!");
	      return false;
		}
		$.ajax({
		      type: "GET",
		      url: "/admin/user/getDtls/"+esclationToId,
		      success: function (data) {
		        var json = JSON.stringify(data);
		        var jsonobject = JSON.parse(json);
		        
					$("#esclation-to-name").val(jsonobject.name);					
			      },		      
		    });  
	});
	
});
