package com.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.JwtRequest;

@Service
public class LoginUserDetailService implements UserDetailsService {
	
	@Autowired
	private JwtRequest jwtRequest;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
			return new User(jwtRequest.getUsername(), jwtRequest.getPassword(), new ArrayList<>());
	}
}
