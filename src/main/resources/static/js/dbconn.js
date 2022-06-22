/**
 * 
 */
function onClick() {
	let data = {
			query: 'SELECT db_name() AS test'
		};
	$.ajax({
		type: "POST",
      	contentType: "application/json",             
      	url: "/dbconn",
      	timeout: 600000,
      	data: JSON.stringify(data),
		success: function (data) {
			alert(data.test);
		},
        error: function (e) {
             
		}
	});
}