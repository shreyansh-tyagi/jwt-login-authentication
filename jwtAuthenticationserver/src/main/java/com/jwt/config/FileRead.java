package com.jwt.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jwt.helper.JwtTokenHelper;
import com.jwt.model.JwtRequest;
import com.jwt.service.LoginUserDetailService;

public class FileRead {

	@Autowired
	private JwtRequest jwtRequest;
	
	@Autowired 
	private LoginUserDetailService loginUserDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Value("${url}")
	private String URL;

	File input = new File(URL);
	FileReader fr = null;
	String str, token=null;
	String username, password;

	public String fileReader() throws IOException {

		try {
			fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				if (str.contains(jwtRequest.getUsername()) && str.contains(jwtRequest.getPassword())) {
					jwtRequest.setUsername(jwtRequest.getUsername());
					jwtRequest.setPassword(jwtRequest.getPassword());
					UserDetails userDetails = this.loginUserDetailService.loadUserByUsername(jwtRequest.getUsername());
				    token = this.jwtTokenHelper.generateToken(userDetails);
				}
				else {
					throw new UsernameNotFoundException("user not found!");
				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LoginUserDetailService.class.getName()).log(Level.SEVERE, null, ex);

		} catch (IOException ex1) {
			Logger.getLogger(LoginUserDetailService.class.getName()).log(Level.SEVERE, null, ex1);

		} finally {
			fr.close();
		}
		return token;

	}
}
