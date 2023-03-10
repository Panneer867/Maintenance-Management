$.getJSON('/graph/stocks/allmonths', function(data) {


	console.log(data)

	const monthNames = [];
	const quantities = [];

	data.forEach((item) => {
		monthNames.push(item.MONTH_NAME);
		quantities.push(item.TOTAL_QUANTITY);
	});

	// Render the chart
	const chart = Highcharts.chart('container5', {
		title: {
			text: 'Month Wise Total Stocks',
			align: 'center'
		},
		xAxis: {
			categories: monthNames
		},yAxis: {
			title: {
				text: 'Total Available Stocks'
			}
		},
		series: [{
			type: 'column',
			name: 'Total Stocks',
			colorByPoint: true,
			data: quantities,
			showInLegend: false
		}]
	});
});