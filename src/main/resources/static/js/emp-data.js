
function sum() {

	var sl = document.getElementById('sl').value;
	var cl = document.getElementById('cl').value;
	var lwp = document.getElementById('lwp').value;
	var result = parseInt(sl) + parseInt(cl) + parseInt(lwp);
	if (!isNaN(result)) {
		document.getElementById('totalLeave').value = result;
	}
}



function sal() {

	var salary = document.getElementById('salary').value;
	var result = (salary * 0.3);
	if (!isNaN(result)) {
		document.getElementById('basicSalary').value = result;
	}
}

function sumEditLeave() {

	var slCopy = document.getElementById('slCopy').value;
	var clCopy = document.getElementById('clCopy').value;
	var lwpCopy = document.getElementById('lwpCopy').value;
	var result1 = parseInt(slCopy) + parseInt(clCopy) + parseInt(lwpCopy);
	if (!isNaN(result1)) {
		document.getElementById('totalLeaveCopy').value = result1;
	}
}

function editSal() {

	var salaryCopy = document.getElementById('salaryCopy').value;
	var result = (salaryCopy * 0.3);
	if (!isNaN(result)) {
		document.getElementById('basicSalaryCopy').value = result;
	}
}



//function for Getting Employee Code Data by Dept

$("#changeDepartmentName").change(function(e) {
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
		success: function(data) {
			var json = JSON.stringify(data);
			var result = JSON.parse(json);
			for (var i = 0; i < result.length; i++) {
				s +=
					'<option>' + result[i].employeeCode + "</option>";
			}
			$("#allEmployeeCode").html(s);
		},
	});
});


//function for Getting ComapnyName  by BranchName

$("#ChangeBranchName").change(function(e) {
	var branch = $(this).val();

	// alert(branch);

	$.ajax({
		type: "get",
		url: "/employee/getCompanyByBranch/" + branch,
		success: function(data) {

			var json = JSON.stringify(data);
			var jsonobject = JSON.parse(json);

			$("#company-Name").val(jsonobject.company);
			//  alert(jsonobject.company);
		}
	});
});

$("#ChangeDeptName").change(function(e) {
		var department = $(this).val();
		//alert(department);
		
		$.ajax({
			type: "get",
			url: "/employee/getCategoryByDept/"+department,
			success : function(data){
			var json =	      JSON.stringify(data);
			var jsonobject  = JSON.parse(json);                   
			 $("#emp-category").val(jsonobject.empCategory);
			 alert(jsonobject.empCategory);
			 
			}
		});
	
	});