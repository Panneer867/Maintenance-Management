$(document).ready(function () {
	
	$("#getconsumer-data-from-billing").change(function () {		
		var counsumerId = $("#getconsumer-data-from-billing").val();
		
		 if (counsumerId == " ") {	      
	      alert("Please Enter Consumer Id !!");
	      return false;
	    }
			console.log(counsumerId);
			
			$.ajax({
		      type: "GET",
		      url: "http://192.168.0.157:9595/ubarms/arms/getConsumersData/121/SAGAR",
		      success: function (data) {
		        var json = JSON.stringify(data);
		        var jsonobject = JSON.parse(json);
		
		        console.log(jsonobject);
		       
		       }
		    });   			
		
	});
	
});
