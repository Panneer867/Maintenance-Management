$(document).ready(function() {

	$('.table .edit-master').on('click', function(event) {

		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(employee) {

			$('#employeeIdCopy').val(employee.employeeId);
			$('#empCodeCopy').val(employee.employeeCode);
			$('#empNameCopy').val(employee.empName);
			$('#salaryCopy').val(employee.salary);
			$('#maritalstatusCopy').val(employee.maritalstatus);
			$('#contactNoCopy').val(employee.contactNo);
			$('#refContactNoCopy').val(employee.refContactNo);
			$('#addressCopy').val(employee.address);
			$('#stateCopy').val(employee.state);
			$('#cityCopy').val(employee.city);
			$('#pinCodeCopy').val(employee.pinCode);
			$('#bankAccNoCopy').val(employee.bankAccNo);
			$('#bankNameCopy').val(employee.bankName);
			$('#ifscCodeCopy').val(employee.ifscCode);
			$('#slCopy').val(employee.sl);
			$('#clCopy').val(employee.cl);
			$('#lwpCopy').val(employee.lwp);
			$('#dlNoCopy').val(employee.dlNo);
			$('#basicSalaryCopy').val(employee.basicSalary);
			$('#totalLeaveCopy').val(employee.totalLeave);
		});

		$('#editMaster').modal();
	});

});