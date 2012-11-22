package org.adaikiss.xun.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.adaikiss.xun.entity.Attachment;
import org.junit.After;
import org.junit.Before;
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
public class AttachmentRepositoryTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	AttachmentRepository attachmentRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByNameOrDescriptionLike() {
		String keyword = "%初见%";
		List<Attachment> list = attachmentRepository
				.findByNameLikeOrDescriptionLike(keyword, keyword);
		assertThat(list.size(), equalTo(1));
	}

}
