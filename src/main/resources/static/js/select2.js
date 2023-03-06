var typed = "";
$('#mySelect2').select2({
	language: {
		noResults: function(term) {
			typed = $('.ssssss').val();
			alert(typed)
		}
	}
});

var typed1 = "";
$('#mySelect3').select2({
	language: {
		noResults: function(term) {
			typed1 = $('.select2-search__field').val();
		}
	}
});