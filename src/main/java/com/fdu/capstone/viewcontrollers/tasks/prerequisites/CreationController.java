package com.fdu.capstone.viewcontrollers.tasks.prerequisites;

import java.sql.Types;

import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Controller("TaskPrerequisiteCreationController")
public class CreationController {
	
	private static final String insertSql =
			"INSERT INTO TaskPrerequisites "
				+ "(TPOWNER, TaskID) "
				+ "VALUES (?, ?)";
	
	public static void creation(JdbcTemplate jdbcTemplate, String taskOwnerId, JSONArray taskIds) {
		// define query arguments
		taskIds.forEach(taskId -> {
			Object[] params = new Object[] { 
				taskOwnerId,
				taskId
	    	};
	        // define SQL types of the arguments
	        int[] types = new int[] { Types.CHAR, Types.CHAR };
	        
	        // execute insert query to insert the data
	        jdbcTemplate.update(insertSql, params, types);
		});
	}
}
