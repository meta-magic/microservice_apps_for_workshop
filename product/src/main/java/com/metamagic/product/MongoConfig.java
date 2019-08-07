package com.metamagic.product;

import java.util.Properties;

public class MongoConfig {

	public Properties getDBConfig() {
		
		String dbhost = System.getenv("mongo_host");
		String port = System.getenv("mongo_port");

		Properties properties = new Properties();
		properties.put("spring.data.mongodb.database", "productdb");
		properties.put("spring.data.mongodb.host", dbhost);
		properties.put("spring.data.mongodb.port", port);
		return properties;
	}

}
