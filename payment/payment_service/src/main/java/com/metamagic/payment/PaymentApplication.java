package com.metamagic.payment;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PaymentApplication.class);
		Properties properties = new MongoConfig().getDBConfig();
        application.setDefaultProperties(properties);
		application.run(args);
	}

}
