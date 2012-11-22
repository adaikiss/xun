package org.adaikiss.xun.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;

import org.adaikiss.xun.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author hlw
 * 
 */
@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class UserRepositoryTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setDisplayName("测试一号");
		user.setLoginName("testone");
		user.setNiceName("测试一号");
		User saved = userRepository.save(user);
		assertThat(saved.getId(), notNullValue());
	}

	@Test
	public void testFindByLoginName() {
		String loginName = "zhaoyun";
		User user = userRepository.findByLoginName(loginName);
		assertThat(user.getLoginName(), equalTo(loginName));
	}

	@Test
	public void testFindByDisplayName() {
		String displayName = "常山赵子龙";
		List<User> result = userRepository.findByDisplayName(displayName);
		assertThat(result.size(), equalTo(1));
	}

	@Test
	public void testSearchByNiceNameOrDisplayName() {
		User user = userRepository.findOne(1l);
		String keyword = "%赵子龙%";
		Page<User> page = userRepository.searchByNiceNameOrDisplayName(keyword,
				new PageRequest(0, 10));
		assertThat(page.getContent().size(), equalTo(1));
		assertThat(page.getContent(), hasItem(user));
	}

	@Test
	public void testFindByNiceNameLikeOrDisplayNameLike() {
		User user = userRepository.findOne(1l);
		String keyword = "%赵子龙%";
		Page<User> page = userRepository.findByNiceNameLikeOrDisplayNameLike(
				keyword, keyword, new PageRequest(0, 10));
		assertThat(page.getContent().size(), equalTo(1));
		assertThat(page.getContent(), hasItem(user));
	}
}
