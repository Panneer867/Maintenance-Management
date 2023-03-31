$.getJSON('/stocks/graph/return/chart', function(data) {

	const categories = data.map(item => item.category);
	const quantities = data.map(item => item.totalQuantity);

	const sum = quantities.reduce((accumulator, currentValue) => {
		return accumulator + currentValue;
	}, 0);

	Highcharts.chart('categorywise-stocks-return', {
		title: {
			text: ' Stocks Return',
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
			valueSuffix: ' Stocks'
		},
		plotOptions: {
			column: {
				color: '#851ebd' // Set the color of the columns to red
			}
		},
		series: [{
			type: 'column',
			name: "Stocks",
			data: quantities
		}]
	});

});