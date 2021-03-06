package com.fdu.capstone.viewcontrollers.projects;

import java.sql.Date;
import java.sql.Types;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.ObjectBase;
import com.fdu.capstone.models.User;
import com.fdu.capstone.viewcontrollers.UtilController;

@Controller("ProjectCreationController")
public class CreationController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String insertSql =
		"INSERT INTO Projects (" +
			" POwner, " +
			" PName, " +
			" StartedDate, " +
			" ExpectedDueDate, " +
			" Difficulty)" +
			" OUTPUT INSERTED.PID AS id " +
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
        Object[] params = new Object[] { 
    		currentLoggedIn.getUid(),
    		projectJSONObject.getString("ProjectName"),
    		Date.valueOf(projectJSONObject.getString("StartedDate")),
    		Date.valueOf(projectJSONObject.getString("ExpectedDueDate")),
    		projectJSONObject.getInt("Difficulty")
		};
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR, Types.CHAR, Types.DATE, Types.DATE, Types.INTEGER };
        List<ObjectBase> res = jdbcTemplate.query(insertSql, params, types,
				BeanPropertyRowMapper.newInstance(ObjectBase.class));
        return res.get(0).getId();
	}
}
