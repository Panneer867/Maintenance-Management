$.getJSON('/dashboard/get/asset-management', function(data) {

	console.log(data);

	const transformedData = data.map(item => {
		return {
			name: item.departments,
			y: item.totalQuantity
		};
	});

	Highcharts.chart('asset-management-1', {
		chart: {
			type: 'pie'
		},
		title: {
			text: 'Asset Records',
			align: 'center'
		},
		subtitle: {
			text: 'Department Wise Assets Record',
			align: 'center'
		},

		accessibility: {
			announceNewData: {
				enabled: true
			},
			point: {
				valueSuffix: '%'
			}
		},

		plotOptions: {
			series: {
				dataLabels: {
					enabled: true,

				}
			}
		},

		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> Assets<br/>'
		},

		series: [
			{
				name: 'Department',
				colorByPoint: true,
				data: transformedData
			}
		],

	});

});