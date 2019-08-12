package com.metamagic.order;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(OrderApplication.class);
		Properties properties = new MongoConfig().getDBConfig();
        application.setDefaultProperties(properties);
		application.run(args);
	}

}
