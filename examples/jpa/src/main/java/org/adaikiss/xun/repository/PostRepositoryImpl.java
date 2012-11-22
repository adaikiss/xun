/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.List;

import org.adaikiss.xun.entity.Post;
import org.adaikiss.xun.utils.ServiceException;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hlw
 *
 */
@Repository
public class PostRepositoryImpl implements PostRepositorySolr {

	@Autowired(required = false)
	private SolrServer solrServer;

	@Override
	public void solrSave(Post post) {
		try {
			solrServer.addBean(post);
			solrServer.commit();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void solrDeleteById(Long id) {
		try {
			solrServer.deleteById(String.valueOf(id));
			solrServer.commit();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Post> solrQuery(String queryString) {
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery(queryString);
			QueryResponse res = solrServer.query(query);
			return res.getBeans(Post.class);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
