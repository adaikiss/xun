package org.adaikiss.xun.repository;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.adaikiss.xun.entity.Credit;
import org.adaikiss.xun.entity.QCredit;
import org.adaikiss.xun.predicate.CreditPredicates;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class CreditRepositoryTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	CreditRepository creditRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryDsl() {
		Credit c1 = creditRepository.findOne(1l);
		Credit c2 = creditRepository.findOne(2l);
		QCredit $ = QCredit.credit;
		Iterable<Credit> list = creditRepository.findAll($.credit1.goe(150)
				.and($.credit2.loe(250)));
		assertThat(list, hasItem(c1));
		assertThat(list, not(hasItem(c2)));
	}

	@Test
	public void testCustomPredicates() {
		Credit c1 = creditRepository.findOne(1l);
		Credit c2 = creditRepository.findOne(2l);
		Page<Credit> page = creditRepository.findAll(
				CreditPredicates.isProletariat(300), new PageRequest(0, 10));
		List<Credit> list = page.getContent();
		assertThat(list, hasItem(c1));
		assertThat(list, not(hasItem(c2)));
	}

	@Test
	public void testCustomImplement() {
		Credit c1 = creditRepository.findOne(1l);
		System.out.println("__________________________________________________________" + c1);
		Credit c2 = creditRepository.findOne(2l);
		List<Credit> list = creditRepository.findProletariats(300);
		assertThat(list, hasItem(c1));
		assertThat(list, not(hasItem(c2)));
	}

}
