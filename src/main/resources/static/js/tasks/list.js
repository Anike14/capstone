/**
 * 
 */
 
function taskListInit() {
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getTasksList",
      	timeout: 600000,
      	// data: JSON.stringify(data),
		success: function (tasks) {
			let taskListBody = $("table#TaskList > tbody:last-child")[0];
			tasks.forEach(function (task) { 
				let newTr = document.createElement('tr')
				newTr.innerHTML = '<td>'
					+ task.name + '</a></td>  <td>' 
					+ task.startedDate + '</td> <td>' 
					+ task.expectedDueDate + '</td> <td>'
					+ task.difficult + '</td> <td>'
					+ <button>createSubTasks</button> + '</td> <td>';
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
	let me = this,
		taskListBody = $("table#ProjectList > tbody:last-child")[0];
}