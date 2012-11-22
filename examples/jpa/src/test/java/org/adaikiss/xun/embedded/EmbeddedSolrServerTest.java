/**
 * 
 */
package org.adaikiss.xun.embedded;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author hlw
 * 
 */
@ActiveProfiles({ "test" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-solr.xml" })
public class EmbeddedSolrServerTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	SolrServer solrServer;

	@Test
	public void testEmbeddedSolrServer() throws Exception {
		SolrQuery q = new SolrQuery();
		q.setQuery("*:*");
		solrServer.query(q).getResults().getNumFound();// no exception means
														// success
	}
}
