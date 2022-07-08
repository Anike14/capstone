package com.fdu.capstone.viewcontrollers.tasks;

import java.sql.Date;
import java.sql.Types;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class CreationController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String insertSql =
			"INSERT INTO Tasks (" +
				" TOwner, " +
				" TName, " +
				" StartedDate, " +
				" ExpectedDueDate, " +
				" Difficulty) " +
				"VALUES (?, ?, ?, ?, ?)";
	
	@PostMapping(value = "/createProject", produces="application/json")
	public @ResponseBody void creation(@RequestBody String taskJSONString) {
		JSONObject taskJSONObject = new JSONObject(taskJSONString);
		// define query arguments
        Object[] params = new Object[] { 
    		taskJSONObject.getString("TaskOwner"),
    		taskJSONObject.getString("TaskName"),
    		Date.valueOf(taskJSONObject.getString("StartedDate")),
    		Date.valueOf(taskJSONObject.getString("ExpectedDueDate")),
    		taskJSONObject.getInt("Difficulty")
		};
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR, Types.CHAR, Types.DATE, Types.DATE, Types.INTEGER };
        jdbcTemplate.update(insertSql, params, types);
	}
}
