package com.fdu.capstone.viewcontrollers.tasks;

import java.sql.Date;
import java.sql.Types;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.ObjectBase;

@Controller("TaskCreationController")
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
				" OUTPUT INSERTED.TID AS id " +
				"VALUES (?, ?, ?, ?, ?)";
	
	@PostMapping(value = "/createTask", produces="application/json")
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
        List<ObjectBase> res = jdbcTemplate.query(insertSql, params, types, 
        		BeanPropertyRowMapper.newInstance(ObjectBase.class));
        
        com.fdu.capstone.viewcontrollers.tasks.
        	prerequisites.CreationController.creation(jdbcTemplate, res.get(0).getId(), (JSONArray)taskJSONObject.get("Prerequisites"));
	}
}
