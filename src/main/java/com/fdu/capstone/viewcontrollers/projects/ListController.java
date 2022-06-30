package com.fdu.capstone.viewcontrollers.projects;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.Project;
import com.fdu.capstone.models.User;
import com.fdu.capstone.viewcontrollers.UtilController;

@Controller
public class ListController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String searchSql =
		"SELECT POwner AS owner, "
		+ "PName AS name, "
		+ "StartedDate AS startedDate, "
		+ "ExpectedDueDate AS expectedDueDate, "
		+ "Difficulty AS difficult "
		+ "FROM Projects WHERE POwner IN (?)";
	
	@GetMapping(value = "/projects/list")
	public String list() {
		return "/projects/list";
	}
	
	@PostMapping(value = "/projects/list", produces="application/json")
	public @ResponseBody List<Project> findAllProjects() {
		User currentLoggedIn = UtilController.getCurrentLoginUser(jdbcTemplate, "MASTER", "IAmGod");
		Object[] params = new Object[] { currentLoggedIn.getUid() };
        // define SQL types of the arguments
        int[] types = new int[] { Types.CHAR };
		return jdbcTemplate.query(searchSql, params, types,
				BeanPropertyRowMapper.newInstance(Project.class));
	}
}
