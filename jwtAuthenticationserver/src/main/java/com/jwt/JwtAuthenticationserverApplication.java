package com.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@SpringBootApplication
@ComponentScan

public class JwtAuthenticationserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationserverApplication.class, args);
	}

}
