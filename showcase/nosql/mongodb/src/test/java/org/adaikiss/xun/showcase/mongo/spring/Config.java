/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author HuLingwei
 *
 */
@Configuration
@Profile({"test"})
public class Config extends AbstractMongoConfiguration{
	@Override
	protected String getDatabaseName() {
		return "database";
	}

	@Override
	public Mongo mongo() throws Exception {
		Properties props = PropertiesLoaderUtils.loadAllProperties("application.test.properties");
		return new MongoClient(props.getProperty("mongo.server.host"), Integer.parseInt(props.getProperty("mongo.server.port")));
	}

}
