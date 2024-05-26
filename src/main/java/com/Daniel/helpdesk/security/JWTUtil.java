package com.Daniel.helpdesk.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String genarateToken(String email) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			return JWT.create().withIssuer("auth-api").withSubject(email).withExpiresAt(gerarDataExpiracao())
					.sign(algorithm);
		} catch (JWTCreationException ex) {
			throw new RuntimeException("Erro ao gerar o Token" + ex.getMessage());
		}
	}

	private Instant gerarDataExpiracao() {
		return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-04:00"));
	}

	public String validaTokenJWT(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			return JWT.require(algorithm)
			.withIssuer("auth-api")
			.build()
			.verify(token)
			.getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}
}
