package com.metamagic.shoppingcart;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ShoppingcartApplication.class);
		Properties properties = new MongoConfig().getDBConfig();
        application.setDefaultProperties(properties);
		application.run(args);
	}

}
