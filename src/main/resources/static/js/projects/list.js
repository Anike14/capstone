/**
 * 
 */

function projectListInit() {
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getProjectsList",
      	timeout: 600000,
		success: function (projects) {
			let projectListBody = $("table#ProjectList > tbody:last-child")[0];
			projects.forEach(function (project) { 
				let newTr = document.createElement('tr')
				newTr.innerHTML = '<td>'
					+ project.name + '</a></td>  <td>' 
					+ project.startedDate + '</td> <td>' 
					+ project.expectedDueDate + '</td> <td>'
					+ getProjectDifficulty(project.difficult) + '</td> <td>'
					+ getProjectStatus(project.status) + '</td> </td>';
				projectListBody.append(newTr);
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

function getProjectDifficulty(difficulty) {
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

function getProjectStatus(status) {
	switch(status) {
		case 0: return 'New';
		case 1: return 'In Progress';
		case 2: return 'Finished';
		default: return '';
	}
}