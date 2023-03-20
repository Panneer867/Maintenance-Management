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
				text: 'Million liters'
			}
		},
		tooltip: {
			valueSuffix: ' Inward Materials'
		},
		series: [{
			type: 'column',
			name: "Materials",
			data: quantities
		}, {
			type: 'spline',
			name: 'Average',
			data: quantities,
			marker: {
				lineWidth: 2,
				lineColor: Highcharts.getOptions().colors[1],
				fillColor: 'orange'
			}
		}]
	});

});