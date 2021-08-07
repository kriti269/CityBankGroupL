$(document).ready(function() {
    setTimeout(function(){
	    if ($('.error').length > 0) {
	    	$('.error').remove();
	    }
		if ($('.success').length > 0) {	
			$('.success').remove();
	    }
	    var search = window.location.search;
	    if(search) {
	        $('#search').val(search);
	    }
	}, 3000);
});