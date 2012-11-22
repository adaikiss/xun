package org.adaikiss.xun.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.adaikiss.xun.entity.User;
import org.hibernate.Session;
import org.junit.Test;

public class AutoIncrementTest extends HibernateTestCase {

	@Test
	public void testAutoIncrement(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new User("赵云", "zhaoyun"));
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new User("阿尔萨斯", "asas"));
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> result = (List<User>)session.createQuery("from User u").list();
		session.close();
		assertThat(result.get(0).getId(), equalTo(1l));
		assertThat(result.get(1).getId(), equalTo(2l));
	}
}
