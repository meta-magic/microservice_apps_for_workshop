package com.metamagic.shoppingcart;

import java.util.Properties;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Properties properties = new MongoConfig().getDBConfig();
		application.application().setDefaultProperties(properties);
		return application.sources(ShoppingcartApplication.class);
	}

}
