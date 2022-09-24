package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginHome {

	@RequestMapping("/welcome")
	public String welcome() {
		String text = "this is the welcome page";
		text += " unauthorized user not allowed";
		return text;
	}

}
