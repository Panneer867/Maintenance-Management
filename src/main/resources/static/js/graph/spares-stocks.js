$.getJSON('/stocks/graph/spares/chart', function(data) {

	const categories = data.map(item => item.category);
	const quantities = data.map(item => item.totalQuantity);

	const sum = quantities.reduce((accumulator, currentValue) => {
		return accumulator + currentValue;
	}, 0);

	Highcharts.chart('spares-stocks', {
		title: {
			text: 'Inward Spares',
			align: 'center'
		},
		xAxis: {
			categories: categories
		},
		yAxis: {
			title: {
				text: 'Qunatities'
			}
		},
		tooltip: {
			valueSuffix: ' Inward Spares'
		},
		plotOptions: {
			column: {
				color: '#b5543c' // Set the color of the columns to red
			}
		},
		series: [{
			type: 'column',
			name: "Spares",
			data: quantities
		}]
	});

});