 $.getJSON('/employee/dash/empLeave', function(data) {
    if (Array.isArray(data)) { // Add a check for array
        const department = [];
        const empCount = [];

        data.forEach((emp) => {
            department.push(emp.department);
            empCount.push(emp.empCount);
        });

        // Render the chart
        const chart = Highcharts.chart('monthwise-total-empLeave', {
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: 'Department Wise Emp Leave',
                align: 'center'
            },
            series: [{
                type: 'pie',
                name: 'Total Employee',
                data: data.map(emp => ({
                    name: emp.department,
                    y: emp.empCount
                })),
                innerSize: '50%', // Add innerSize property to make it a donut chart
                depth: 45, // Set the depth of the chart
                showInLegend: false
            }]
        });
    }
});
