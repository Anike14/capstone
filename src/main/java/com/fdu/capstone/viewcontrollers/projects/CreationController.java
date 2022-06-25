package com.fdu.capstone.viewcontrollers.projects;

import java.sql.Date;
import java.sql.Types;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.Project;
import com.fdu.capstone.models.User;
import com.fdu.capstone.viewcontrollers.UtilController;

@Controller
public class CreationController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String insertSql =
		"INSERT INTO Projects (" +
			" POwner, " +
			" PName, " +
			" StartedDate, " +
			" ExpectedDueDate, " +
			" Difficulty) " +
			"VALUES (?, ?, ?, ?, ?)";
	
	@GetMapping(value = "/projects/creation")
	public String creation() {
		return "/projects/creation";
	}
	
	@PostMapping(value = "/projects/creation", produces="application/json")
	public @ResponseBody void creation(@RequestBody String projectJsonString) {
		System.out.println(projectJsonString);
		JSONObject projectJSONObject = new JSONObject(projectJsonString);
		System.out.println(projectJSONObject);
		User currentLoggedIn = UtilController.getCurrentLoginUser(jdbcTemplate, "MASTER", "IAmGod");
		// define query arguments
        Object[] params = new Object[] { 
        		currentLoggedIn.getUid(),
        		projectJSONObject.getString("ProjectName"), 
        		Date.valueOf(projectJSONObject.getString("StartedDate")), 
        		Date.valueOf(projectJSONObject.getString("ExpectedDueDate")),
        		projectJSONObject.getInt("Difficulty")
    	};
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR, Types.VARCHAR, Types.DATE, Types.DATE, Types.INTEGER };
        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        int row = jdbcTemplate.update(insertSql, params, types);
        
        System.out.println(row + " row inserted.");
	}
}
