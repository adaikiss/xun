/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author HuLingwei
 * 
 */
public class MongoIllustrationTest {
	private MongoClient mongoClient;
	private DB db;

	@Before
	public void setUp() throws Exception {
		Properties props = PropertiesLoaderUtils
				.loadAllProperties("application.test.properties");
		mongoClient = new MongoClient(props.getProperty("mongo.server.host"),
				Integer.parseInt(props.getProperty("mongo.server.port")));
		db = mongoClient.getDB("test");
	}

	@After
	public void tearDown() throws Exception {
		db.dropDatabase();
		mongoClient.close();
	}

	@Test
	public void test() throws Exception {
		DBCollection col = db.getCollection("heros");
		col.insert(new BasicDBObject("name", "Tiny"), new BasicDBObject("name",
				"Tink"));
		Assert.assertEquals(2, col.count());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Tiny");
		map.put("type", "Strength");
		map.put("level", 11);
		col.update(new BasicDBObject("name", "Tiny"),  new BasicDBObject(map)); 
		DBObject tiny = col.findOne(new BasicDBObject("name", "Tiny"));
		Assert.assertEquals(11, tiny.get("level"));
		
	}
}
