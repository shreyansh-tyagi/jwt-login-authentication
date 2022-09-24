package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.helper.JwtTokenHelper;
import com.jwt.service.LoginUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private LoginUserDetailService loginUserDetailService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String requestTokenHeader = request.getHeader("Authorization"); // finding header from the token
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7); // from header finding the token
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(jwtToken); // from token finding the username

			} catch (Exception e) {
				e.printStackTrace();
			}

			UserDetails userDetails = this.loginUserDetailService.loadUserByUsername(username); // finding user details
																								// service from that
																								// username

			// security and validation
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// authenticating the token
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// setting the token in service context holder
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("token is not validated");
			}

		}

		else {
			System.out.println("unable to find the token header ");
		}

		filterChain.doFilter(request, response);

	}
}
