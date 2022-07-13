/**
 * 
 */
 
function taskListInit() {
	this.loadTasks();
}
 
function loadTasks() {
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getTasksList?id=" + project.id,
      	timeout: 600000,
		success: function (tasks) {
			let taskListBody = $("table#TaskList > tbody:last-child")[0];
			tasks.forEach(function (task) { 
				let newTr = document.createElement('tr')
				newTr.innerHTML = '<td>'
					+ task.name + '</a></td>  <td>' 
					+ task.startedDate + '</td> <td>' 
					+ task.expectedDueDate + '</td> <td>'
					+ task.difficult + '</td> <td>'
					+ task.status + '</td> </td>';
				taskListBody.append(newTr);
				newTr.classList.add("clickable");
				newTr.addEventListener('click', function () {
					location.href = '/projects/detail?id=' + project.id;
				});
			});
			
		},
        error: function (e) {
             
		}
	});
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
		formData = $("form").serializeArray(), data = {};
	formData.forEach(function(value){
	    data[value.name] = value.value;
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
		},
        error: function (e) {
             
		}
	});
}