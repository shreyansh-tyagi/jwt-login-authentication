package com.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.JwtRequest;

@Service
public class LoginUserDetailService implements UserDetailsService {

	//JwtRequest jwtRequest = new JwtRequest();
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 	
		//	return new User(jwtRequest.getUsername(), jwtRequest.getPassword(), new ArrayList<>());
		if (userName.equals("user1")|| userName.equals("user2")|| userName.equals("user3")|| userName.equals("user4")) {
			return new User("user1", "pass1", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("user not found!");
		}
	}
}
