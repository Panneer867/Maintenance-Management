$(document).ready(function () {

	$("#payment-empId").change(function (e) {  
       if (e.target.value === "") {
        $("#payment-empId").val("");
        alert("Select the empCode");
        return false;
        }
         $.ajax({
      type: "get",
      url: "/employee/getEmp/" + e.target.value,
      success: function (data) {

        var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);

      			$("#payment-empId").val(jsonobject.employeeCode);
				$("#payment-empName").val(jsonobject.empName);
				$("#payment-designation").val(jsonobject.department);
				$("#payment-fatherName").val(jsonobject.fatherName);
				$("#payment-department").val(jsonobject.department);									   							
				$("#payment-grossSalary").val(jsonobject.salary);   
				$("#payment-basicSalary").val(jsonobject.basicSalary); 
			 
				    
				var grossSal = $("#payment-grossSalary").val();
				var empBasicsal = $("#payment-basicSalary").val();
				 		
				 // DA Value
				 var empda = parseFloat (grossSal * 0.1);
				
				
				// HRA value
				 var emphra = parseFloat (empBasicsal) + parseFloat (empda);
				 var hra = parseFloat(emphra * 0.4);
				 //alert(hra);
				 
				  // Fixed Value some feilds
				  var empCon = parseFloat(1600);
				  var empMA = parseFloat(1250);
		     	  var empTelPhone = parseFloat(0);
		     	  var emplta = parseFloat(0);
		   
		     	  
		     	// Spl Allow Value
		     	 var empSplAllow1 = parseFloat(grossSal) - parseFloat(emphra) 
		     	 var empSplAllow2 = parseFloat(empSplAllow1) - parseFloat(hra) 
		     	 var empSplAllow3 = parseFloat(empSplAllow2) - parseFloat(empCon) 
		         var empSplAllow = parseFloat(empSplAllow3) - parseFloat(empMA) 
		       
		       
		       	 //ESI Value
		       	   var empEsi;
		       	 if( 21000 <= grossSal){
					  empEsi = parseFloat (grossSal * 0.0075);	
				   }
		       	 else{
					   empEsi=0;
					  }
				  
				   // PT Value
				   
				   if(15000 <= grossSal)
				   {
					    var empTax = 200;
				   }
				   else{
					   var empTax = 150;
				   }
				   
				   
				  // PF Value
				   var empPf = parseFloat (emphra * 0.12);
		       
		       
			$("#payment-da").val(empda); 
		    $("#payment-hra").val(hra); 	
			$("#payment-conveyance").val(empCon);
			$("#payment-medical").val(empMA);
			$("#payment-telAllow").val(empTelPhone);
			$("#payment-ltaAllow").val(emplta);
			$("#payment-profTax").val(empTax);
			$("#payment-spalAllow").val(empSplAllow);
			$("#payment-esi").val(empEsi);
			$("#payment-pf").val(empPf);
      },
      error: function (e) {
        console.log("error occured while fetching Item");
      },
      });
	});
	
	 $("#payment-tds").keyup(function(){
       var tdsvalue = $("#payment-tds").val();
       var grossSalary = $("#payment-grossSalary").val();
       var pf =  $("#payment-pf").val();
       var tax = $("#payment-profTax").val();
       var esi = $("#payment-esi").val();
       
       var totalSum = parseFloat(tdsvalue)+parseFloat(pf)+parseFloat(tax)+parseFloat(esi);       
       var netSalary = parseFloat(grossSalary)-parseFloat(totalSum);
       $("#payment-netSalary").val(netSalary);
     });



// getting emp data to leave 

$("#leave-empId").change(function (e) {
	
	 if (e.target.value === "") {
        $("#payment-empId").val("");
        alert("Select the empCode");
        return false;
        }
	
	
	$.ajax({
      type: "get",
      url: "/employee/getEmp/" + e.target.value,
      success: function (data) {
        var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);

      			$("#leave-empId").val(jsonobject.employeeCode);
				$("#leave-empName").val(jsonobject.empName);
				$("#leave-designation").val(jsonobject.department);
				$("#leave-fatherName").val(jsonobject.fatherName);
				$("#leave-department").val(jsonobject.department);									   							
				$("#leave-houseNo").val(jsonobject.salary);   
				$("#leave-dateOfJoin").val(jsonobject.basicSalary); 
		        $("#leave-address").val(jsonobject.address); 
		        $("#leave-refContactNo").val(jsonobject.refContactNo); 
		        $("#leave-contactNo").val(jsonobject.contactNo); 
		        $("#leave-Asl").val(jsonobject.sl); 
		        $("#leave-Acl").val(jsonobject.cl); 
		        $("#leave-Alwp").val(jsonobject.lwp); 
		        
      },
      error: function (e) {
        console.log("error occured while fetching Item");
      },
      });

});


});


function empDataForHrLeave(employeeId){	

	

			$.ajax({
				type:"get",
				url:"/employee/getEmpData/"+employeeId,
				success:function(data){                 
				var json = JSON.stringify(data);	    	
				var jsonobject = JSON.parse(json);	
															
				$("#hrleave-empId").val(jsonobject.employeeCode);
				$("#hrleave-empName").val(jsonobject.empName);
				$("#hrleave-designation").val(jsonobject.department);
				$("#hrleave-fatherName").val(jsonobject.fatherName);
				$("#hrleave-department").val(jsonobject.department);									   							
				$("#hrleave-houseNo").val(jsonobject.salary);   
				$("#hrleave-dateOfJoin").val(jsonobject.basicSalary); 
		        $("#hrleave-address").val(jsonobject.address); 
		        $("#hrleave-refContactNo").val(jsonobject.refContactNo); 
		        $("#hrleave-contactNo").val(jsonobject.contactNo); 
		        $("#hrleave-Asl").val(jsonobject.sl); 
		        $("#hrleave-Acl").val(jsonobject.cl); 
		        $("#hrleave-Alwp").val(jsonobject.lwp); 		   
				
		  }
		  
		}); 
	}



