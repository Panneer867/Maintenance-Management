$(document).ready(function() {
// @ts-ignore
$('.MasterTable').DataTable({
		"responsive": true,
		"autoWidth": false,		
		 pageLength : 5,
		 "searching": false,
         "paging": true, 
         "info": true,         
         "lengthChange":false 
         //lengthMenu: [[5, 10, 15, -1], [5, 10, 15, 'All Data']]	
          
	});			 
});