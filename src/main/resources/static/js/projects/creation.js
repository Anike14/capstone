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
      	dataType: 'text',
		success: function (projectId) {
			location.href = '/projects/detail?id=' + projectId;
		},
        error: function (e) {
             
		}
	});
}