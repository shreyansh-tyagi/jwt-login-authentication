package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtTokenHelper;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.service.LoginUserDetailService;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginUserDetailService loginUserDetailService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception { // getting username
																									// and password
																									// after post api
																									// hit and setting
																									// in jwtrequest
																									// class
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate( // getting username and password from jwtrequest to authenticate
														// the user
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad credentials");
		} catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}

		// if try block execute successfully then it will generate the token
		UserDetails userDetails = this.loginUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		System.out.println("JWT Token: " + token);
		return ResponseEntity.ok(new JwtResponse(token)); // this is to change the format into JSON
	}

}
