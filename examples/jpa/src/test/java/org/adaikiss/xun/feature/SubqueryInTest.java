/**
 * 
 */
package org.adaikiss.xun.feature;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import org.adaikiss.xun.entity.Role;
import org.adaikiss.xun.entity.User;
import org.adaikiss.xun.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
public class SubqueryInTest extends
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
	public void testFindUserByRole_Name() {
		final String roleName = "hero";
		Specification<User> spec = new Specification<User>() {

            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
                Subquery<Long> sq = query.subquery(Long.class);
                SetJoin<User, Role> roles = root.joinSet("roles");
                Root<Role> roleRoot = sq.from(Role.class);
                sq.select(roleRoot.<Long>get("id")).where(cb.equal(roleRoot.<String> get("name"), roleName));
                return cb.in(roles.<Long>get("id")).value(sq);
            }
        };
        List<User> users = userRepository.findAll(spec);
        for(User user : users){
        	System.out.println(user.getDisplayName());
        }
        Assert.assertThat(users.size(), is(3));
        Assert.assertThat(Arrays.asList(new String[]{"赵云", "阿尔萨斯", "小小"}), hasItems(users.get(0).getNiceName(), users.get(1).getNiceName(), users.get(2).getNiceName()));
	}
}