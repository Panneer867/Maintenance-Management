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
});