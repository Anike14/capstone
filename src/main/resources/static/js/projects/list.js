/**
 * 
 */

function init() {
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/projects/list",
      	timeout: 600000,
      	// data: JSON.stringify(data),
		success: function (projects) {
			let projectListBody = $("table#ProjectList > tbody:last-child")[0];
			projects.forEach(function (project) { 
				let newTr = document.createElement('tr')
				newTr.innerHTML = '<td>' 
					+ project.name + '</td>  <td>' 
					+ project.startedDate + '</td> <td>' 
					+ project.expectedDueDate + '</td> <td>'
					+ project.difficult + '</td> <td>';
				projectListBody.append(newTr);
			});
			
		},
        error: function (e) {
             
		}
	});
}