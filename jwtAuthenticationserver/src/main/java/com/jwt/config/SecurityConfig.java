package com.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.model.JwtRequest;
import com.jwt.service.LoginUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginUserDetailService loginUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf() // Cross-Site Request Forgery. It is an attack which is disabled.
				.disable().cors() // here co-request will come
				.disable().authorizeRequests() // here request will be authorizied
				.antMatchers("/token") // allow URL
				.permitAll().anyRequest() // all other request are permitted but if
											// request token is hit then for that permit is there
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class); 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserDetailService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // as password is in plain text
													// therefore using this kind of encryption
	}

	// creating a bean of authentication manager
	@Bean
	public AuthenticationManager autheticationManagerBean() throws Exception { // it will provide the object of
																				// authenticationManager to spring
																				// container and then it will get
																				// injected into our code
		return super.authenticationManagerBean();
	}
	
}
