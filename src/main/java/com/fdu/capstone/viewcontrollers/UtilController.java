package com.fdu.capstone.viewcontrollers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.User;

public class UtilController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping(value = {"/login"})
	public @ResponseBody User getCurrentLoginUser(@RequestBody String loginDetailString) {
		JSONObject object = new JSONObject(loginDetailString);
		String	userName = object.getString("userName"),
				password = object.getString("password");
		return UtilController.getCurrentLoginUser(jdbcTemplate, userName, password);
	}
	
	public static User getCurrentLoginUser(JdbcTemplate jdbcTemplate, String userName, String passWord) {
		String query = "SELECT * FROM users "
				+ "WHERE UName='" + userName
				+ "' AND Password='" + passWord + "'";
		List<User> result = jdbcTemplate.query(query, 
				BeanPropertyRowMapper.newInstance(User.class));
		return result.get(0);
	}
}
