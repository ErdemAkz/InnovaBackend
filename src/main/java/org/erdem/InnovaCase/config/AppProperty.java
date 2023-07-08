package org.erdem.InnovaCase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


@ConfigurationProperties("innovacaseapplication")
public class AppProperty {

	private String jwtSecret;
	public String getJwtSecret() {
		return jwtSecret;
	}
	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	
}
