package com.fdu.capstone.viewcontrollers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdu.capstone.models.testConnObj;

@Controller
public class DbconnController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping(value = "/dbconn")
	public String dbconn() {
		return "dbconn";
	}
	
	@PostMapping(value = "/dbconn", produces="application/json")
	public @ResponseBody testConnObj onClick(@RequestBody String jsonString) {
		JSONObject object = new JSONObject(jsonString);
		String query = object.getString("query");
		System.out.println("------" + object + "-------");
		List<testConnObj> result = jdbcTemplate.query(query, 
				BeanPropertyRowMapper.newInstance(testConnObj.class));
		return result.get(0);
	}
}
