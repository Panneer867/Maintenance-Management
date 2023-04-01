$.getJSON('/dashboard/get/hold/workorder-management', function(data) {
  
   const departments = data.map(item => item.departments);
	const count = data.map(item => item.count);
	
	
	const total = departments.map((department, index) => [department, count[index]]);
Highcharts.chart('work-order-3', {
   colors: ['#0000FF', '#00FF00', '#00e6c0'],
  chart: {
    type: 'column'
  },
  title: {
    text: 'Hold Work Orders'
  },
  subtitle: {
    text: 'Department wise holding work orders'
  },
  xAxis: {
    type: 'category',
    labels: {
     
      style: {
        fontSize: '13px',
        fontFamily: 'Verdana, sans-serif'
      }
    }
  },
  yAxis: {
    min: 0,
    title: {
      text: 'Work Orders'
    },
    allowDecimals: false
  },
  legend: {
    enabled: false
  },
  tooltip: {
    pointFormat: 'Work Orders: <b>{point.y} holds</b>'
  },
  series: [{
  
    data: total,
    dataLabels: {
      enabled: true,
      rotation: -90,
      color: '#FFFFFF',
      align: 'right',
      format: '{point.y}', // one decimal
      y: 10, // 10 pixels down from the top
      style: {
        fontSize: '13px',
        fontFamily: 'Verdana, sans-serif'
      }
    }
  }]
});
});