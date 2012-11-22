package org.adaikiss.xun.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.adaikiss.xun.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles({ "test", "embedded" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	UserDao userDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGet() {
		User user = userDao.get(1l);
		assertThat(user.getNiceName(), equalTo("赵云"));
	}

	@Test
	public void testSave() {
		User user = new User("小小", "山岭巨人", "tiny");
		Long id = userDao.save(user);
		assertThat(id, equalTo(3l));
	}

}
