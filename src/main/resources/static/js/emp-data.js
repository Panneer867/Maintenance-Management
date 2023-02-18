  
   function sum() {
	  
      var sl = document.getElementById('sl').value;
      var cl = document.getElementById('cl').value;
      var lwp = document.getElementById('lwp').value;
      var result = parseInt(sl) + parseInt(cl)+ parseInt(lwp);
      if (!isNaN(result)) {
         document.getElementById('totalLeave').value = result;
      }
}



 function sal() {
   
      var salary = document.getElementById('salary').value;
      var result =(salary * 0.3) ;
      if (!isNaN(result)) {
         document.getElementById('basicSalary').value = result;
      }
}

  function sumEditLeave() {
	  
      var slCopy = document.getElementById('slCopy').value;
      var clCopy = document.getElementById('clCopy').value;
      var lwpCopy = document.getElementById('lwpCopy').value;
      var result1 = parseInt(slCopy) + parseInt(clCopy)+ parseInt(lwpCopy);
      if (!isNaN(result1)) {
         document.getElementById('totalLeaveCopy').value = result1;
      }
}
 
 function editSal() {
   
      var salaryCopy = document.getElementById('salaryCopy').value;
      var result =(salaryCopy * 0.3) ;
      if (!isNaN(result)) {
         document.getElementById('basicSalaryCopy').value = result;
      }
}



/*function sendAjaxRequest() {
    var department = $("#department").val();
    $.get( "/employee/regions?department=" + department, function( data ) {
        $("#employeeCode").empty();
        data.forEach(function(item, i) {
            var option = "<option value = " + item + ">" + item +  "</option>";
            $("#employeeCode").append(option);
        });
    });
};
*/


//function for Getting Employee Code Data by Dept

  $("#changeDepartmentName").change(function (e) {
         var department = $(this).val();
         //alert(department);
         if (e.target.value === "") {
        $("#changeDepartmentName").val("");
        alert("Select the department");
        return false;
        }

    var s = '<option value=" ">Select</option>';
   $.ajax({
      type: "get",
      url: "/employee/getEmployeeCodeByDept/" + department,
      data: { departmentName: department },
      success: function (data) {
        var json = JSON.stringify(data);
        var result = JSON.parse(json);
        for (var i = 0; i < result.length; i++) {
          s +=
            '<option>' +result[i].employeeCode +"</option>";
        }
        $("#allEmployeeCode").html(s);
      },
    });
  });
