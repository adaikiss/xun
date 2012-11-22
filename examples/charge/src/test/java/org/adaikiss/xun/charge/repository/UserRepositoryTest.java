package org.adaikiss.xun.charge.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.enumeration.UserStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	UserRepository userRepository;

	@Test
	public void testFindByLoginNameAndStatus() {
		String loginName = "jetty";
		User user = userRepository.findByLoginNameAndStatus(loginName, UserStatus.normal);
		assertThat(user.getLoginName(), equalTo(loginName));
	}

}
