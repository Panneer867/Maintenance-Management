$.getJSON('/dashboard/get/approved/workorder-management', function(data) {
  
  const departments = data.map(item => item.departments);
	const count = data.map(item => item.count);
	
Highcharts.chart('work-order-2', {
  colors: ['#00e6c0', '#00FF00', '#0000FF'],
  chart: {
    type: 'cylinder',
    options3d: {
      enabled: true,
      alpha: 0,
      beta: 0,
      depth: 50,
      viewDistance: 25
    }
  },
  title: {
    text: 'Approved Work Orders'
  },
  subtitle: {
    text: 'Department wise approved work orders'
  },
  xAxis: {
    categories: departments
 
  },
  yAxis: {
    title: {
      margin: 20,
      text: 'Work Orders'
    },
     allowDecimals: false
  },
  tooltip: {
    headerFormat: '<b>{point.x}</b><br>'
  },
  plotOptions: {
    series: {
      depth: 25,
      colorByPoint: true
    }
  },
  series: [{
    data: count,
    name: 'Work Orders',
    showInLegend: false
  }]
});
});