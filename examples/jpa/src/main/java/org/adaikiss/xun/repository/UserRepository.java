package org.adaikiss.xun.repository;

import java.util.List;

import org.adaikiss.xun.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> ,
JpaSpecificationExecutor<User>{
	@Transactional(readOnly = true)
	public User findByLoginName(String loginName);

	@Transactional(readOnly = true)
	public List<User> findByDisplayName(String displayName);

	@Query("from User u where u.niceName like :keyword or u.displayName like :keyword")
	public Page<User> searchByNiceNameOrDisplayName(
			@Param("keyword") String keyword, Pageable pageRequest);

	public Page<User> findByNiceNameLikeOrDisplayNameLike(String keyword1,
			String keyword2, Pageable pageRequest);

	@Transactional(readOnly = true)
	public List<User> findByRoles_Name(String roleName);
}
