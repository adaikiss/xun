package org.adaikiss.xun.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.adaikiss.xun.entity.Post;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles({ "test", "embedded" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class PostDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	PostDao postDao;

	@Before
	public void setUp() throws Exception {
		FullTextSession fullTextSession = Search
				.getFullTextSession(sessionFactory.getCurrentSession());
		fullTextSession.createIndexer(Post.class).startAndWait();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFullTextQueryStringQueryParserPageable() {
		Page<Post> result = postDao.fullTextQuery("content:first",
				new QueryParser(Version.LUCENE_35, "content", new StopAnalyzer(
						Version.LUCENE_35)), new PageRequest(0, 10));
		List<Post> list = result.getContent();
		assertThat(list.size(), equalTo(2));
	}

	@Test
	public void testFullTextQueryStringQueryParser() {
		List<Post> list = postDao.fullTextQuery("content:first",
				new QueryParser(Version.LUCENE_35, "content", new StopAnalyzer(
						Version.LUCENE_35)));
		assertThat(list.size(), equalTo(2));
	}

}
