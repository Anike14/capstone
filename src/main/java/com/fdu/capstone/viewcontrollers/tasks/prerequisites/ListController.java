package com.fdu.capstone.viewcontrollers.tasks.prerequisites;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.fdu.capstone.models.Task;
import com.fdu.capstone.models.TaskPrerequisite;

@Controller("TaskPrerequisitesListController")
public class ListController {
	
	private static final String searchSql = 
			"SELECT TPOWNER AS owner, TID AS taskId, TName AS taskName, "
			+ "CASE STATUS WHEN 2 THEN 'TRUE' ELSE 'FALSE' END AS achieved "
			+ "FROM Tasks AS A INNER JOIN TaskPrerequisites AS B "
			+ "ON A.TID = B.TaskID "
			+ "AND B.TPOWNER = ?";
	
	public static List<TaskPrerequisite> getTaskPrerequisites(JdbcTemplate jdbcTemplate, String taskOwnerId) {
		// define query arguments
		Object[] params = new Object[] { taskOwnerId };
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR };
        // execute insert query to insert the data
        return jdbcTemplate.query(searchSql, params, types,
        		BeanPropertyRowMapper.newInstance(TaskPrerequisite.class));
	}
}
