package com.Daniel.helpdesk.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Daniel.helpdesk.domain.dto.CredentialsDTO;
import com.Daniel.helpdesk.service.exception.DataIntegrityViolationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredentialsDTO cred = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					cred.getEmail(), cred.getSenha(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Email ou Senha inválidos");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.genarateToken(username);
		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		long date = new Date().getTime();
		return "{"
				+"\"timestamp\": " + date + ", "
				+"\"status\": 401, "
				+"\"error\": \"Não autorizado\", "
				+"\"message\": \"Email ou senha inválidos\", "
				+"\"path\": \"/login\"}";
				}
}
