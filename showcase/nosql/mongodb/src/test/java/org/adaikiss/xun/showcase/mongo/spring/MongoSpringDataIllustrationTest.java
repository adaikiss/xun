/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.text.SimpleDateFormat;
import java.util.List;

import org.adaikiss.xun.showcase.mongo.spring.entity.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author HuLingwei
 * 
 */
@ActiveProfiles({ "test" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MongoSpringDataIllustrationTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	MongoOperations mongoOps;

	@After
	public void tearDown() {
		mongoOps.dropCollection(Person.class);
	}

	@Test
	public void test() throws Exception {
		Person p = new Person("Joe", 28,
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-03-06"));
		mongoOps.insert(p);
		logger.info("Insert: " + p);

		p = mongoOps.findById(p.getId(), Person.class);
		Assert.assertNotNull(p);
		logger.info("Found: " + p);

		// Update
		mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 29),
				Person.class);
		p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
		Assert.assertEquals(29, p.getAge());
		logger.info("Updated: " + p);

		// Delete
		mongoOps.remove(p);

		// Check that deletion worked
		List<Person> people = mongoOps.findAll(Person.class);
		Assert.assertEquals(0, people.size());
		logger.info("Number of people = : " + people.size());
	}
}
