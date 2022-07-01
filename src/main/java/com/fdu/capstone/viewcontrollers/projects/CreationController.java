package com.fdu.capstone.viewcontrollers.projects;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping(value = "/createProject", produces="application/json")
	public @ResponseBody String creation(@RequestBody String projectJsonString) {
		JSONObject projectJSONObject = new JSONObject(projectJsonString);
		User currentLoggedIn = UtilController.getCurrentLoginUser(jdbcTemplate, "MASTER", "IAmGod");
		// define query arguments
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, new String[]{"PID"});
            preparedStatement.setString(1, currentLoggedIn.getUid());
            preparedStatement.setString(2, projectJSONObject.getString("ProjectName"));
            preparedStatement.setDate(3, Date.valueOf(projectJSONObject.getString("StartedDate")));
            preparedStatement.setDate(4, Date.valueOf(projectJSONObject.getString("ExpectedDueDate")));
            preparedStatement.setInt(5, projectJSONObject.getInt("Difficulty"));
            return preparedStatement;
        }, keyHolder);
        for (Map<String, Object> map : keyHolder.getKeyList()) {
        	for (Entry<String, Object> e : map.entrySet()) {
                System.out.println("key: " + e.getKey());
                System.out.println("value: " + e.getValue());
        	}
        }
        String projectId = (String)keyHolder.getKeys().get("PID");
        System.out.println("projectId: " + projectId);
        return projectId;
	}
}
