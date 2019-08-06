package com.metamagic.auth;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

import ch.qos.logback.classic.Logger;

@Configuration
@EnableMongoRepositories(basePackages = "com.metamagic.auth.repo")
public class MongoConfig extends AbstractMongoConfiguration {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MongoConfig.class);

	@Autowired
	private Environment env;

	@Override
	public MongoClient mongoClient() {
		String host = env.getProperty("mongo_host");
		String port = env.getProperty("mongo_port");
		LOGGER.info("Mongo db config [ host = " + host + " port = " + port + "]");
		return new MongoClient(host, Integer.parseInt(port));
	}

	@Override
	protected String getDatabaseName() {
		String dbname = env.getProperty("mongo_auth_db");
		LOGGER.info("Mongo db config [ dbname = " + dbname + "]");
		return dbname;
	}

}
