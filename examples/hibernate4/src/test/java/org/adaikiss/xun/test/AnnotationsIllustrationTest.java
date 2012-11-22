package org.adaikiss.xun.test;

import java.util.List;

import org.adaikiss.xun.entity.User;
import org.hibernate.Session;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class AnnotationsIllustrationTest extends HibernateTestCase {

	@Test
	public void testBasicUsage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		User user1 = new User();
		user1.setNiceName("赵云");
		user1.setDisplayName("常山赵子龙");
		user1.setLoginName("zhaoyun");
		User user2 = new User();
		user2.setNiceName("阿尔萨斯");
		user2.setDisplayName("夜之哀伤");
		user2.setLoginName("asas");
		session.save(user1);
		session.save(user2);

		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> result = session.createQuery("from User").list();
		session.close();
		assertThat(result.size(), equalTo(2));
		assertThat(result, hasItem(user1));
		assertThat(result, hasItem(user2));
		session.getTransaction().commit();
	}
}
