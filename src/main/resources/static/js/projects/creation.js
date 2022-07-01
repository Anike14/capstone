function onCreateClick() {
	let formData = $("form").serializeArray(), data = {};
	formData.forEach(function(value){
	    data[value.name] = value.value;
	});
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/createProject",
      	timeout: 6000,
      	data: JSON.stringify(data),
		success: function (data) {
		},
        error: function (e) {
             
		}
	});
}