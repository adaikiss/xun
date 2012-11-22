/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.List;

import org.adaikiss.xun.entity.Post;

/**
 * solr access
 * @author hlw
 *
 */
public interface PostRepositorySolr {
	void solrSave(Post post);
	void solrDeleteById(Long id);
	List<Post> solrQuery(String queryString);
}
