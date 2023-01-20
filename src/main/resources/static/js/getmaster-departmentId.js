//function for Show department Master Id Data in Table Format
     
function getDepartmentIdMaster(data){
	    var table_data=" ";
	             $.ajax({
						type:"get",
						url:"/masters/getDepartmentIds/"+data,
						success:function(data){                 
						var json = JSON.stringify(data);	    	
						var result = JSON.parse(json);
						
						for (var i = 0; i < result.length; i++) {
		    	    	  table_data += '<tr>' +	    	    		 
	    	    		  '<td>' +result[i].masterIdName + '</td>' +
	    	    		  '<td>' +result[i].deptName + '</td>' +
	    	    		  '<td>' +result[i].deptId + '</td>' +
	    	    		  '<td>' +result[i].deptIdDesc + '</td>' +	    	    		  
	    	    		  '</tr>';		    	    	 		    	    				    	    	  
		    	      }	    	                	
	    	    	  $('#result-table').html(table_data);
					 }
					 
				});
}

//function for display indivisual Department Id Master Dto data 

function updateDeptMasterId(deptMasterId){     
       $.ajax({
				type:"get",
				url:"/masters/getDeptMasterIdData/"+deptMasterId,
				success:function(data){                 
				var json = JSON.stringify(data);	    	
				var result = JSON.parse(json);
				
				$("#edit-dept-master-masterid").val(result.depMasterId);
				$("#edit-dept-master-idName").val(result.masterIdName);
				$("#edit-dept-master-department").val(result.deptName);
				$("#edit-dept-master-startNo").val(result.deptId);
				$("#edit-dept-master-desc").val(result.deptIdDesc);				
			  }
		});
}

