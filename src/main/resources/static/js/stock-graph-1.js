$.getJSON('/stocks/dashboard/month', function(data) {

	console.log(data);

	// Define the categories and series data arrays
	const typeCategories = ["MATERIALS", "SPARES", "TOOLS"];
	const monthCategories = [...new Set(data.map(item => item.monthName))];
	const seriesData = [];

	// Loop through the type categories and get the quantity data for each type in each month
	typeCategories.forEach((type, index) => {
		const typeData = [];
		monthCategories.forEach(month => {
			const filteredData = data.filter(item => item.monthName === month && item.stockType === type);
			const quantity = filteredData.length > 0 ? filteredData[0].totalQuantity : 0;
			typeData.push(quantity);
		});
		seriesData.push(typeData);
	});


	Highcharts.chart('container', {
		title: {
			text: 'Month Wise Stocks ',
			align: 'center'
		},
		xAxis: {
			categories: monthCategories
		},
		yAxis: {
			title: {
				text: 'Available Stocks'
			}
		},
		tooltip: {
			valueSuffix: ' Stocks'
		},
		series: [{
			type: 'column',
			name: typeCategories[0],
			data: seriesData[0]
		}, {
			type: 'column',
			name: typeCategories[1],
			data: seriesData[1]
		}, {
			type: 'column',
			name: typeCategories[2],
			data: seriesData[2]
		}]
	});
});
