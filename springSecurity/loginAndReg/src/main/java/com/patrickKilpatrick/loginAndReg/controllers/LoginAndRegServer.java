package com.patrickKilpatrick.loginAndReg.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com")
@EntityScan("com")
public class LoginAndRegServer {

	public static void main(String[] args) {
		
		SpringApplication.run(LoginAndRegServer.class, args);

	}

}
