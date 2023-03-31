$.getJSON('/stocks/graph/tools/chart', function(data) {

	const categories = data.map(item => item.category);
	const quantities = data.map(item => item.totalQuantity);

	const sum = quantities.reduce((accumulator, currentValue) => {
		return accumulator + currentValue;
	}, 0);

	Highcharts.chart('tools-stocks', {
		title: {
			text: 'Inward Tools',
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
			valueSuffix: ' Inward Tools'
		},
		plotOptions: {
			column: {
				color: '#1ebd6d' // Set the color of the columns to red
			}
		},
		series: [{
			type: 'column',
			name: "Tools",
			data: quantities
		}]
	});

});