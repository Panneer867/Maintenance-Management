
// myChart Total Assests
const data = {
    'React': 185134,
    'Vue': 195514,
    'Angular': 80460,
    'Svelte': 57022,
    'Ember.js': 22165,
    'Backbone.js': 27862
};

const ctx = document.getElementById('myChart').getContext('2d');

const myChart = new Chart(ctx, {
	type: 'bar',
	data: {
		labels: Object.keys(data),
		datasets: [
			{
				label: 'Total Assests',
				data: Object.values(data),
			},
		],
	},
});

// chart 2 Assets By Group/Department
const data2 = {
    'React': 185134,
    'Vue': 195514,
    'Angular': 80460,
    'Svelte': 57022,
    'Ember.js': 22165,
    'Backbone.js': 27862
};
const ctx1 = document.getElementById('myChart1').getContext('2d');

const myChart1 = new Chart(ctx1, {
	type: 'bar',
	data: {
		labels: Object.keys(data2),
		datasets: [
			{
				label: 'Assets By Group/Department',
				data: Object.values(data2),
			},
		],
	},
});



//*****************


