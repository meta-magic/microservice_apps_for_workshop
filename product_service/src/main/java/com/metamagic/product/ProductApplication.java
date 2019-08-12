package com.metamagic.product;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ProductApplication.class);
		Properties properties = new MongoConfig().getDBConfig();
        application.setDefaultProperties(properties);
		application.run(args);
	}

}
