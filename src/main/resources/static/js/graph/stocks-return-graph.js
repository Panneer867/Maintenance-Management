$.getJSON('/stocks/dashboard/return', function(data) {

	//console.log(data);

	const returnData = data.map(item => [item.stockType, item.totalQuantity]);

	Highcharts.chart('stocks-return-graph', {
		chart: {
			type: 'pie',
			options3d: {
				enabled: true,
				alpha: 45
			}
		},
		title: {
			text: 'Stocks Return',
			align: 'center'
		},

		plotOptions: {
			pie: {
				innerSize: 100,
				depth: 45
			}
		},
		series: [{
			name: 'Stocks',
			data: returnData
		}]
	});
});