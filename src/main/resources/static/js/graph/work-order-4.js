$.getJSON('/dashboard/get/cancel/workorder-management', function(data) {
  
  
   const departments = data.map(item => item.departments);
	const count = data.map(item => item.count);
	
	
	const total = departments.map((department, index) => [department, count[index]]);
	
	
Highcharts.chart('work-order-4', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: 0,
      plotShadow: false
    },
    title: {
      text: 'Cancel Work Orders',
      align: 'center'
    },
    subtitle: {
      text: 'Department wise cancelled work orders',
      align: 'center'
    },
    
    plotOptions: {
      pie: {
        dataLabels: {
          enabled: true,
          distance: -50,
          style: {
            fontWeight: 'bold',
            color: 'white'
          }
        },
        startAngle: -90,
        endAngle: 90,
        center: ['50%', '75%'],
        size: '110%'
      }
    },
    series: [{
      type: 'pie',
      name: 'Cancel Orders',
      innerSize: '60%',
      data: total
    }]
  });
});