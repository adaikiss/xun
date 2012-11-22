/**
 * 
 */
package org.adaikiss.xun.repository;

import org.adaikiss.xun.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 *
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositorySolr{

}
