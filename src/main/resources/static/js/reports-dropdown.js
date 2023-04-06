$("#mis-category").on("change", function() {
  var $button = $(this).val();
 
  if ($button === "ASSETS") {
    var options = [
      { id: "op1", value: "ASSETS", text: "Assets Report" },
    ];
    
    options.forEach(function(option) {
      $('#' + option.id).remove();
      $('<option/>', { id: option.id, value: option.value, text: option.text }).appendTo('#mis-subcategory');
    });
  }
  
  
  if ($button === "STOCKS") {
    var options = [
      { id: "op1", value: "INWARDMATERIALS", text: "Inward Materials Report" },
      { id: "op2", value: "INWARDSPARES", text: "Inward Spares Report" },
      { id: "op3", value: "INWARDTOOLS", text: "Inward Tools Report" },
      { id: "op4", value: "OUTWARDSTOCKS", text: "Outward Stocks Report" },
      { id: "op5", value: "STOCKSRETURN", text: "Stocks Return Report" }
    ];
    
    options.forEach(function(option) {
      $('#' + option.id).remove();
      $('<option/>', { id: option.id, value: option.value, text: option.text }).appendTo('#mis-subcategory');
    });
  }
  
  
  if ($button === "WORKORDERS") {
    var options = [
      { id: "op1", value: "GENERATEWORKORDERS", text: "Generate Workorders Report" },
      { id: "op2", value: "APPROVEDWORKORDERS", text: "Approved Workorders Report" },
      { id: "op3", value: "HOLDWORKORDERS", text: "Hold Workorders Report" },
      { id: "op4", value: "CANCELWORKORDERS", text: "Cancel Workorders Report" }
    ];
    
    options.forEach(function(option) {
      $('#' + option.id).remove();
      $('<option/>', { id: option.id, value: option.value, text: option.text }).appendTo('#mis-subcategory');
    });
  }
  
 
});