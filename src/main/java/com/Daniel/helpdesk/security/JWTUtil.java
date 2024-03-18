package com.Daniel.helpdesk.security;

import java.security.Signature;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String genarateToken(String email) {
		return Jwts.builder()
			   .setSubject(email)
			   .setExpiration(new Date(System.currentTimeMillis()+ expiration))
			   .signWith(secret,SignatureAlgorithm.HS512)
			   .compact();
	}

}
