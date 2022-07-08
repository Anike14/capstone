package com.fdu.capstone.viewcontrollers.tasks;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.Task;

@Controller("TaskListController")
public class ListController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String searchSql =
			"SELECT TID AS id, TOwner AS owner, "
			+ "TName AS name, "
			+ "StartedDate AS startedDate, "
			+ "ExpectedDueDate AS expectedDueDate, "
			+ "Difficulty AS difficult "
			+ "FROM Tasks WHERE TOwner IN (?)";
	
	@GetMapping(value = "/tasks")
	public String list() {
		return "/tasks/list";
	}
	
	@GetMapping(value = "/getTasksList", produces="application/json")
	public @ResponseBody List<Task> findAllTasks(@RequestBody String parentId) {
		return getTasksByParentID(jdbcTemplate, parentId);
	}
	
	public static List<Task> getTasksByParentID(JdbcTemplate jdbcTemplate, String id) {
		Object[] params = new Object[] { id };
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR };
        List<Task> res = jdbcTemplate.query(searchSql, params, types,
				BeanPropertyRowMapper.newInstance(Task.class));
        for (Task t : res) t.setSubTasks(getTasksByParentID(jdbcTemplate, t.getId()));
        return res;
	}
}
