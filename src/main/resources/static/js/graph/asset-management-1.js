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
    text: 'Division Wise Assets Record',
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
      name: 'Division',
      colorByPoint: true,
      data: [
        {
          name: 'Division 1',
          y: 61,
         
        },
        {
          name: 'Division 2',
          y: 9,
          
        },
        {
          name: 'Division 3',
          y: 9,
          
        },
        {
          name: 'Division 4',
          y: 8,
         
        },
        {
          name: 'Division 5',
          y: 11,
         
        }
      ]
    }
  ],
 
});