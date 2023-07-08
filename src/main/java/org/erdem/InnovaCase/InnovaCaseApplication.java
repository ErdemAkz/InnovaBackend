package org.erdem.InnovaCase;

import org.erdem.InnovaCase.config.AppProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(AppProperty.class)
@EnableScheduling
public class InnovaCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovaCaseApplication.class, args);
	}

}
