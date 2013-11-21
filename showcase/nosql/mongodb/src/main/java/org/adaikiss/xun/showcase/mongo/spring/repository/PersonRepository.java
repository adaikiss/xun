/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring.repository;

import java.util.List;

import org.adaikiss.xun.showcase.mongo.spring.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author HuLingwei
 *
 */
public interface PersonRepository extends MongoRepository<Person, String>{
	public List<Person> findByName(String name);
	public List<Person> findByAgeGreaterThan(int age);
}
