$.getJSON('/stocks/dashboard/total', function(data) {

	const monthNames = [];
	const quantities = [];

	data.forEach((item) => {
		monthNames.push(item.monthName);
		quantities.push(item.totalQuantity);
	});

	// Render the chart
	const chart = Highcharts.chart('monthwise-total-stocks', {
		title: {
			text: 'Month Wise Total Stocks',
			align: 'center'
		},
		xAxis: {
			categories: monthNames
		}, yAxis: {
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