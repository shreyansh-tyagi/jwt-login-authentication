package com.jwt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.controller.FileRead;
import com.jwt.model.JwtRequest;

@Service
public class LoginUserDetailService implements UserDetailsService {

	@Autowired
	private JwtRequest jwtRequest;

	@Value("${url}")
	private String URL;
	
	File input = new File(URL);
	FileReader fr = null;
	String str;
	String username, password;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		try {
			fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				if (str.contains(jwtRequest.getUsername()) && str.contains(jwtRequest.getPassword())) {
					username = jwtRequest.getUsername();
					password = jwtRequest.getPassword();
				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex);

		} catch (IOException ex1) {
			Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex1);

		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new User(username, password, new ArrayList<>());
	}
}
