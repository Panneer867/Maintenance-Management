  
   function sum() {
	  
      var sl = document.getElementById('sl').value;
      var cl = document.getElementById('cl').value;
      var lwp = document.getElementById('lwp').value;
      var result = parseInt(sl) + parseInt(cl)+ parseInt(lwp);
      if (!isNaN(result)) {
         document.getElementById('totalLeave').value = result;
      }
}



 function sal() {
   
      var salary = document.getElementById('salary').value;
      var result =(salary * 0.3) ;
      if (!isNaN(result)) {
         document.getElementById('basicSalary').value = result;
      }
}


 