/**
 * 
 */

function init() {
	$.ajax({
		type: "GET",
      	contentType: "application/json",             
      	url: "/getProjectsList",
      	timeout: 600000,
      	// data: JSON.stringify(data),
		success: function (projects) {
			let projectListBody = $("table#ProjectList > tbody:last-child")[0];
			projects.forEach(function (project) { 
				let newTr = document.createElement('tr')
				newTr.innerHTML = '<td>'
					+ project.name + '</a></td>  <td>' 
					+ project.startedDate + '</td> <td>' 
					+ project.expectedDueDate + '</td> <td>'
					+ project.difficult + '</td> <td>';
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