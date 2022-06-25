$(document).ready(function() {
    console.log( "ready!" );
});

function onClick() {
	let formData = $("form").serializeArray(), data = {};
	formData.forEach(function(value){
	    data[value.name] = value.value;
	});
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/projects/creation",
      	timeout: 6000,
      	data: JSON.stringify(data),
		success: function () {
		},
        error: function (e) {
             
		}
	});
}