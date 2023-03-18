// Data retrieved from https://www.ssb.no/energi-og-industri/olje-og-gass/statistikk/sal-av-petroleumsprodukt/artikler/auka-sal-av-petroleumsprodukt-til-vegtrafikk
Highcharts.chart('materials-stocks', {
  title: {
    text: 'Inward Materials',
    align: 'center'
  },
  xAxis: {
    categories: ['Jet fuel', 'Duty-free diesel', 'Petrol', 'Diesel', 'Gas oil']
  },
  yAxis: {
    title: {
      text: 'Million liters'
    }
  },
  tooltip: {
    valueSuffix: ' Inward Materials'
  },
  series: [{
    type: 'column',
   name: "Materials",
    data: [59, 83, 65, 228, 184]
  },{
    type: 'spline',
    name: 'Average',
    data: [59, 83, 65, 228, 184],
    marker: {
      lineWidth: 2,
      lineColor: Highcharts.getOptions().colors[1],
      fillColor: 'orange'
    }
  }, {
    type: 'pie',
    name: 'Total',
    data: [{
      name: "Materials",
      y: 619,
      
      dataLabels: {
        enabled: true,
        distance: -50,
        format: '{point.total} M',
        style: {
          fontSize: '15px'
        }
      }
    },],
    center: [75, 65],
    size: 100,
    innerSize: '75%',
    showInLegend: false,
    dataLabels: {
      enabled: false
    }
  }]
});