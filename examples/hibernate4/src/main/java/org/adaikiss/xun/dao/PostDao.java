/**
 * 
 */
package org.adaikiss.xun.dao;

import java.util.List;

import org.adaikiss.xun.entity.Post;
import org.adaikiss.xun.utils.ServiceException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author hlw
 * 
 */
@Repository
public class PostDao extends BaseDao<Post, Long> {
	public Page<Post> fullTextQuery(String queryString, QueryParser parser,
			Pageable pageRequest) {
		Query luceneQuery;
		try {
			luceneQuery = parser.parse(queryString);
		} catch (ParseException e) {
			throw new ServiceException(e);
		}
		FullTextSession s = Search.getFullTextSession(getSession());
		FullTextQuery query = s.createFullTextQuery(luceneQuery, Post.class);
		query.setMaxResults(pageRequest.getPageSize());
		query.setFirstResult(pageRequest.getOffset());
		@SuppressWarnings("unchecked")
		List<Post> result = query.list();
		PageImpl<Post> page = new PageImpl<Post>(result, pageRequest,
				query.getResultSize());
		return page;
	}

	public List<Post> fullTextQuery(String queryString, QueryParser parser) {
		Query luceneQuery;
		try {
			luceneQuery = parser.parse(queryString);
		} catch (ParseException e) {
			throw new ServiceException(e);
		}
		FullTextSession s = Search.getFullTextSession(getSession());
		FullTextQuery query = s.createFullTextQuery(luceneQuery, Post.class);
		@SuppressWarnings("unchecked")
		List<Post> result = query.list();
		return result;
	}
}
