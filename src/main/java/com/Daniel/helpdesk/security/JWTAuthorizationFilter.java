package com.Daniel.helpdesk.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService detailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService detailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
	    this.detailsService = detailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = extraiTokenHeader(request);
			
		if(token != null) {
			UsernamePasswordAuthenticationToken authToken = getAuthentication(token);
			if(authToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(request, response);
	}
	
	
	@SuppressWarnings("null")
	public String extraiTokenHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header == null) {
		 return null;
		}
		
		if(!header.split(" ")[0].equals("Bearer")) {
			return null;
		}
		
		return header.split(" ")[1];
		}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
	 if(jwtUtil.validaTokenJWT(token) != null) {
		 String username = jwtUtil.validaTokenJWT(token);
		 UserDetails details = detailsService.loadUserByUsername(username);
		 return new UsernamePasswordAuthenticationToken(details.getUsername(),null,details.getAuthorities());
	 }
	 return null;
	}
}
