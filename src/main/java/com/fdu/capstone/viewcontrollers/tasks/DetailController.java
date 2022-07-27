package com.fdu.capstone.viewcontrollers.tasks;


import java.sql.Date;
import java.sql.Types;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.Project;
import com.fdu.capstone.models.Task;

@Controller("TaskDetailController")
public class DetailController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String searchSql =
		"SELECT TID AS id, TOwner AS owner, "
		+ "STATUS AS status, "
		+ "TName AS name, "
		+ "StartedDate AS startedDate, "
		+ "ExpectedDueDate AS expectedDueDate, "
		+ "Difficulty AS difficult, "
		+ "Progress AS progress "
		+ "FROM Tasks WHERE TID = ?";

	private static final String updateSql =
		"UPDATE Task SET TName = ?, StartedDate = ?, ExpectedDueDate = ?, Difficulty = ?, Progress = ?, Status = ?, WHERE TID = ?";
	
	@GetMapping(value = "/tasks/detail")
	public static String detail() {
		return "tasks/detail";
	}

	@GetMapping(value = "/getTaskDetail")
	public @ResponseBody Task getTask(@RequestParam(value="id", required=true) String taskId) {
		System.out.println(taskId);
		Task task = jdbcTemplate.queryForObject(searchSql, BeanPropertyRowMapper.newInstance(Task.class),
			new Object[]{taskId});
		task.setPrerequisites(com.fdu.capstone.viewcontrollers.
				tasks.prerequisites.ListController.getTaskPrerequisites(jdbcTemplate, taskId));
		return task;
	}
	
	@PostMapping(value = "/updateTask", produces="application/json")
	public @ResponseBody void update(@RequestBody String taskJsonString) {
		JSONObject taskJSONObject = new JSONObject(taskJsonString);
        // define query arguments
        Object[] params = new Object[] { 
    		taskJSONObject.getString("TaskName"), 
    		Date.valueOf(taskJSONObject.getString("StartedDate")), 
    		Date.valueOf(taskJSONObject.getString("ExpectedDueDate")),
    		taskJSONObject.getInt("Difficulty"),
    		taskJSONObject.getInt("Progress"),
    		taskJSONObject.getInt("Progress") != 0 ? 1 : 0,
    		taskJSONObject.getString("id"),
    	};
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR, Types.DATE, Types.DATE, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.CHAR };
        // execute insert query to insert the data
        jdbcTemplate.update(updateSql, params, types);
	}
}
