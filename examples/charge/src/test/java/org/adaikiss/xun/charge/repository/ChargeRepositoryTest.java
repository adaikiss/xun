/**
 * 
 */
package org.adaikiss.xun.charge.repository;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.adaikiss.xun.charge.entity.Charge;
import org.adaikiss.xun.charge.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChargeRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ChargeRepository chargeRepository;

	@Test
	public void testFindByUserOrderByDateDesc() {
		User user = new User();
		user.setId(1l);
		List<Charge> result = chargeRepository.findByUserOrderByDateDesc(user);
		assertThat(result.size(), not(0));
	}

	@Test
	public void testFindByUserIdOrderByDateDesc(){
		List<Charge> result = chargeRepository.findByUserIdOrderByDateDesc(1l);
		assertThat(result.size(), not(0));
	}
}
