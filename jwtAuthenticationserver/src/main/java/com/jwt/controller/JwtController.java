package com.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
	
	@RequestMapping("\token")
	public ResponseEntity<?> generateToken(){
		
		
	}

}
