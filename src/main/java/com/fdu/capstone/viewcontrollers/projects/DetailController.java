package com.fdu.capstone.viewcontrollers.projects;


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

@Controller
public class DetailController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String searchSql =
		"SELECT PID AS id, POwner AS owner, "
		+ "PName AS name, "
		+ "StartedDate AS startedDate, "
		+ "ExpectedDueDate AS expectedDueDate, "
		+ "Difficulty AS difficult "
		+ "FROM Projects WHERE PID = ?";

	private static final String updateSql =
		"UPDATE Projects SET PName = ?, StartedDate = ?, ExpectedDueDate = ?, Difficulty = ? WHERE PID = ?";
	
	@GetMapping(value = "/projects/detail")
	public static String detail() {
		return "projects/detail";
	}

	@GetMapping(value = "/getProjectDetail")
	public @ResponseBody Project getProject(@RequestParam(value="id", required=true) String projectId) {
		System.out.println(projectId);
		Project project = jdbcTemplate.queryForObject(searchSql, BeanPropertyRowMapper.newInstance(Project.class),
				new Object[]{projectId});
		project.setTasks(
				com.fdu.capstone.viewcontrollers.tasks.ListController.getTasksByParentID(jdbcTemplate, project.getId()));
		return project;
	}
	
	@PostMapping(value = "/updateProject", produces="application/json")
	public @ResponseBody void update(@RequestBody String projectJsonString) {
		JSONObject projectJSONObject = new JSONObject(projectJsonString);
        // define query arguments
        Object[] params = new Object[] { 
        		projectJSONObject.getString("ProjectName"), 
        		Date.valueOf(projectJSONObject.getString("StartedDate")), 
        		Date.valueOf(projectJSONObject.getString("ExpectedDueDate")),
        		projectJSONObject.getInt("Difficulty"),
        		projectJSONObject.getString("id"),
    	};
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR, Types.DATE, Types.DATE, Types.INTEGER, Types.CHAR };
        
        // execute insert query to insert the data
        jdbcTemplate.update(updateSql, params, types);
	}
}
