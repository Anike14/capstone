/**
 * 
 */
var task;

function taskInit() {
	let me = this;
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getTaskDetail" + location.search,
      	timeout: 600000,
		success: function (task) {
			me.task = task;
			$("#BackToProject")[0].href = "/projects/detail?id=" + task.owner;
			$("#Status")[0].value = getTaskStatus(task.status);
			$("#TaskName")[0].value = task.name;
			$("#StartedDate")[0].value = task.startedDate;
			$("#ExpectedDueDate")[0].value = task.expectedDueDate;
			$("#Difficulty")[0].value = task.difficult;
			$("#Progress")[0].value = task.progress;
			$("#Progress")[0].min = task.progress;
		},
        error: function (e) {
             
		}
	});
}

function onModifyClick() {
	let me = this,
		formData = $("form").serializeArray(), data = {};
	data['id'] = me.task.id;
	formData.forEach(function(value){
	    data[value.name] = value.value;
	});
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/updateTask",
      	timeout: 6000,
      	data: JSON.stringify(data),
		success: function (data) {
		},
        error: function (e) {
             
		}
	});
}

function getTaskStatus(status) {
	switch(status) {
		case 0: return 'New';
		case 1: return 'In Progress';
		case 2: return 'Finished';
		default: return '';
	}
}