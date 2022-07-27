/**
 * 
 */
var taskslist;
 
function taskListInit() {
	this.loadTasks();
}
 
function loadTasks() {
	let me = this,
		taskListBodyObject = $("table#TaskList > tbody:last-child"),
		titleBar = $("table#TaskList > tbody:last-child > tr")[0],
		prerequisitesSelect = $("select#Prerequisites")[0],
		taskListBody = taskListBodyObject[0];
	taskListBodyObject.empty();
	taskListBody.append(titleBar);
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getTasksList?id=" + project.id,
      	timeout: 600000,
		success: function (tasks) {
			taskslist = tasks;
			tasks.forEach(function (task) { 
				let newTr = document.createElement('tr'),
					newOption = document.createElement('option');
				newTr.innerHTML = '<td>'
					+ task.name + '</a></td>  <td>' 
					+ task.startedDate + '</td> <td>' 
					+ task.expectedDueDate + '</td> <td>'
					+ getTaskDifficulty(task.difficult) + '</td> <td>'
					+ getTaskStatus(task.status) + '</td> </td>';
				newOption.value = task.id;
				newOption.innerHTML = task.name;
				taskListBody.append(newTr);
				prerequisitesSelect.append(newOption);
				newTr.classList.add("clickable");
				newTr.addEventListener('click', function () {
					location.href = '/tasks/detail?id=' + task.id;
				});
			});
			
		},
        error: function (e) {
             
		}
	});
}

function getTaskDifficulty(difficulty) {
	switch(difficulty) {
		case 0: return 'A Piece of Cake';
		case 1: return 'Easy';
		case 2: return 'Medium';
		case 3: return 'Need Some Research';
		case 4: return 'Difficult';
		case 5: return 'Need Research Before Evaluate';
		default: return '';
	}
}

function getTaskStatus(status) {
	switch(status) {
		case 0: return 'New';
		case 1: return 'In Progress';
		case 2: return 'Finished';
		default: return '';
	}
}

function onCreateTaskClick() {
	let taskModal = $("#taskCreation")[0];
	taskModal.style.display = "block";
}

function onCreateTaskModalClosed() {
	let taskModal = $("#taskCreation")[0];
	taskModal.style.display = "none";
}

function onCreateClick() {
	let me = this,
		taskModal = $("#taskCreation")[0];
		formData = $("form").serializeArray(), data = { "Prerequisites": [] };
	formData.forEach(function(value){
		if (value.name === "Prerequisites") {
			data["Prerequisites"][data["Prerequisites"].length] = value.value;
		} else data[value.name] = value.value;
	});
    data['TaskOwner'] = project.id;
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/createTask",
      	timeout: 6000,
      	data: JSON.stringify(data),
      	dataType: 'text',
		success: function () {
			me.loadTasks();
			taskModal.style.display = "none";
		},
        error: function (e) {
             
		}
	});
}