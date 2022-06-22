package com.fdu.capstone.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
	
	@GetMapping(value = "/greeting")
	public String greeting() {
		return "greeting";
	}
}
