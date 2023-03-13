$.getJSON('/stocks/dashboard/outward', function(data) {

	console.log(data);


	Highcharts.chart('container1', {
		chart: {
			type: 'column'
		},
		title: {
			text: 'Outward Stocks'
		},
		xAxis: {
			categories: data.map(entry => entry.month_name)
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Outwards'
			}
		},
		tooltip: {
			pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
			shared: true
		},
		plotOptions: {
			column: {
				stacking: 'percent'
			}
		},
		series: [{
			name: 'Materials',
			data: data.map(entry => entry.material_quantity)
		}, {
			name: 'Spares',
			data: data.map(entry => entry.spare_quantity)
		}, {
			name: 'Tools',
			data: data.map(entry => entry.tool_quantity)
		}]
	});
});