$.getJSON('/stocks/dashboard/outward', function(data) {
	
	//console.log(data)

	Highcharts.chart('outward-workorder-stocks', {
		chart: {
			type: 'column'
		},
		title: {
			text: 'Outward Stocks'
		},
		xAxis: {
			categories: data.map(entry => entry.monthName)
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Quantities'
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
			name: 'MATERIALS',
			data: data.map(entry => entry.materialsQuantity)
		}, {
			name: 'SPARES',
			data: data.map(entry => entry.sparesQuantity)
		}, {
			name: 'TOOLS',
			data: data.map(entry => entry.toolsQuantity)
		}]
	});
});