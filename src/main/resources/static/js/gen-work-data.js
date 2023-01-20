$(document).ready(function() {

//Add Catrgory Materials/Parts			 	
var count = 0;
var s = " ";
const categoryData = [];
$("#gen-add-category").click(function () {
	 
  var categoryName = $("#category").val();
  var itemName = $("#item").val();
  var hsncode = $("#hsnCode").val();
  var unit = $("#unitMeassure").val();
  var qantity = $("#gentrMaterialQty").val();
  var amount = $("#gentrMaterialAmount").val();

   
  const bundle = {
	category:categoryName,
	item:itemName,
	hsnCode:hsncode,
	unitMeassure:unit,
	gentrMaterialQty:qantity,
	gentrMaterialAmount:amount
  }
 
   categoryData.push(bundle);
   console.log(categoryData);

    count = count + 1;
        
     if (amount) {
      s +=
        "<tr>" +
        "<td>" +count +"</td>" +
        '<td>' + categoryName + "</td>" +
        '<td>' +itemName +"</td>" +
        '<td>' + hsncode +"</td>" +
        '<td>' + unit +"</td>" +
        '<td>' + qantity +"</td>" +
        '<td>' + amount +"</td>" +
        "</tr>";
    }
    $(".category-item-display").html(s); 

  });

  //save the data in category 
 $("#gen-submit").click(function () {
	
	$("#gen-labour-work-btn").show();
	
 var indentNo = $("#indentNo").val();
 var indentDepartement = $("#indentDepartement").val();
var genWorkOrderDepartment = $("#genWorkOrderDepartment").val();
 var workOrderNumber = $("#workOrderNumber").val();
 var workOrderDate = $("#workOrderDate").val();
 var division = $("#division").val();
 var subDivision = $("#subDivision").val();
 var workOrderScope = $("#workOrderScope").val();
 var workOrderIssuBy = $("#workOrderIssuBy").val();
 var workPriority = $("#workPriority").val();
 var workOrderCost = $("#workOrderCost").val();
 var contact = $("#contact").val();
 var workSite = $("#workSite").val();
 var expectedStartDate = $("#expectedStartDate").val();
 var expectedEndDate = $("#expectedEndDate").val();
 
    var createBundleInput = {
	    		    indentNo:indentNo,
	    		    indentDepartement:indentDepartement,
					genWorkOrderDepartment:genWorkOrderDepartment,
	    		    workOrderNumber:workOrderNumber,
					workOrderDate:workOrderDate,
					division:division,
					subDivision:subDivision,
					workOrderScope:workOrderScope,
					workOrderIssuBy:workOrderIssuBy,
					workPriority:workPriority,
					workOrderCost:workOrderCost,
					contact:contact,
					workSite:workSite,
					expectedStartDate:expectedStartDate,
					expectedEndDate:expectedEndDate,
	    		    items: categoryData,
	    		 };

       $.ajax({
	        url: "/workorder/createBundle",
	        method: "POST",
	        data: JSON.stringify(createBundleInput),
	        contentType: "application/json; charset=utf-8;",
	        success: function (data) {

	           alert("Data Saved");
	        },
			error: function () {
				console.log("error occus");
			  },
			complete: function () {
				console.log("Completed");
			  },	   
	    });

  });


//Add Employees And Labours /Time
var count = 0;
var emp = " ";
const emplLabourData = [];
$("#gen-add-employees").click(function () {
	  var employeeCategorty = $("#employeeCategorty").val();
	  var labourQty = $("#labourQty").val();
	  var days = $("#days").val();
	  var time = $("#time").val();
	  var totalHours = $("#totalHours").val();

		const laboursbundle = {
			employeeCategorty:employeeCategorty,
			labourQty:labourQty,
			days:days,
			time:time,
			totalHours:totalHours
		  }

	emplLabourData.push(laboursbundle);
	   console.log(emplLabourData);
	
	 count = count + 1;

     if (totalHours) {
      emp +=
        "<tr>" +
        "<td>" +count +"</td>" +
        '<td>' + employeeCategorty + "</td>" +
        '<td>' +labourQty +"</td>" +
        '<td>' + days +"</td>" +
        '<td>' + time +"</td>" +
        '<td>' + totalHours +"</td>" +
        "</tr>";
    }
 $(".employees-labour-display").html(emp); 

});


 //save the data in Employee Labour
 $("#gen-emplabours-submit").click(function () {
	
	$("#gen-vehicle-work-btn").show();
	// @ts-ignore
	$("#gen-category-work-btn").disable();

	
	 var indentNo = $("#indentNo").val();
	 var indentDepartement = $("#indentDepartement").val();
	 var genWorkOrderDepartment = $("#genWorkOrderDepartment").val();
	 var workOrderNumber = $("#workOrderNumber").val();
	 var workOrderDate = $("#workOrderDate").val();
	 var division = $("#division").val();
	 var subDivision = $("#subDivision").val();
	 var workOrderScope = $("#workOrderScope").val();
	 var workOrderIssuBy = $("#workOrderIssuBy").val();
	 var workPriority = $("#workPriority").val();
	 var workOrderCost = $("#workOrderCost").val();
	 var contact = $("#contact").val();
	 var workSite = $("#workSite").val();
	 var expectedStartDate = $("#expectedStartDate").val();
	 var expectedEndDate = $("#expectedEndDate").val();

			var empLabourInput  = {
	    		    indentNo:indentNo,
	    		    indentDepartement:indentDepartement,
					genWorkOrderDepartment:genWorkOrderDepartment,
	    		    workOrderNumber:workOrderNumber,
					workOrderDate:workOrderDate,
					division:division,
					subDivision:subDivision,
					workOrderScope:workOrderScope,
					workOrderIssuBy:workOrderIssuBy,
					workPriority:workPriority,
					workOrderCost:workOrderCost,
					contact:contact,
					workSite:workSite,
					expectedStartDate:expectedStartDate,
					expectedEndDate:expectedEndDate,
	    		    genLabour: emplLabourData,
	    		 };

  console.log(empLabourInput);
	
	   $.ajax({
	        url: "/workorder/createLabourBundle",
	        method: "POST",
	        data: JSON.stringify(empLabourInput),
	        contentType: "application/json; charset=utf-8;",
	        success: function (data) {

	           alert("Data Saved");
	        },
			error: function () {
				console.log("error occus");
			  },
			complete: function () {
				console.log("Completed");
			  },	   
	    });
			
});



//Add Vehicle /Timing 
var count = 0;
var veh = " ";
const vehicleData = [];
$("#gen-add-vehicle").click(function () {
	  var vehicleType = $("#vehicleType").val();
	  var vehicleNumber = $("#vehicleNumber").val();
	  var driverName = $("#driverName").val();
	  var driverContactNumber = $("#driverContactNumber").val();
	  var odoMeterReading = $("#odoMeterReading").val();
	  var startTime = $("#startTime").val();
	   alert(vehicleType);

const vehiclebundle = {
			vehicleType:vehicleType,
			vehicleNumber:vehicleNumber,
			driverName:driverName,
			driverContactNumber:driverContactNumber,
			odoMeterReading:odoMeterReading,
			startTime:startTime
		  }
		
		vehicleData.push(vehiclebundle);
		   console.log(vehicleData);
	
	 count = count + 1;
		
		if (startTime) {
      veh +=
        "<tr>" +
        "<td>" +count +"</td>" +
        '<td>' + vehicleType + "</td>" +
        '<td>' +vehicleNumber +"</td>" +
        '<td>' + driverName +"</td>" +
        '<td>' + driverContactNumber +"</td>" +
        '<td>' + odoMeterReading +"</td>" +
		'<td>' + startTime +"</td>" +
        "</tr>";
    }
	 $(".vehicle-labour-display").html(veh); 

});

 //save the data in Employee Labour
 $("#gen-vehicle-submit").click(function () {
	
	
	 var indentNo = $("#indentNo").val();
	 var indentDepartement = $("#indentDepartement").val();
	 var genWorkOrderDepartment = $("#genWorkOrderDepartment").val();
	 var workOrderNumber = $("#workOrderNumber").val();
	 var workOrderDate = $("#workOrderDate").val();
	 var division = $("#division").val();
	 var subDivision = $("#subDivision").val();
	 var workOrderScope = $("#workOrderScope").val();
	 var workOrderIssuBy = $("#workOrderIssuBy").val();
	 var workPriority = $("#workPriority").val();
	 var workOrderCost = $("#workOrderCost").val();
	 var contact = $("#contact").val();
	 var workSite = $("#workSite").val();
	 var expectedStartDate = $("#expectedStartDate").val();
	 var expectedEndDate = $("#expectedEndDate").val();

			var genVehicleInput  = {
	    		    indentNo:indentNo,
	    		    indentDepartement:indentDepartement,
					genWorkOrderDepartment:genWorkOrderDepartment,
	    		    workOrderNumber:workOrderNumber,
					workOrderDate:workOrderDate,
					division:division,
					subDivision:subDivision,
					workOrderScope:workOrderScope,
					workOrderIssuBy:workOrderIssuBy,
					workPriority:workPriority,
					workOrderCost:workOrderCost,
					contact:contact,
					workSite:workSite,
					expectedStartDate:expectedStartDate,
					expectedEndDate:expectedEndDate,
	    		    genVehicle: vehicleData,
	    		 };

  console.log(genVehicleInput);

	   $.ajax({
	        url: "/workorder/createVehicleBundle",
	        method: "POST",
	        data: JSON.stringify(genVehicleInput),
	        contentType: "application/json; charset=utf-8;",
	        success: function (data) {

	           alert("Data Saved");
	        },
			error: function () {
				console.log("error occus");
			  },
			complete: function () {
				console.log("Completed");
			  },	   
	    });
	
});

//load page
/* $(".click").click(function () {
	location.reload();*/
  /*  var indentNo = $("#indentNo").val();
 console.log(indentNo);*/
/*});*/
	
	//get tab data category to EmpLabours
$("#gen-labour-work-btn").click(function () {
	 var indentNo = $("#indentNo").val();
	 var indentDepartement = $("#indentDepartement").val();
	 var genWorkOrderDepartment = $("#genWorkOrderDepartment").val();
	 var workOrderNumber = $("#workOrderNumber").val();
	 var workOrderDate = $("#workOrderDate").val();
	 var division = $("#division").val();
	 var subDivision = $("#subDivision").val();
	 var workOrderScope = $("#workOrderScope").val();
	 var workOrderIssuBy = $("#workOrderIssuBy").val();
	 var workPriority = $("#workPriority").val();
	 var workOrderCost = $("#workOrderCost").val();
	 var contact = $("#contact").val();
	 var workSite = $("#workSite").val();
	 var expectedStartDate = $("#expectedStartDate").val();
	 var expectedEndDate = $("#expectedEndDate").val();

		    
		$("#indentNo-labour").val(indentNo);
		$("#indentdepartement-labour").val(indentDepartement);
		$("#workdepartment-labour").val(genWorkOrderDepartment);
		$("#workrdernumber-labour").val(workOrderNumber);
		$("#workdate-labour").val(workOrderDate);
		$("#division-labour").val(division);
		$("#subdivision-labour").val(subDivision);
		$("#workorderscope-labour").val(workOrderScope);
		$("#workorderissuby-labour").val(workOrderIssuBy);
		$("#workpriority-labour").val(workPriority);
		$("#workordercost-labour").val(workOrderCost);
		$("#contact-labour").val(contact);
		$("#worksite-labour").val(workSite);
		$("#expectedstartdate-labour").val(expectedStartDate);
		$("#expectedenddate-labour").val(expectedEndDate);

	 
});
 
//get tab data EmpLabours to vehicle
$("#gen-vehicle-work-btn").click(function () {
	 var indentNo = $("#indentNo").val();
	 var indentDepartement = $("#indentDepartement").val();
	 var genWorkOrderDepartment = $("#genWorkOrderDepartment").val();
	 var workOrderNumber = $("#workOrderNumber").val();
	 var workOrderDate = $("#workOrderDate").val();
	 var division = $("#division").val();
	 var subDivision = $("#subDivision").val();
	 var workOrderScope = $("#workOrderScope").val();
	 var workOrderIssuBy = $("#workOrderIssuBy").val();
	 var workPriority = $("#workPriority").val();
	 var workOrderCost = $("#workOrderCost").val();
	 var contact = $("#contact").val();
	 var workSite = $("#workSite").val();
	 var expectedStartDate = $("#expectedStartDate").val();
	 var expectedEndDate = $("#expectedEndDate").val();

    alert(indentNo);

		$("#indentNo-vehicle").val(indentNo);
		$("#indentdepartement-vehicle").val(indentDepartement);
		$("#workdepartment-vehicle").val(genWorkOrderDepartment);
		$("#workrdernumber-vehicle").val(workOrderNumber);
		$("#workdate-vehicle").val(workOrderDate);
		$("#division-vehicle").val(division);
		$("#subdivision-vehicle").val(subDivision);
		$("#workorderscope-vehicle").val(workOrderScope);
		$("#workorderissuby-vehicle").val(workOrderIssuBy);
		$("#workpriority-vehicle").val(workPriority);
		$("#workordercost-vehicle").val(workOrderCost);
		$("#contact-vehicle").val(contact);
		$("#worksite-vehicle").val(workSite);
		$("#expectedstartdate-vehicle").val(expectedStartDate);
		$("#expectedenddate-vehicle").val(expectedEndDate);
	
});

});