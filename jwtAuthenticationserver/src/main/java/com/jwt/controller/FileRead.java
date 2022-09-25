package com.jwt.controller;

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

import com.jwt.model.JwtRequest;
import com.jwt.service.LoginUserDetailService;

public class FileRead {
	
	@Autowired
	private JwtRequest jwtRequest;
	
	@Value("${url}")
	private String URL;
	
	public void fileReader() throws IOException {
	
	File input=new File(URL);
	FileReader fr=null;
	String str;
	try {
		fr=new FileReader(input);
		BufferedReader br =new BufferedReader(fr);
		while((str=br.readLine())!=null) {
			if(str.contains(jwtRequest.getUsername()) && str.contains(jwtRequest.getPassword())) {
				UsernamePassword(jwtRequest.getUsername(),jwtRequest.getPassword());
			}
		}
		
		
	} catch(FileNotFoundException ex) {
		Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE,null,ex);
		
	}
	catch(IOException ex1) {
		Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE,null,ex1);
		
	}
	finally {
		fr.close();
	}

}
	public void UsernamePassword(String user, String pass) {
		
	}
	
}
