
// Message while Deleting Employee 

function deleteEmpMaster(cid){
	swal({		 
		  text: "Are you sure ? You Want To Delete This !!",		 
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {		   
		    window.location ="/employee/delete/"+cid;
		  } else {		   
		    swal("Data is safe !!");
		  }
		});	
}