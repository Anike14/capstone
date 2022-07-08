package com.fdu.capstone.viewcontrollers.projects;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.Project;
import com.fdu.capstone.models.User;
import com.fdu.capstone.viewcontrollers.UtilController;

@Controller("ProjectListController")
public class ListController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String searchSql =
		"SELECT PID AS id, POwner AS owner, "
		+ "PName AS name, "
		+ "StartedDate AS startedDate, "
		+ "ExpectedDueDate AS expectedDueDate, "
		+ "Difficulty AS difficult "
		+ "FROM Projects WHERE POwner IN (?)";
	
	@GetMapping(value = "/projects")
	public String list() {
		return "/projects/list";
	}
	
	@GetMapping(value = "/getProjectsList", produces="application/json")
	public @ResponseBody List<Project> findAllProjects() {
		User currentLoggedIn = UtilController.getCurrentLoginUser(jdbcTemplate, "MASTER", "IAmGod");
		Object[] params = new Object[] { currentLoggedIn.getUid() };
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR };
        List<Project> res = jdbcTemplate.query(searchSql, params, types,
				BeanPropertyRowMapper.newInstance(Project.class));
        return res;
	}
}
