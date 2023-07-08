package org.erdem.InnovaCase.security.jwt;


import io.jsonwebtoken.*;
import org.erdem.InnovaCase.config.AppProperty;
import org.erdem.InnovaCase.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	private final AppProperty appProperty;
	
	@Autowired
	public JwtUtils(AppProperty appProperty) {
		this.appProperty = appProperty;
	}

	/**
	 * creates the Jwt Token
	 * @param authentication
	 * @return
	 */
	public String generateJwtToken(Authentication authentication) {
		
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipal.getEmail())) // userPrincipal.getEmail()
				.setId(userPrincipal.getId())
				//.claim("otherValue","other value" )
				.setIssuedAt(new Date())
				//.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))  //To set expiration
				.signWith(SignatureAlgorithm.HS512, appProperty.getJwtSecret())
				.compact();
	}

	/**
	 * Gets the user's email from token
	 * @param token
	 * @return
	 */
	public String getUserEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(appProperty.getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperty.getJwtSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}