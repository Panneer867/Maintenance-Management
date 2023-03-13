

	Highcharts.chart('container1', {
		chart: {
			type: 'column'
		},
		title: {
			text: 'Outward Stocks'
		},
		xAxis: {
			categories: []
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
			data: []
		}, {
			name: 'Spares',
			data: []
		}, {
			name: 'Tools',
			data: []
		}]
	});
