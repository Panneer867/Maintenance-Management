$.getJSON('/graph/stocks/inward', function(data) {
	
	console.log(data);

	const result = {};
	data.forEach((item) => {
		if (!result[item.month_name]) {
			result[item.month_name] = {};
		}
		if (!result[item.month_name][item.type]) {
			result[item.month_name][item.type] = item.total_quantity;
		}
	});
	
	console.log(result);
	
	const monthCategories = Object.keys(result);
	const typeCategories = ['MATERIALS', 'SPARES', 'TOOLS'];
	const seriesData = typeCategories.map((type) => {
		return monthCategories.map((month) => {
			return result[month][type] || 0;
		});
	});

	Highcharts.chart('container', {
		title: {
			text: 'Stocks Month Wise',
			align: 'center'
		},
		xAxis: {
			categories: typeCategories
		},
		yAxis: {
			title: {
				text: 'Stocks'
			}
		},
		tooltip: {
			valueSuffix: ' Stocks'
		},
		series: [{
			type: 'column',
			name: monthCategories[0],
			data: seriesData[0]
		}, {
			type: 'column',
			name: monthCategories[1],
			data: seriesData[1]
		}, {
			type: 'column',
			name: monthCategories[2],
			data: seriesData[2]
		}]
	});
});
