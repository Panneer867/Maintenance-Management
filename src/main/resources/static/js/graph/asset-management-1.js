// Create the chart
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
      data: [
        {
          name: 'Department 1',
          y: 61,
         
        },
        {
          name: 'Department 2',
          y: 9,
          
        },
        {
          name: 'Department 3',
          y: 9,
          
        },
        {
          name: 'Department 4',
          y: 8,
         
        },
        {
          name: 'Department 5',
          y: 11,
         
        }
      ]
    }
  ],
 
});