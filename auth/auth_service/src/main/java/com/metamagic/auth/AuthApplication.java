package com.metamagic.auth;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AuthApplication.class);
		Properties properties = new MongoConfig().getDBConfig();
        application.setDefaultProperties(properties);
		application.run(args);
	}

}
