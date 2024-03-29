$.getJSON('/stocks/graph/materials/chart', function(data) {

	const categories = data.map(item => item.category);
	const quantities = data.map(item => item.totalQuantity);

	const sum = quantities.reduce((accumulator, currentValue) => {
		return accumulator + currentValue;
	}, 0);

	Highcharts.chart('materials-stocks', {
		title: {
			text: 'Inward Materials',
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
			valueSuffix: ' Inward Materials'
		},
		plotOptions: {
			column: {
				color: '#1ebdad' // Set the color of the columns to red
			}
		},
		series: [{
			type: 'column',
			name: "Materials",
			data: quantities
		}]
	});

});