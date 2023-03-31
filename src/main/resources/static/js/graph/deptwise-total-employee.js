$.getJSON('/employee/dash/empCount', function(data) {
    if (Array.isArray(data)) { // Add a check for array
        const department = [];
        const empCount = [];

        data.forEach((emp) => {
            department.push(emp.department);
            empCount.push(emp.empCount);
        });

        // Render the chart
        const chart = Highcharts.chart('monthwise-total-emp', {
            title: {
                text: 'Department Wise Employee',
                align: 'center'
            },
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            series: [{
                type: 'pie',
                name: 'Total Employee',
                colorByPoint: true,
                data: data.map(emp => ({
                    name: emp.department,
                    y: emp.empCount
                })),
                showInLegend: true // set to true to show the legend
            }]
        });
    }
});
