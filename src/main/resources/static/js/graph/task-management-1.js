$.getJSON('/dashboard/get/task-management', function(data) {

	Highcharts.chart('task-management-1', {
		chart: {
			type: 'column'
		},
		title: {
			align: 'center',
			text: 'Complaints'
		},
		subtitle: {
			align: 'center',
			text: 'No. of complaints Pending in each investigation'
		},
		accessibility: {
			announceNewData: {
				enabled: true
			}
		},
		xAxis: {
			type: 'category'
		},
		yAxis: {
			title: {
				text: 'Total Complaints'
			}

		},
		legend: {
			enabled: false
		},
		plotOptions: {
			series: {
				borderWidth: 0,
				dataLabels: {
					enabled: true,

				}
			}
		},

		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> Pending<br/>'
		},

		series: [
			{
				name: '',
				colorByPoint: true,
				data: [
					{
						name: 'JEE',
						y: data.jeComplaints,

					},
					{
						name: 'AEE',
						y: data.aeeComplaints,

					},
					{
						name: 'EE',
						y: data.eeComplaints,

					},
					{
						name: 'COMMISSIONER',
						y: data.commComplaints,

					}
				]
			}
		]
	});
});