$.getJSON('/dashboard/get/pending/workorder-management', function(data) {

const departments = data.map(item => item.departments);
	const count = data.map(item => item.count);
	// Set up the chart
	const chart = new Highcharts.Chart('work-order-1', {
     colors: ['#e60086', '#00FF00', '#0000FF'],
		chart: {
			renderTo: 'container',
			type: 'column',
			options3d: {
				enabled: true,
				alpha: 0,
				beta: 0,
				depth: 50,
				viewDistance: 25
			}
		},
		xAxis: {
			categories: departments,
			
		},
		yAxis: {
			title: {				
				text: 'Work Orders'
			}, 
			 allowDecimals: false
		},
		tooltip: {
			headerFormat: '<b>{point.key}</b><br>',
			pointFormat: 'Work Orders: {point.y}'
		},
		title: {
			text: 'Generate Work Orders',
			align: 'center'
		},
		subtitle: {
			text: 'Department wise pending work orders',
			align: 'center'
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			column: {
				depth: 25
			}
		},
		series: [{
			data: count,
			colorByPoint: true
		}]
	});

	
});