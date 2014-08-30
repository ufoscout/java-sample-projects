$(document).ready(function(){
	$('#result').hide();
	
	$('#clickButton').click(function() {
	  var result = fullname( $('#firstname').val(), $('#lastname').val() );
	  $('#spanname').text(result);
	  $('#result').show();
	});
});



function fullname(firstname, lastname) {
	var fullname = firstname + " " + lastname;
	return fullname;  
}