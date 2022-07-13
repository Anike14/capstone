/**
 * 
 */
var project;

function projectInit(callback) {
	let me = this;
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getProjectDetail" + location.search,
      	timeout: 600000,
		success: function (project) {
			me.project = project;
			$("#ProjectName")[0].value = project.name;
			$("#StartedDate")[0].value = project.startedDate;
			$("#ExpectedDueDate")[0].value = project.expectedDueDate;
			$("#Difficulty")[0].value = project.difficult;
			callback();
		},
        error: function (e) {
             
		}
	});
}

function onModifyClick() {
	let me = this,
		formData = $("form").serializeArray(), data = {};
	data['id'] = me.project.id;
	formData.forEach(function(value){
	    data[value.name] = value.value;
	});
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/updateProject",
      	timeout: 6000,
      	data: JSON.stringify(data),
		success: function (data) {
		},
        error: function (e) {
             
		}
	});
}

function onModifyClick() {
	let me = this,
		formData = $("form").serializeArray(), data = {};
	data['id'] = me.project.id;
	formData.forEach(function(value){
	    data[value.name] = value.value;
	});
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/updateProject",
      	timeout: 6000,
      	data: JSON.stringify(data),
		success: function (data) {
		},
        error: function (e) {
             
		}
	});
}