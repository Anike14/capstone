/**
 * 
 */
var task;

function taskInit() {
	let me = this,
		prerequisitesSelect = $("select#Prerequisites")[0];
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
			
			let disabled = false;
			if (task.prerequisites.length === 0) {
				$("label#PrerequisitesLabel")[0].style.display="none";
				$("select#Prerequisites")[0].style.display="none";
			} else {
				task.prerequisites.forEach((prerequisite)=>{
					if (!prerequisite.achieved) {
						disabled = true;
					}
					newOption = document.createElement('option');
					newOption.value = prerequisite.taskId;
					newOption.innerHTML = prerequisite.taskName;
					prerequisitesSelect.append(newOption);
				});
			}
			$("#Progress")[0].value = task.progress;
			$("#Progress")[0].min = task.progress;
			$("#Progress")[0].disabled = disabled;
		},
        error: function (e) {
             
		}
	});
}

function onModifyClick() {
	let me = this,
		formData = $("form#task-detail").serializeArray(), data = {};
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