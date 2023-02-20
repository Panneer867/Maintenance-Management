$(document).ready(function () {


// getting emp data to leave 

$("#leave-empId").change(function (e) {
	
	$.ajax({
      type: "get",
      url: "/employee/getEmp/" + e.target.value,
      success: function (data) {
        var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);

      			$("#leave-empId").val(jsonobject.employeeCode);
				$("#leave-empName").val(jsonobject.empName);
				$("#leave-designation").val(jsonobject.designation);
				$("#leave-fatherName").val(jsonobject.fatherName);
				$("#leave-department").val(jsonobject.department);									   							
				$("#leave-houseNo").val(jsonobject.houseNo);   
				$("#leave-dateOfJoin").val(jsonobject.dateOfJoin); 
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
      
          var employeeCode = $("#leave-empId").val();
          //alert(employeeCode);

       $.ajax({
       type: "get",
       url: "/employee/total-leaves/" + employeeCode,
       success: function (data) {
	    
	      var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);
        
         $("#totalSacnSickLeave").val(jsonobject.sacnSickLeave);
         $("#totalSacnCausalLeave").val(jsonobject.sacnCausalLeave);
         $("#totalSacnLwp").val(jsonobject.sacnLwp);
    
        }
    });
});

// getting emp data to Salary Generator
$("#allEmployeeCode").change(function (e) {
	   var employeeCode = $(this).val();
	  // alert(employeeCode);

	$.ajax({
      type: "get",
      url: "/employee/getEmp/" + e.target.value,
      success: function (data) {
        var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);

      			$("#salary-empCode").val(jsonobject.employeeCode);
				$("#salary-empName").val(jsonobject.empName);
				$("#salary-basicSalary").val(jsonobject.basicSalary);
				$("#salary-panNO").val(jsonobject.panNO);
				$("#salary-designation").val(jsonobject.designation);									   							
				$("#salary-esiNumber").val(jsonobject.esiNumber);   
				$("#salary-pfNo").val(jsonobject.pfNo); 
		        $("#salary-contactNo").val(jsonobject.contactNo); 
		        $("#salary-dateOfJoin").val(jsonobject.dateOfJoin); 
		        $("#salary-bankAccNo").val(jsonobject.bankAccNo); 
		        
		        
		        $("#salary-empSalary").val(jsonobject.salary); 
		        var empTotalSal =$("#salary-empSalary").val();
		       // alert(empTotalSal);
		        
		        
		        var basicSalary = $("#salary-basicSalary").val();
		         // alert(basicSalary);
		          
		         // DA Value
				 var empda = parseFloat (empTotalSal * 0.1);
		        // alert(empda);
		         
		       	// HRA value
				var emphra = parseFloat (basicSalary) + parseFloat (empda);
				var salEmphra = parseFloat(emphra * 0.4);
				   $("#salary-salEmphra").val(salEmphra); 
		       // alert(salEmphra);
		       
		        // Income Tax
		         var inCometax = parseFloat(0);   
			    $("#salary-inCometax").val(inCometax); 
			    
			    // Conveyance
			    var empCon = parseFloat(1600);
			    $("#salary-empCon").val(empCon); 
			    
			    // Medical
			     var empMA = parseFloat(1250);
			    $("#salary-empMA").val(empMA); 
			   // alert(empMA);
			   
			    // Spl Allow Value
		     	 var empSplAllow1 = parseFloat(empTotalSal) - parseFloat(emphra) 
		     	 var empSplAllow2 = parseFloat(empSplAllow1) - parseFloat(salEmphra)
		     	 var empSplAllow3 = parseFloat(empSplAllow2) - parseFloat(empCon) 
		         var empSplAllow = parseFloat(empSplAllow3) - parseFloat(empMA) 
			     $("#salary-empSplAllow").val(empSplAllow); 
			    
			    // LTA Allow
			    var lta = parseFloat(0);
			    $("#salary-lta").val(lta); 
			   
			   // PF Value
				var empPf = parseFloat (emphra * 0.12); 
				//alert(empPf);
				$("#salary-empPf").val(empPf);
      },
      error: function (e) {
        console.log("error occured while fetching Item");
      },
      });
      
      
// getting Working days
    var employeeCode = $("#allEmployeeCode").val();
      
       $.ajax({
       type: "get",
       url: "/employee/total-leaves/" + employeeCode,
       success: function (data) {
	    
	      var json = JSON.stringify(data);
        var jsonobject = JSON.parse(json);
        
         $("#totalSacnSickLeave").val(jsonobject.sacnSickLeave);
         $("#totalSacnCausalLeave").val(jsonobject.sacnCausalLeave);
         $("#totalSacnLwp").val(jsonobject.sacnLwp);
         
          var totalSanLeave =  parseInt(jsonobject.sacnSickLeave) + parseInt(jsonobject.sacnCausalLeave)  + parseInt(jsonobject.sacnLwp);
            $("#totalSanEmpLeave").val(totalSanLeave);
  
		 var currentDate = new Date();
         var daysInMonth = new Date(currentDate.getFullYear(), currentDate.getMonth()+1, 0).getDate(); 
         //alert(daysInMonth);
        
         $("#totalWorkingDays").val(daysInMonth);
       
         var earingDays = daysInMonth - totalSanLeave;
         // alert(earingDays);
         $("#totalEaringDays").val(earingDays);
         
       $("#salary-empBasicSalary").val("");
       $("#actualSalary").val("");
       $("#salary-pfAmount").val("");
       $("#slary-Grosspay").val("");
       $("#salary-da").val("");
       $("#salary-empda").val("");
       $("#salary-esi").val("");
       $("#salary-GrossDeduct").val("");
       $("#salary-hra").val(""); 
       $("#salary-emphra").val("");
       $("#salary-profTax").val("");
       $("#salary-netpay").val("");
       $("#salary-convencyance").val("");
       $("#salary-empConv").val("");
       $("#salary-tds").val("");
       $("#salary-medical").val("");
       $("#salary-employeeMA").val("");
       $("#salary-splAllow").val("");
       $("#salary-empSpl").val("");
       $("#salary-telAllow").val("");
       $("#salary-emplta").val("");
       $("#totalEarnings").val("");
       $("#totalActualEarn").val("");
       $("#totalDeduction").val("");
       $("#salary-totalnetpay").val("");
        }
    });


   $('#salary-Generator').click(function(){  
	    	  // total Days in month
	    	  var totaldays = $("#totalWorkingDays").val();
	//alert(totaldays);
	    	  // Total earningDay
	    	  var earningDay = $("#totalEaringDays").val();
	    	  
	    	   // BasicSalary value 
	    	  var basicSalary = $("#salary-basicSalary").val();
	    	  $("#salary-empBasicSalary").val(basicSalary);
	    	
	    	// Total salary
	    	var empsalary = $("#salary-empSalary").val();
	    	
	    	 // DA value
	    	 var da = parseFloat(empsalary * 0.1);
	    	 $("#salary-da").val(da);

	    	 // HRA value
	    	 var hra = $("#salary-salEmphra").val();
	    	 $("#salary-hra").val(hra);

	    	   // Conveyance value
	    	   var empConv = $("#salary-empCon").val();
	    	   $("#salary-convencyance").val(empConv);

	    	   // Medical value
	    	   var empMA = parseFloat(1250);
			   $("#salary-medical").val(empMA); 
	
	    	    // SplAllow value
	    	    var empspl = $("#salary-empSplAllow").val();
	    	    $("#salary-splAllow").val(empspl);

	    	    // Total Earnings value
	    	    var totalEarn = parseFloat(basicSalary) + parseFloat(da) +parseFloat(hra) +
                                parseFloat(empConv) +parseFloat(empMA) +parseFloat(empspl) ;
	    	    $("#totalEarnings").val(totalEarn);
	    	    
	// ************************  Actual Earnings ********************************* 
	
	//alert('empsalary'+empsalary);
             // empActualSalary value
	    	var earningSalary =  parseFloat((empsalary / totaldays)* earningDay).toFixed(2);
	    	//alert('empActualSalary'+earningSalary);
	    	
	    	
	    	var leaveDeductSal = parseFloat(empsalary ) - parseFloat(earningSalary).toFixed(2);
	    	//alert(leaveDeductSal);

            var leaveAmount = parseFloat(leaveDeductSal / 3).toFixed(2);
            //alert(leaveAmount);

	    	  // actualSalary value
	    	 var actualSalary = parseFloat(basicSalary - leaveAmount).toFixed(2)
	    	// alert('actualSalary'+actualSalary);
	    	$("#actualSalary").val(actualSalary);
	    	
	    	 // DA value
	    	$("#salary-empda").val(da);
	    	
	    	 // HRA value
	    	 var empHra = parseFloat(hra - leaveAmount).toFixed(2);
	    	
	    	$("#salary-emphra").val(empHra);
	    	
	        // Conveyance value
	        $("#salary-empConv").val(empConv);
	        
	        // Medical value
	          $("#salary-employeeMA").val(empMA);
	          
	       // SPL Allow value
	    	 var empsplAllow = parseFloat(empspl - leaveAmount).toFixed(2);
	    	 $("#salary-empSpl").val(empsplAllow);
	    	// alert(empsplAllow);
	    	 
	       // telAllow value
	        var telAllow = parseFloat(0);
	        $("#salary-telAllow").val(telAllow);
	        
	      // lta value
	         var lta = parseFloat(0);
	         $("#salary-emplta").val(lta);
	         
	         // Actual Earnings
	        var totalActualEarn = parseFloat(actualSalary) + parseFloat(da) + parseFloat(empHra) +
                                   parseFloat(empConv) + parseFloat(empMA) + parseFloat(empsplAllow) + 
                                   parseFloat(telAllow) + parseFloat(lta) ;
                   var empActualEarn   =  parseFloat(totalActualEarn).toFixed(2);            
              /* var totalActualEarn = parseFloat(actualSalary + da + empHra + empConv + empMA + empsplAllow + telAllow + lta);  */             
              $("#totalActualEarn").val(empActualEarn);             
		
// ************************  Deductions ********************************* 
		
		 // PF value
		var empPf = parseFloat(basicSalary) + parseFloat(da);
		var empPfAmount = parseFloat(empPf * 0.12).toFixed(2)
		// alert(empPfAmount);
		$("#salary-pfAmount").val(empPfAmount);
		
		//alert('empsalary'+empsalary);
		// ESI value
		 
		       	 if(21000 <= empsalary){
					  var  empEsi = parseFloat (empsalary * 0.0075);	
				  }else{
					   var  empEsi=0;
					   }
				$("#salary-esi").val(empEsi);	  
					
		// PT Value
		   if(15000 <= empsalary){
			   var empTax = 200;
			}else{
				  var empTax = 150;
				 }
		  $("#salary-profTax").val(empTax);
		  
		  
 $("#salary-tds").keyup(function(){
		  var tds = $("#salary-tds").val();
		
		 var empPfval = $("#salary-pfAmount").val();
		 var empEsival = $("#salary-esi").val();
		 var empProTax = $("#salary-profTax").val();
	
		  var totalDeduction = parseFloat(empPfval) + parseFloat(empEsival) + parseFloat(empProTax)+parseFloat(tds);
		 $("#totalDeduction").val(totalDeduction);
		 
// ************************  NetPay ********************************* 	   
		  var empGrosspay = $("#totalActualEarn").val();  
			  $("#slary-Grosspay").val (empGrosspay);
		 
		 	var empGrossDeduct = $("#totalDeduction").val();
			$("#salary-GrossDeduct").val(empGrossDeduct);
			
			var netpay = parseFloat(empGrosspay - empGrossDeduct).toFixed(2);
			$("#salary-netpay").val(netpay);
			$("#salary-totalnetpay").val(netpay);
	});	
			
	  });
  
});

});

/*function DataForEmpInspct(employeeId){
	alert(employeeId);
	
			$.ajax({
				type:"get",
				url:"/employee/getEmpData/"+employeeId,
				success:function(data){                 
				var json = JSON.stringify(data);	    	
				var jsonobject = JSON.parse(json);												
				$("#inspct-employeeCode").val(jsonobject.employeeCode);
				$("#inspct-empName").val(jsonobject.empName);
				$("#inspct-department").val(jsonobject.department);
				$("#inspct-fatherName").val(jsonobject.fatherName);
				$("#inspct-houseNo").val(jsonobject.houseNo);
				$("#inspct-contactNo").val(jsonobject.contactNo);
				$("#inspct-designation").val(jsonobject.designation);
				$("#inspct-bankAccNo").val(jsonobject.bankAccNo);
				$("#inspct-ifscCode").val(jsonobject.ifscCode);
				$("#inspct-dateOfJoin").val(jsonobject.dateOfJoin);
				$("#inspct-bankName").val(jsonobject.bankName);
				$("#inspct-aadharNo").val(jsonobject.aadharNo);
				$("#inspct-passportNo").val(jsonobject.passportNo);
				$("#inspct-dlNo").val(jsonobject.dlNo);
				$("#inspct-pfNo").val(jsonobject.pfNo);
				$("#inspct-panNO").val(jsonobject.panNO);
				$("#inspct-esiNumber").val(jsonobject.esiNumber);	
				$("#inspct-refContactNo").val(jsonobject.refContactNo);	
				$("#inspct-address").val(jsonobject.address);								   
				
		  }
		  
		}); 
	
}*/

/*function DataForApvrlLeave(empLeaveId){
	alert(empLeaveId);
	
			$.ajax({
				type:"get",
				url:"/employee/getEmpLeaveData/"+empLeaveId,
				success:function(data){                 
				var json = JSON.stringify(data);	    	
				var jsonobject = JSON.parse(json);												
				$("#aprl-employeeId").val(jsonobject.employeeId);
				$("#aprl-empName").val(jsonobject.empName);
				$("#aprl-designation").val(jsonobject.designation);
				$("#aprl-department").val(jsonobject.department);
				$("#aprl-fatherName").val(jsonobject.fatherName);
				$("#aprl-sickLeave").val(jsonobject.sickLeave);
				$("#aprl-causalLeave").val(jsonobject.causalLeave);
				$("#aprl-annualLwp").val(jsonobject.annualLwp);
				$("#aprl-sacnSickLeave").val(jsonobject.sacnSickLeave);
				$("#aprl-sacnCausalLeave").val(jsonobject.sacnCausalLeave);
				$("#aprl-sacnLwp").val(jsonobject.sacnLwp);
				$("#aprl-deptHead").val(jsonobject.deptHead);
				$("#aprl-remark").val(jsonobject.remark);
				$("#aprl-leaveDate").val(jsonobject.leaveDate);
				$("#aprl-noOfLeave").val(jsonobject.noOfLeave);
				$("#aprl-leaveType").val(jsonobject.leaveType);
				
		  }
		  
		}); 
	
}

*/

