/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.adaikiss.xun.showcase.mongo.spring.entity.Person;
import org.adaikiss.xun.showcase.mongo.spring.repository.PersonRepository;
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
@ActiveProfiles({ "test", "repository" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MongoSpringDataRepositoryIllustrationTest extends
		AbstractJUnit4SpringContextTests {
	@Autowired
	PersonRepository repo;
	@Autowired
	MongoOperations mongoOps;

	@After
	public void tearDown() {
		mongoOps.dropCollection(Person.class);
	}

	@Test
	public void testCrud() throws Exception {
		Person p = new Person("Joe", 28,
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-03-06"));
		repo.save(p);
		logger.info("Insert: " + p);

		p = repo.findOne(p.getId());
		Assert.assertNotNull(p);
		logger.info("Found: " + p);

		// Update
		p.setAge(29);
		repo.save(p);
		p = repo.findByName("Joe").get(0);
		Assert.assertEquals(29, p.getAge());
		logger.info("Updated: " + p);

		// Delete
		repo.delete(p);

		// Check that deletion worked
		List<Person> people = repo.findAll();
		Assert.assertEquals(0, people.size());
		logger.info("Number of people = : " + people.size());
	}

	@Test
	public void testFindByAgeGreaterThan(){
		repo.save(Arrays.asList(new Person("Jack", 27), new Person("John", 28), new Person("Jackson", 29)));
		List<Person> people = repo.findByAgeGreaterThan(27);
		Assert.assertEquals(2, people.size());
	}
}
