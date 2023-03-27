$.getJSON('/dashboard/get/task-management', function(data) {

	console.log(data);
	
	
	const department = Object.keys(data.departmentCount);
const count = Object.values(data.departmentCount);

	const chart = Highcharts.chart('task-management-2', {
		title: {
			text: 'Department wise Complaints',
			align: 'center'
		},
		subtitle: {
			text: 'Total No of Departments & Pending Complaints',
			align: 'center'
		},
		xAxis: {
			categories: department
		}, yAxis: {
			title: {
				text: 'Total Complaints'
			}

		},
		series: [{
			type: 'column',
			name: 'Complaints',
			colorByPoint: true,
			data: count,
			showInLegend: false
		}]
	});

});